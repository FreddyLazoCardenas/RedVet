package com.papps.freddy_lazo.redvet.presenter;

import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.interactor.PetLoverChatList;
import com.papps.freddy_lazo.domain.interactor.PetLoverSendMessage;
import com.papps.freddy_lazo.domain.model.RedVetMessage;
import com.papps.freddy_lazo.redvet.interfaces.ChatActivityView;

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

    public void getMessages(){
        petLoverChatList.bindParams(view.getApiToken(),view.getAppointmentId());
        petLoverChatList.execute(new MessagesObservable());
    }

    public void sendMessage(String message){
        petLoverSendMessage.bindParams(view.getApiToken(),view.getAppointmentId(),message);
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
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }
}
