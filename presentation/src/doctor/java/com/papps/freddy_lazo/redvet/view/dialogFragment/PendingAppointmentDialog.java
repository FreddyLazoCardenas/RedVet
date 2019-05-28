package com.papps.freddy_lazo.redvet.view.dialogFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.papps.freddy_lazo.data.sharedPreferences.PreferencesManager;
import com.papps.freddy_lazo.redvet.GlideApp;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.PendingAppointmentDialogView;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerLoginFragmentComponent;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerPendingAppointmentDialogComponent;
import com.papps.freddy_lazo.redvet.model.DoctorAppointmentModel;
import com.papps.freddy_lazo.redvet.model.DoctorModel;
import com.papps.freddy_lazo.redvet.presenter.DoctorPendingAppointmentPresenter;
import com.papps.freddy_lazo.redvet.util.DateHelper;
import com.papps.freddy_lazo.redvet.view.activity.HomeActivity;

import java.text.MessageFormat;
import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class PendingAppointmentDialog extends BaseDialogFragment implements PendingAppointmentDialogView {

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
    @BindView(R.id.tv_appointment_type)
    TextView tvAppointmentType;
    @BindView(R.id.tv_appointment_reason)
    TextView tvAppointmentReason;
    @BindView(R.id.iv_appointment_color)
    ImageView ivAppointmentType;
    @BindView(R.id.group_buttons)
    android.support.constraint.Group gButtons;

    @Inject
    DoctorPendingAppointmentPresenter presenter;
    @Inject
    PreferencesManager preferencesManager;


    private DoctorAppointmentModel model;
    private HomeActivity activity;
    private RequestInterface listener;

    public static PendingAppointmentDialog newInstance(String data, RequestInterface listener) {
        Bundle args = new Bundle();
        args.putString("data", data);
        PendingAppointmentDialog dialog = new PendingAppointmentDialog();
        dialog.listener = listener;
        dialog.setCancelable(false);
        dialog.setArguments(args);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_quote, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildInjection();
    }


    private void buildInjection() {
        DaggerPendingAppointmentDialogComponent.builder()
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
        tvAppointmentType.setText(model.getType());
        tvAppointmentReason.setText(model.getReason());
        ivAppointmentType.setImageResource(setImageAppointmentType());
    }

    private int setImageAppointmentType() {
        switch (model.getType()) {
            case "Emergencia":
                return R.drawable.emergency;
            case "Urgencia":
                return R.drawable.urgency;
            case "Consulta":
                return R.drawable.simple_appointment;
            case "Baño":
                return R.drawable.barber_shower_appointment;
            case "Otros":
                return R.drawable.other_appointment;
            default:
                return R.drawable.emergency;

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

    @OnClick(R.id.img_dismiss)
    public void dismissImg() {
        dismiss();
    }

    @OnClick({R.id.tv_confirm, R.id.iv_confirm})
    public void confirm() {
        presenter.sendRequest();
    }

    @Override
    public String getApiToken() {
        return DoctorModel.toModel(preferencesManager.getDoctorCurrentUser()).getApi_token();
    }

    @Override
    public int getAppointmentId() {
        return model.getId();
    }

    @Override
    public void successRequest() {
        listener.successPendingRequest(getAppointmentId());
        dismiss();
    }

    @Override
    public void initUI() {
        if (getArguments() != null) {
            activity = (HomeActivity) getActivity();
            String data = getArguments().getString("data");
            model = DoctorAppointmentModel.toModel(data);
            presenter.setView(this);
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

    @OnClick(R.id.chat)
    public void chatClicked() {
        navigator.navigateToChatActivity(activity, model.getId());
    }

    @OnClick(R.id.phone)
    public void phoneClicked() {
        navigator.navigatePhoneCall(activity, model.getPetLover().getPhone());
    }

    public interface RequestInterface {

        void successPendingRequest(int id);
    }
}
