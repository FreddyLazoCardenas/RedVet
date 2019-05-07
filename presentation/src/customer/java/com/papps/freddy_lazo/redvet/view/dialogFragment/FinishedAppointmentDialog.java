package com.papps.freddy_lazo.redvet.view.dialogFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.papps.freddy_lazo.data.sharedPreferences.PreferencesManager;
import com.papps.freddy_lazo.redvet.GlideApp;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.PetLoverAppointmentView;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerFinishedAppointmentDialogComponent;
import com.papps.freddy_lazo.redvet.model.AppointmentPhotoModel;
import com.papps.freddy_lazo.redvet.model.PetLoverAppointmentModel;
import com.papps.freddy_lazo.redvet.model.PetLoverModel;
import com.papps.freddy_lazo.redvet.model.PetLoverRegisterModel;
import com.papps.freddy_lazo.redvet.presenter.PetLoverFinishedAppointmentPresenter;
import com.papps.freddy_lazo.redvet.util.DateHelper;
import com.papps.freddy_lazo.redvet.view.activity.HomeActivity;
import com.papps.freddy_lazo.redvet.view.adapter.AppointmentPhotoAdapter;

import java.text.MessageFormat;
import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class FinishedAppointmentDialog extends BaseDialogFragment implements AppointmentPhotoAdapter.onClickAdapter, PhotoListDialog.OnClickListener, PetLoverAppointmentView {

    @BindView(R.id.img_pet)
    ImageView imgPet;
    @BindView(R.id.img_owner)
    ImageView imgOwner;
    @BindView(R.id.txt_name)
    TextView tvPet;
    @BindView(R.id.txt_pet_birthday)
    TextView tvPetBirthday;
    @BindView(R.id.txt_date)
    TextView date;
    @BindView(R.id.txt_time)
    TextView time;
    @BindView(R.id.txt_address)
    TextView address;
    @BindView(R.id.txt_dni)
    TextView tvDni;
    @BindView(R.id.diagnose_content)
    TextView diagnoseContent;
    @BindView(R.id.rv_attach)
    RecyclerView rvAttach;
    @BindView(R.id.tv_doctor_job)
    TextView tvDoctorJob;
    @BindView(R.id.tv_doctor_name)
    TextView tvDoctorName;
    @BindView(R.id.group_buttons)
    android.support.constraint.Group gButtons;

    @Inject
    PreferencesManager preferencesManager;
    @Inject
    PetLoverFinishedAppointmentPresenter presenter;
    @Inject
    AppointmentPhotoAdapter adapter;

    private PetLoverAppointmentModel model;
    private PetLoverRegisterModel pet;
    private PetLoverModel petLoverModel;
    private HomeActivity activity;
    private int photoId;


    public static FinishedAppointmentDialog newInstance(String data) {
        Bundle args = new Bundle();
        args.putString("data", data);
        FinishedAppointmentDialog dialog = new FinishedAppointmentDialog();
        dialog.setCancelable(false);
        dialog.setArguments(args);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_pl_finished_appointment, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildInjection();
    }


    private void buildInjection() {
        DaggerFinishedAppointmentDialogComponent.builder()
                .applicationComponent(getAndroidApplication().getApplicationComponent())
                .build().inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();
    }


    private void fillUi() {
        gButtons.setVisibility(View.GONE);
        displayPhoto(pet.getPhoto_url(), imgPet);
        displayPhoto(model.getDoctor().getPhoto_url(), imgOwner);
        tvPet.setText(pet.getName());
        String[] split = model.getDate().split("-");
        date.setText(getString(R.string.doctor_date, split[2], split[1], split[0].substring(2)));
        time.setText(model.getTime());
        Calendar calendar = DateHelper.convertToDate(pet.getBirthday());
        tvPetBirthday.setText(MessageFormat.format("{0} {1} {2}", calendar.get(Calendar.DAY_OF_MONTH), DateHelper.getMonthForInt(calendar.get(Calendar.MONTH)).substring(0, 3), calendar.get(Calendar.YEAR)));
        address.setText(model.getDoctor().getAddress());
        tvDoctorName.setText(MessageFormat.format("{0} {1}", model.getDoctor().getFirst_name(), model.getDoctor().getLast_name()));
        tvDni.setText(model.getDoctor().getNumber_document());
        tvDoctorJob.setText(setJobText(model.getDoctor().getType()));
        diagnoseContent.setText(model.getDiagnosis());
        adapter.bindList(model.getPhotos());
    }

    private int setJobText(String type) {
        switch (type) {
            case "clinic":
                return R.string.doctor_clinic_type;
            case "vet":
                return R.string.doctor_vet_type;
            case "other":
                return R.string.doctor_other_type;
            default:
                return R.string.doctor_clinic_type;
        }
    }


    public void displayPhoto(String photoUrl, ImageView img) {
        GlideApp.with(activity)
                .asBitmap()
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .placeholder(R.drawable.ic_placeholder)
                .load(photoUrl)
                .into(img);
    }

    @OnClick(R.id.btn_cancel)
    public void btnCancelAppointment() {
        dismiss();
    }


    @OnClick(R.id.img_dismiss)
    public void btnDismiss() {
        dismiss();
    }

    @Override
    public void initUI() {
        if (getArguments() != null) {
            activity = (HomeActivity) getActivity();
            String data = getArguments().getString("data");
            model = PetLoverAppointmentModel.toModel(data);
            petLoverModel = PetLoverModel.toModel(preferencesManager.getPetLoverCurrentUser());
            presenter.setView(this);
            getPetData(model.getPet_by_pet_lover_id());
            setUpRv();
            fillUi();
        }
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void showErrorMessage(String message) {
        showMessage(activity, message);
    }

    @Override
    public void showErrorNetworkMessage(String message) {

    }

    private void getPetData(String petId) {
        for (PetLoverRegisterModel petLoverRegisterModel : petLoverModel.getPetList()) {
            if (petLoverRegisterModel.getId() == Integer.valueOf(petId)) {
                pet = petLoverRegisterModel;
                break;
            }
        }
    }

    private void setUpRv() {
        rvAttach.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        rvAttach.setAdapter(adapter);
        adapter.setListener(this);
    }

    @Override
    public void itemClicked(AppointmentPhotoModel data) {
        photoId = data.getId();
        navigator.navigateToPhotoDetailActivity(activity, data.getPhoto_url());
    }

    @Override
    public void delete() {
        presenter.sendRequest();
    }

    @Override
    public void cancel() {

    }

    @Override
    public String getApiToken() {
        return PetLoverModel.toModel(preferencesManager.getPetLoverCurrentUser()).getApi_token();
    }

    @Override
    public int getAppointmentId() {
        return model.getId();
    }

    @Override
    public int getPhotoAppointmentId() {
        return photoId;
    }

    @Override
    public void successDelete() {
        adapter.itemDeleted(photoId);
    }

    @OnClick(R.id.chat)
    public void chatClicked() {
        navigator.navigateToChatActivity(activity, getAppointmentId());
    }

    @OnClick(R.id.phone)
    public void phoneClicked() {
        navigator.navigatePhoneCall(activity, model.getDoctor().getPhone());
    }

    @OnClick(R.id.txt_address)
    public void goToAppointment() {
        navigator.navigateToNavigation(activity, model.getDoctor().getLatitude(), model.getDoctor().getLongitude());
    }
}
