package com.papps.freddy_lazo.redvet.view.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.papps.freddy_lazo.domain.model.ServicesDoctorRegister;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.MapFragmentView;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerMapFragmentComponent;
import com.papps.freddy_lazo.redvet.model.CreateAppointmentObjectModel;
import com.papps.freddy_lazo.redvet.model.DoctorModel;
import com.papps.freddy_lazo.redvet.model.PetRedVetModel;
import com.papps.freddy_lazo.redvet.model.ServiceDoctorModel;
import com.papps.freddy_lazo.redvet.model.ServicesModel;
import com.papps.freddy_lazo.redvet.presenter.MapFragmentPresenter;
import com.papps.freddy_lazo.redvet.view.activity.HomeActivity;
import com.papps.freddy_lazo.redvet.view.adapter.AppointmentHeaderAdapter;
import com.papps.freddy_lazo.redvet.view.adapter.PetAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MapFragment extends BaseFragment implements OnMapReadyCallback, MapFragmentView, GoogleMap.OnMarkerClickListener,
        PetAdapter.onClickAdapter, AppointmentHeaderAdapter.onClickAdapter, SearchView.OnQueryTextListener {

    private static final int REQUEST_LOCATION = 2;
    private static final int SERVICES_REQUEST_CODE = 3;
    private HomeActivity activity;
    private FusedLocationProviderClient fusedLocationClient;
    private GoogleMap googleMap;


    @Inject
    PetAdapter adapter;
    @Inject
    MapFragmentPresenter presenter;
    @BindView(R.id.rv_pet)
    RecyclerView recyclerView;
    @BindView(R.id.rv_types)
    RecyclerView rvTypes;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Inject
    AppointmentHeaderAdapter typeAdapter;

    private LatLng position;
    private List<Integer> petsIdArray = new ArrayList<>();
    private List<String> typeArray = new ArrayList<>();
    private List<ServicesDoctorRegister> servicesDoctorRegisterList = new ArrayList<>();
    private List<Integer> servicesArray = new ArrayList<>();
    private String query;

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
        setHasOptionsMenu(true);
        buildInjection();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (HomeActivity) getActivity();
        Objects.requireNonNull(activity).setSupportActionBar(toolbar);
        activity.setTitle("Filtrar");
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


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.map_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(this);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void checkPermission() {
        int permissionGranted = ContextCompat.checkSelfPermission(context(), Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionGranted != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION);
        } else {
            typeArray.add("clinic");
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
                        position = new LatLng(location.getLatitude(), location.getLongitude());
                        googleMap.addMarker(new MarkerOptions().position(position)
                                .icon(bitmapDescriptorFromVector(activity, R.drawable.ic_location_marker)));
                        btnLocation();
                        // Logic to handle location object
                    }
                });
    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, Objects.requireNonNull(vectorDrawable).getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SERVICES_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                getServicesData(data);
            }
        }
    }

    private void getServicesData(Intent listData) {
        List<ServicesModel> servicesData = listData.getParcelableArrayListExtra("data");
        servicesDoctorRegisterList.clear();
        if (!servicesData.isEmpty()) {
            for (ServicesModel servicesModel : servicesData) {
                if (servicesModel.getState()) {
                    ServicesDoctorRegister data = new ServicesDoctorRegister(servicesModel.getResponseId(), servicesModel.getId());
                    servicesDoctorRegisterList.add(data);
                }
            }
        }
        sendRequest();
    }

    private void sendRequest() {
        servicesArray.clear();
        for (ServicesDoctorRegister model : servicesDoctorRegisterList) {
            servicesArray.add(model.getService_id());
        }
        presenter.getDoctors();
    }

    @Override
    public void initUI() {
        presenter.setView(this);
        setUpPetRv();
        presenter.getPets();
    }

    private void setUpPetRv() {
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        adapter.setView(this);

        rvTypes.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        rvTypes.setAdapter(typeAdapter);
        typeAdapter.setView(this);
        initHeaderAdapter();
    }

    private void initHeaderAdapter() {
        List<CreateAppointmentObjectModel> data = new ArrayList<>();
        data.add(new CreateAppointmentObjectModel("Clínicas", "clinic", true));
        data.add(new CreateAppointmentObjectModel("Veterinarios", "vet"));
        data.add(new CreateAppointmentObjectModel("Otros", "other"));
        typeAdapter.bindList(data);
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
        googleMap.setOnMarkerClickListener(this);
        Log.d("onMapReady", "cargo el mapa");
        presenter.getDoctors();
    }

    @OnClick(R.id.txt_services)
    public void servicesClick() {
        List<ServiceDoctorModel> data = new ArrayList<>();
        if (!servicesDoctorRegisterList.isEmpty()) {
            for (ServicesDoctorRegister tmpData : servicesDoctorRegisterList) {
                data.add(new ServiceDoctorModel(tmpData.getId(), tmpData.getService_id()));
            }
        }
        navigator.navigateToServicesActivity(this, data, SERVICES_REQUEST_CODE);
    }

    @Override
    public void getListData(List<DoctorModel> data) {
        setDoctorMarkers(data);
        requestLastPosition();
    }

    @Override
    public String getApiToken() {
        return activity.getModel().getApi_token();
    }

    @Override
    public List<String> getType() {
        return typeArray;
    }

    @Override
    public List<Integer> getServices() {
        return servicesArray;
    }

    @Override
    public List<Integer> getPets() {
        return petsIdArray;
    }

    @Override
    public String getText() {
        return query;
    }

    @Override
    public void successRequest(List<PetRedVetModel> data) {
        adapter.bindList(data);
    }

    private void setDoctorMarkers(List<DoctorModel> data) {
        googleMap.clear();
        for (DoctorModel doctorModel : data) {
            LatLng latLng = new LatLng(Double.valueOf(doctorModel.getLatitude()), Double.valueOf(doctorModel.getLongitude()));
            addDoctorMarker(latLng, doctorModel);
        }
    }

    private void addDoctorMarker(LatLng latLng, DoctorModel doctorModel) {
        if (googleMap != null) {
            googleMap.addMarker(new MarkerOptions().position(latLng).icon(bitmapDescriptorFromVector(activity, getIcon(doctorModel.getType())))).setTag(doctorModel);
        }
    }

    private int getIcon(String type) {
        switch (type) {
            case "clinic":
                return R.drawable.ic_marker_clinic;
            case "vet":
                return R.drawable.ic_doctor_marker;
            case "other":
                return R.drawable.ic_marker_other;
            default:
                return R.drawable.ic_doctor_marker;
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Log.d("onMarkerClick", String.valueOf(marker.getTag()));
        if (marker.getTag() != null)
            navigator.navigateDoctorDetailFragment(activity, String.valueOf(marker.getTag()));
        return false;
    }

    @OnClick(R.id.btn_location)
    public void btnLocation() {
        if (position != null && googleMap != null) {
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(position)            // Sets the center of the map to Mountain View
                    .zoom(17)                    // Sets the zoom
                    .build();                   // Creates a CameraPosition from the builder
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public void data(List<PetRedVetModel> data) {
        petsIdArray.clear();
        for (PetRedVetModel petData : data) {
            if (petData.isSelected()) {
                petsIdArray.add(petData.getId());
            }
        }
        presenter.getDoctors();
    }

    @Override
    public void dataAdapter(List<CreateAppointmentObjectModel> data) {
        typeArray.clear();
        for (CreateAppointmentObjectModel model : data) {
            if (model.isSelected()) {
                typeArray.add(model.getSearchName());
            }
        }
        presenter.getDoctors();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        query = s;
        presenter.getDoctors();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        query = s;
        return false;
    }
}
