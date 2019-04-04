package com.papps.freddy_lazo.redvet.view.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerMapComponent;

import butterknife.BindView;
import butterknife.OnClick;

public class MapActivity extends BaseActivity implements OnMapReadyCallback {

    private static final int REQUEST_LOCATION = 1;
    private static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 2;

    private FusedLocationProviderClient fusedLocationClient;
    private GoogleMap googleMap;


    public static Intent getCallingIntent(BaseActivity activity) {
        return new Intent(activity, MapActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        injectView(this);
        buildInjection();
        initUI();
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
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        requestLastPosition();
    }

    @OnClick(R.id.card_search_container)
    public void goToLocations(){
        seeLocationsApi();
    }

    private void seeLocationsApi() {
        try {
            Log.d("cardClicked", "try");
            AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                    .setCountry("PE")
                    .build();
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                            .setFilter(typeFilter)
                            .build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException e) {
            // TODO: Handle the error.
            Log.d("cardClicked", "GooglePlayServicesRepairableException");

        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
            Log.d("cardClicked", "GooglePlayServicesNotAvailableException");
        }
    }
}
