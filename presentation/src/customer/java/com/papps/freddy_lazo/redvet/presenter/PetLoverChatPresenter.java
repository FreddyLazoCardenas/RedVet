package com.papps.freddy_lazo.redvet.presenter;

import android.text.TextUtils;

import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.interactor.PetLoverChatList;
import com.papps.freddy_lazo.domain.interactor.PetLoverSendMessage;
import com.papps.freddy_lazo.domain.model.RedVetMessage;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.ChatActivityView;
import com.papps.freddy_lazo.redvet.model.mapper.RedVetMessageModelMapper;

import java.util.List;

import javax.inject.Inject;


public class PetLoverChatPresenter implements Presenter<ChatActivityView> {

    private final PetLoverChatList petLoverChatList;
    private final PetLoverSendMessage petLoverSendMessage;
    private ChatActivityView view;

    @Inject
    PetLoverChatPresenter(PetLoverChatList petLoverChatList, PetLoverSendMessage petLoverSendMessage) {
        this.petLoverChatList = petLoverChatList;
        this.petLoverSendMessage = petLoverSendMessage;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    public void getMessages() {
        petLoverChatList.bindParams(view.getApiToken(), view.getAppointmentId());
        petLoverChatList.execute(new MessagesObservable());
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
        petLoverSendMessage.bindParams(view.getApiToken(), view.getAppointmentId(), view.getMessage());
        petLoverSendMessage.execute(new SendMessageObservable());
    }

    @Override
    public void destroy() {
        petLoverSendMessage.unsubscribe();
        petLoverChatList.unsubscribe();
    }

    @Override
    public void setView(ChatActivityView view) {
        this.view = view;
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
}
