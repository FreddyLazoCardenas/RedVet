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
import com.papps.freddy_lazo.data.entity.mapper.AppointmentPhotoMapper;
import com.papps.freddy_lazo.data.sharedPreferences.PreferencesManager;
import com.papps.freddy_lazo.redvet.GlideApp;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.DoctorFinishedFragmentView;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerFinishedAppointmentDialogComponent;
import com.papps.freddy_lazo.redvet.model.AppointmentPhotoModel;
import com.papps.freddy_lazo.redvet.model.DoctorAppointmentModel;
import com.papps.freddy_lazo.redvet.presenter.DoctorFinishedFragmentPresenter;
import com.papps.freddy_lazo.redvet.util.DateHelper;
import com.papps.freddy_lazo.redvet.view.activity.HomeActivity;
import com.papps.freddy_lazo.redvet.view.adapter.AppointmentPhotoAdapter;

import java.text.MessageFormat;
import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class FinishedAppointmentDialog extends BaseDialogFragment implements AppointmentPhotoAdapter.onClickAdapter, PhotoListDialog.OnClickListener, DoctorFinishedFragmentView {

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
    @BindView(R.id.tv_owner)
    TextView tvPetLoverName;
    @BindView(R.id.txt_dni)
    TextView tvDni;
    @BindView(R.id.diagnose_content)
    TextView diagnoseContent;
    @BindView(R.id.rv_attach)
    RecyclerView rvAttach;
    @Inject
    PreferencesManager preferencesManager;
    @Inject
    AppointmentPhotoAdapter adapter;
    @Inject
    DoctorFinishedFragmentPresenter presenter;
    @BindView(R.id.group_buttons)
    android.support.constraint.Group gButtons;

    private DoctorAppointmentModel model;
    private HomeActivity activity;
    private RequestInterface listener;
    private int photoId;


    public static FinishedAppointmentDialog newInstance(String data, RequestInterface listener) {
        Bundle args = new Bundle();
        args.putString("data", data);
        FinishedAppointmentDialog dialog = new FinishedAppointmentDialog();
        dialog.listener = listener;
        dialog.setCancelable(false);
        dialog.setArguments(args);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_finished_appointment, container, false);
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
        displayPhoto(model.getPet().getPhoto_url(), imgPet);
        displayPhoto(model.getPetLover().getPhoto_url(), imgOwner);
        tvPet.setText(model.getPet().getName());
        Calendar calendar = DateHelper.convertToDate(model.getPet().getBirthday());
        tvPetBirthday.setText(MessageFormat.format("{0} {1} {2}", calendar.get(Calendar.DAY_OF_MONTH), DateHelper.getMonthForInt(calendar.get(Calendar.MONTH)).substring(0, 3), calendar.get(Calendar.YEAR)).replaceAll(",", ""));
        String[] split = model.getDate().split("-");
        date.setText(getString(R.string.doctor_date, split[2], split[1], split[0].substring(2)));
        time.setText(model.getTime());
        address.setText(model.getPetLover().getAddress());
        tvPetLoverName.setText(MessageFormat.format("{0} {1}", model.getPetLover().getFirst_name(), model.getPetLover().getLast_name()));
        tvDni.setText(model.getPetLover().getDni());
        diagnoseContent.setText(model.getDiagnosis());
        adapter.bindList(model.getPhotos());
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
            presenter.setView(this);
            String data = getArguments().getString("data");
            model = DoctorAppointmentModel.toModel(data);
            setUpRv();
            fillUi();
        }
    }

    @Override
    public Context context() {
        return activity;
    }

    @Override
    public void showErrorMessage(String message) {
        showMessage(activity, message);
    }

    @Override
    public void showErrorNetworkMessage(String message) {

    }

    private void setUpRv() {
        rvAttach.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        rvAttach.setAdapter(adapter);
        adapter.setListener(this);
    }

    @Override
    public void itemClicked(AppointmentPhotoModel data) {
        photoId = data.getId();
        navigator.showPhotoListDialog(activity, this);
    }

    @Override
    public void delete() {
        presenter.deletePhoto();
    }

    @Override
    public void cancel() {

    }

    @OnClick(R.id.chat)
    public void chatClicked() {
        navigator.navigateToChatActivity(activity, model.getId());
    }

    @OnClick(R.id.phone)
    public void phoneClicked() {
        navigator.navigatePhoneCall(activity, model.getPetLover().getPhone());
    }

    @Override
    public String getApiToken() {
        return activity.getModel().getApi_token();
    }

    @Override
    public int getAppointmentId() {
        return model.getId();
    }

    @Override
    public int getPhotoId() {
        return photoId;
    }

    @Override
    public void successRequest() {
        adapter.itemDeleted(getPhotoId());
    }

    public interface RequestInterface {
        void successFinishedRequest(int id);
    }
}
