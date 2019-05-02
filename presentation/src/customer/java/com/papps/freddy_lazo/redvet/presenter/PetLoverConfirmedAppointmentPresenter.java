package com.papps.freddy_lazo.redvet.presenter;

import android.text.TextUtils;

import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.interactor.PetLoverAppointmentCancel;
import com.papps.freddy_lazo.domain.model.RedVetAppointment;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.PetLoverConfirmedView;

import javax.inject.Inject;

public class PetLoverConfirmedAppointmentPresenter implements Presenter<PetLoverConfirmedView> {

    private final PetLoverAppointmentCancel petLoverAppointmentCancel;
    private PetLoverConfirmedView view;

    @Inject
    public PetLoverConfirmedAppointmentPresenter(PetLoverAppointmentCancel petLoverAppointmentCancel) {
        this.petLoverAppointmentCancel = petLoverAppointmentCancel;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        petLoverAppointmentCancel.unsubscribe();
    }

    @Override
    public void setView(PetLoverConfirmedView view) {
        this.view = view;
    }

    public void sendRequest() {
        petLoverAppointmentCancel.bindParams(view.getApiToken(), view.getAppointmentId(), view.getReason());
        petLoverAppointmentCancel.execute(new PetLoverConfirmObservable());
    }

    public void validate() {
        if (!validateReason(view.getReason()))
            return;
        sendRequest();
    }

    private boolean validateReason(String reason) {
        if (TextUtils.isEmpty(reason)) {
            view.showReasonError(view.context().getString(R.string.text_required_field));
            return false;
        }
        view.hideReasonError();
        return true;
    }

    private class PetLoverConfirmObservable extends DefaultObserver<RedVetAppointment> {

        @Override
        protected void onStart() {
            super.onStart();
        }

        @Override
        public void onNext(RedVetAppointment redVetAppointment) {
            super.onNext(redVetAppointment);
            view.showErrorMessage("Éxito");
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            view.showErrorMessage(e.getMessage());
        }

        @Override
        public void onComplete() {
            super.onComplete();
            view.successRequest();
        }
    }
}
