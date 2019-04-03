package com.papps.freddy_lazo.redvet.view.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.MapFragmentView;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerMapFragmentComponent;
import com.papps.freddy_lazo.redvet.model.DoctorModel;
import com.papps.freddy_lazo.redvet.presenter.MapFragmentPresenter;
import com.papps.freddy_lazo.redvet.view.activity.HomeActivity;
import com.papps.freddy_lazo.redvet.view.adapter.PetAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MapFragment extends BaseFragment implements OnMapReadyCallback, MapFragmentView {

    private static final int REQUEST_LOCATION = 2;
    private HomeActivity activity;
    private FusedLocationProviderClient fusedLocationClient;
    private GoogleMap googleMap;


    @Inject
    PetAdapter adapter;
    @Inject
    MapFragmentPresenter presenter;

    @BindView(R.id.rv_pet)
    RecyclerView recyclerView;

    public static Fragment newInstance() {
        return new MapFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildInjection();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (HomeActivity) getActivity();
        checkPermission();
        initUI();

    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    private void checkPermission() {
        int permissionGranted = ContextCompat.checkSelfPermission(context(), Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionGranted != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION);
        } else {
            initMap();
        }
    }

    @SuppressLint("MissingPermission")
    private void requestLastPosition() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(activity, location -> {
                    // Got last known location. In some rare situations this can be null.
                    if (location != null) {
                        LatLng position = new LatLng(location.getLatitude(), location.getLongitude());
                        googleMap.addMarker(new MarkerOptions().position(position)
                                .icon(bitmapDescriptorFromVector(activity, R.drawable.ic_person_marker)));


                        CameraPosition cameraPosition = new CameraPosition.Builder()
                                .target(position)      // Sets the center of the map to Mountain View
                                .zoom(17)           // Sets the zoom
                                .build();                   // Creates a CameraPosition from the builder
                        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                        // Logic to handle location object
                    }
                });
    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initMap();
            } else {

                if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_CONTACTS)) {
                    //Permission was clicked for never again

                }
            }
        }
    }

    private void buildInjection() {
        DaggerMapFragmentComponent.builder()
                .applicationComponent(getAndroidApplication().getApplicationComponent())
                .build().inject(this);
    }

    @Override
    public void initUI() {
        presenter.setView(this);
        setUpPetRv();
    }

    private void setUpPetRv() {
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        adapter.bindList(true);
    }

    @Override
    public Context context() {
        return getContext();
    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void showErrorNetworkMessage(String message) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        Log.d("onMapReady", "cargo el mapa");
        requestLastPosition();
        presenter.getDoctors();
    }

    @OnClick(R.id.txt_services)
    public void servicesClick() {
        navigator.navigateToServicesFragment(activity);
    }

    @Override
    public void getListData(List<DoctorModel> data) {
        setDoctorMarkers(data);
    }

    private void setDoctorMarkers(List<DoctorModel> data) {
        for (DoctorModel doctorModel : data){
            LatLng latLng = new LatLng(Double.valueOf(doctorModel.getLatitude()), Double.valueOf(doctorModel.getLongitude()));
            addDoctorMarker(latLng);
        }
    }

    private void addDoctorMarker(LatLng latLng) {
        if(googleMap != null){
            googleMap.addMarker(new MarkerOptions().position(latLng).icon(bitmapDescriptorFromVector(activity, R.drawable.ic_doctor_marker)));
        }
    }
}
