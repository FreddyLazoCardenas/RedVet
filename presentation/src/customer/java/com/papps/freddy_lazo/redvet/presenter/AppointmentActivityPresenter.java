package com.papps.freddy_lazo.redvet.presenter;

import android.text.TextUtils;

import com.papps.freddy_lazo.domain.interactor.CreateAppointmentUseCase;
import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.model.CreateAppointment;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.AppointmentActivityView;
import com.papps.freddy_lazo.redvet.model.mapper.CreateAppointmentModelMapper;

import javax.inject.Inject;


public class AppointmentActivityPresenter implements Presenter<AppointmentActivityView> {

    private final CreateAppointmentUseCase createAppointmentUseCase;
    private AppointmentActivityView view;

    @Inject
    AppointmentActivityPresenter(CreateAppointmentUseCase createAppointmentUseCase) {
        this.createAppointmentUseCase = createAppointmentUseCase;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        createAppointmentUseCase.unsubscribe();
    }

    public void validateData() {
        if (!validatePetSelected(view.petByPetLoverId()))
            return;
        if (!validateDate(view.getDate()))
            return;
        if (!validateTime(view.getTime()))
            return;
        if (!validateType(view.getType()))
            return;
        if (!validateReason(view.getReason()))
            return;
        sendRequest();
    }

    private boolean validateReason(String reason) {
        if (TextUtils.isEmpty(reason)) {
            view.showErrorMessage(view.context().getString(R.string.add_reason_data));
            return false;
        } else
            return true;
    }

    private boolean validateType(String type) {
        if (TextUtils.isEmpty(type)) {
            view.showErrorMessage(view.context().getString(R.string.add_type_data));
            return false;
        } else
            return true;
    }

    private boolean validateTime(String time) {
        if (TextUtils.isEmpty(time)) {
            view.showErrorMessage(view.context().getString(R.string.add_time_data));
            return false;
        } else
            return true;
    }

    private boolean validateDate(String date) {
        if (TextUtils.isEmpty(date)) {
            view.showErrorMessage(view.context().getString(R.string.add_date_data));
            return false;
        } else
            return true;
    }

    private boolean validatePetSelected(Integer petByPetLoverId) {
        if (petByPetLoverId == null) {
            view.showErrorMessage(view.context().getString(R.string.add_pets_data));
            return false;
        } else return true;
    }

    private void sendRequest() {
        createAppointmentUseCase.bindParams(view.getApiToken(), view.doctorId(), view.petByPetLoverId(), view.getDate(), view.getTime(), view.getType(), view.getReason());
        createAppointmentUseCase.execute(new CreateAppointmentObservable());
    }

    @Override
    public void setView(AppointmentActivityView view) {
        this.view = view;
    }

    private class CreateAppointmentObservable extends DefaultObserver<CreateAppointment> {

        @Override
        protected void onStart() {
            super.onStart();
        }

        @Override
        public void onNext(CreateAppointment appointment) {
            super.onNext(appointment);
            view.successResponse(CreateAppointmentModelMapper.transform(appointment));
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            view.showErrorMessage(e.getMessage());
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }
}
