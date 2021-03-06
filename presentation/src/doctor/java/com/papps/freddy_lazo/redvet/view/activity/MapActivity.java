package com.papps.freddy_lazo.redvet.view.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerMapComponent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

public class MapActivity extends BaseActivity implements OnMapReadyCallback, GoogleMap.OnCameraIdleListener {

    private static final int REQUEST_LOCATION = 1;
    private static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 2;

    private FusedLocationProviderClient fusedLocationClient;
    private GoogleMap googleMap;


    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.ready_location)
    Button readyButton;
    private LatLng latlng;
    private String address;
    private List<Place.Field> placeFields;

    public static Intent getCallingIntent(Fragment fragment) {
        return new Intent(fragment.getContext(), MapActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        injectView(this);
        buildInjection();
        iniNewApiPlaces();
        initUI();
    }

    private void iniNewApiPlaces() {
        String apiKey = getString(R.string.places_api_key);
        // Setup Places Client
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), apiKey);
        }
        placeFields = getPlaceFields(Place.Field.ADDRESS_COMPONENTS, Place.Field.PHONE_NUMBER, Place.Field.WEBSITE_URI, Place.Field.OPENING_HOURS);
    }


    static List<Place.Field> getPlaceFields(Place.Field... placeFieldsToOmit) {
        // Arrays.asList is immutable, create a mutable list to allow removing fields
        List<Place.Field> placeFields = new ArrayList<>(Arrays.asList(Place.Field.values()));
        placeFields.removeAll(Arrays.asList(placeFieldsToOmit));

        return placeFields;
    }

    private void buildInjection() {
        DaggerMapComponent.builder().applicationComponent(getApplicationComponent()).build().inject(this);
    }

    @Override
    public void initUI() {
        checkPermission();
    }

    private void checkPermission() {
        int permissionGranted = ContextCompat.checkSelfPermission(context(), Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionGranted != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION);
        } else {
            initMap();
        }
    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @SuppressLint("MissingPermission")
    private void requestLastPosition() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, location -> {
                    // Got last known location. In some rare situations this can be null.
                    if (location != null) {
                        LatLng position = new LatLng(location.getLatitude(), location.getLongitude());

                        CameraPosition cameraPosition = new CameraPosition.Builder()
                                .target(position)      // Sets the center of the map to Mountain View
                                .zoom(17)           // Sets the zoom
                                .build();                   // Creates a CameraPosition from the builder
                        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                        // Logic to handle location object
                    }
                });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initMap();
            } else {

                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
                    //Permission was clicked for never again

                }
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == AutocompleteActivity.RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                tvAddress.setText(place.getAddress());
                moveCamera(place.getLatLng());
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                Status status = Autocomplete.getStatusFromIntent(data);
            } else if (resultCode == AutocompleteActivity.RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
        // Required because this class extends AppCompatActivity which extends FragmentActivity
        // which implements this method to pass onActivityResult calls to child fragments
        // (eg AutocompleteFragment).
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCameraIdle() {
        latlng = googleMap.getCameraPosition().target;
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(latlng.latitude, latlng.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            if (!addresses.isEmpty()) {
                address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                Log.d("onCameraIdle", "la direccion de la camara es : " + address);
                tvAddress.setText(address);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void moveCamera(LatLng latLng) {
        CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(latLng, 17);
        googleMap.animateCamera(cu);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        readyButton.setVisibility(View.VISIBLE);
        googleMap.setOnCameraIdleListener(this);
        requestLastPosition();
    }

    @OnClick(R.id.card_search_container)
    public void goToLocations() {
        seeLocationsApi();
    }

    @OnClick(R.id.ready_location)
    public void rdyLocation() {
        saveData();
    }

    public void saveData() {
       if (latlng != null && address != null) {
            Intent data = new Intent();
            data.putExtra("latitude", latlng.latitude);
            data.putExtra("longitude", latlng.longitude);
            data.putExtra("address", address);
            setResult(Activity.RESULT_OK, data);
            finish();
        }
    }



    private void seeLocationsApi() {
        Intent autocompleteIntent =
                new Autocomplete.IntentBuilder( AutocompleteActivityMode.OVERLAY, placeFields)
                        .setCountry("PE")
                        .build(this);
        startActivityForResult(autocompleteIntent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
    }
}
