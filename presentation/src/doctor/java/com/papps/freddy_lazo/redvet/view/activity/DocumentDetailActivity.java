package com.papps.freddy_lazo.redvet.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.papps.freddy_lazo.redvet.GlideApp;
import com.papps.freddy_lazo.redvet.R;

import butterknife.BindView;

public class DocumentDetailActivity extends BaseActivity {

    @BindView(R.id.iv_appointment)
    ImageView ivAppointment;

    private String url;

    public static Intent getCallingIntent(BaseActivity activity, String photo_url) {
        return new Intent(activity, DocumentDetailActivity.class).putExtra("photoUrl", photo_url);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_detail_layout);
        injectView(this);
        initUI();
    }

    @Override
    public void initUI() {
        url = getIntent().getStringExtra("photoUrl");
        seeDetailLogic(url);
    }

    private void seeDetailLogic(String url) {
        if (url.endsWith("jpeg")) {
            loadImage(url);
            return;
        }
        if (url.endsWith("pdf")) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
            return;
        }
        if (url.endsWith("msword")) {

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        }
    }

    private void loadImage(String photo) {
        GlideApp.with(this)
                .asBitmap()
                .dontAnimate()
                .placeholder(R.drawable.ic_placeholder)
                .load(photo != null ? photo : "")
                .into(ivAppointment);
    }
}
