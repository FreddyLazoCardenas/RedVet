package com.papps.freddy_lazo.redvet.presenter;

import android.text.TextUtils;

import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.interactor.DoctorChatList;
import com.papps.freddy_lazo.domain.interactor.DoctorSendMessage;
import com.papps.freddy_lazo.domain.model.RedVetMessage;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.ChatActivityView;
import com.papps.freddy_lazo.redvet.model.mapper.RedVetMessageModelMapper;

import java.util.List;

import javax.inject.Inject;

public class DoctorChatPresenter implements Presenter<ChatActivityView> {
    private final DoctorSendMessage doctorSendMessage;
    private final DoctorChatList doctorChatList;
    private ChatActivityView view;

    @Inject
    public DoctorChatPresenter(DoctorChatList doctorChatList, DoctorSendMessage doctorSendMessage) {
        this.doctorChatList = doctorChatList;
        this.doctorSendMessage = doctorSendMessage;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        doctorChatList.unsubscribe();
        doctorSendMessage.unsubscribe();
    }

    @Override
    public void setView(ChatActivityView view) {
        this.view = view;
    }

    public void getMessages() {
        doctorChatList.bindParams(view.getApiToken(), view.getAppointmentId());
        doctorChatList.execute(new MessagesObservable());
    }

    public void validate() {
        if (!validateMessage(view.getMessage()))
            return;
        sendMessage();
    }

    private boolean validateMessage(String message) {
        if (TextUtils.isEmpty(message)) {
            view.showErrorMessage(view.context().getString(R.string.add_message_data));
            return false;
        }
        return true;
    }

    private void sendMessage() {
        doctorSendMessage.bindParams(view.getApiToken(), view.getAppointmentId(), view.getMessage());
        doctorSendMessage.execute(new SendMessageObservable());
    }

    private class SendMessageObservable extends DefaultObserver<RedVetMessage> {

        @Override
        protected void onStart() {
            super.onStart();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            view.showErrorMessage(e.getMessage());
        }

        @Override
        public void onNext(RedVetMessage redVetMessage) {
            super.onNext(redVetMessage);
            view.showErrorMessage("Exito");
            view.successSendMessage(RedVetMessageModelMapper.transform(redVetMessage));
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }


    private class MessagesObservable extends DefaultObserver<List<RedVetMessage>> {

        @Override
        protected void onStart() {
            super.onStart();
        }

        @Override
        public void onNext(List<RedVetMessage> redVetMessages) {
            super.onNext(redVetMessages);
            view.showErrorMessage("Exito");
            view.successRequest(RedVetMessageModelMapper.transform(redVetMessages));
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
