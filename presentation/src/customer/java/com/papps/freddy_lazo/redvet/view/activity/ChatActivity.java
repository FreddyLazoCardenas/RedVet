package com.papps.freddy_lazo.redvet.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.papps.freddy_lazo.data.sharedPreferences.PreferencesManager;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.ChatActivityView;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerRedVetChatComponent;
import com.papps.freddy_lazo.redvet.model.PetLoverModel;
import com.papps.freddy_lazo.redvet.model.RedVetMessageModel;
import com.papps.freddy_lazo.redvet.presenter.PetLoverChatPresenter;
import com.papps.freddy_lazo.redvet.view.adapter.MessagesAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ChatActivity extends BaseActivity implements ChatActivityView {

    @BindView(R.id.rv_chat)
    RecyclerView rvChat;
    @BindView(R.id.et_chat)
    EditText etChat;
    @Inject
    PetLoverChatPresenter presenter;
    @Inject
    PreferencesManager preferencesManager;
    @Inject
    MessagesAdapter adapter;

    private int appointmentId;
    private boolean fromPush;

    public static Intent getCallingIntent(BaseActivity activity, int appointmentId) {
        return new Intent(activity, ChatActivity.class).putExtra("data", appointmentId);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        injectView(this);
        buildInjection();
        appointmentId = getIntent().getIntExtra("data", -1);
        fromPush = getIntent().getBooleanExtra("from_push", false);
        initUI();
    }

    private void buildInjection() {
        DaggerRedVetChatComponent.builder().applicationComponent(getApplicationComponent()).build().inject(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        presenter.getMessages();
    }

    @OnClick(R.id.img_dismiss)
    public void imgDismiss() {
        finish();
    }

    @Override
    public void finish() {
        if (fromPush) {
            navigator.navigateToHomeActivity(this);
        } else {
            super.finish();
        }
    }

    @Override
    public String getApiToken() {
        return PetLoverModel.toModel(preferencesManager.getPetLoverCurrentUser()).getApi_token();
    }

    @Override
    public int getAppointmentId() {
        return appointmentId;
    }

    @Override
    public String getMessage() {
        return etChat.getText().toString();
    }

    @Override
    public void successRequest(List<RedVetMessageModel> data) {
        adapter.bindList(data);
    }

    @Override
    public void successSendMessage(RedVetMessageModel data) {
        etChat.getText().clear();
        adapter.addMessage(data);
    }

    @Override
    public void initUI() {
        presenter.setView(this);
        presenter.getMessages();
        setUpRv();
    }

    private void setUpRv() {
        rvChat.setLayoutManager(new LinearLayoutManager(this));
        rvChat.setAdapter(adapter);
    }

    @OnClick(R.id.send_btn)
    public void sendMessageClicked() {
        presenter.validate();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }
}
