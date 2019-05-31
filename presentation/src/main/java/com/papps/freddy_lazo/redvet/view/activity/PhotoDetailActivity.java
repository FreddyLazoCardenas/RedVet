package com.papps.freddy_lazo.redvet.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.papps.freddy_lazo.redvet.GlideApp;
import com.papps.freddy_lazo.redvet.R;

import butterknife.BindView;

public class PhotoDetailActivity extends BaseActivity {

    private String url;


    @BindView(R.id.iv_photo)
    ImageView ivPhoto;

    public static Intent getCallingIntent(BaseActivity activity, String url) {
        return new Intent(activity, PhotoDetailActivity.class).putExtra("data", url);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);
        injectView(this);
        url = getIntent().getStringExtra("data");
        initUI();
    }

    @Override
    public void initUI() {
        displayPhoto(url);
    }

    public void displayPhoto(String photoUrl) {
        GlideApp.with(this)
                .asBitmap()
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .placeholder(R.drawable.ic_placeholder)
                .load(photoUrl)
                .into(ivPhoto);
    }
}
