package com.papps.freddy_lazo.redvet.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.papps.freddy_lazo.redvet.GlideApp;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.model.NewsModel;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

public class NewsDetailActivity extends BaseActivity {

    @BindView(R.id.iv_image)
    ImageView ivNew;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;

    public static Intent getCallingIntent(BaseActivity activity, NewsModel data) {
        return new Intent(activity, NewsDetailActivity.class).putExtra("data", data);
    }

    private NewsModel model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        injectView(this);
        initUI();
    }

    @Override
    public void initUI() {
        getExtras();
        fillUi();
    }

    private void fillUi() {
        tvTitle.setText(model.getTitle());
        tvContent.setText(model.getContent());
        displayPhoto();
    }

    private void getExtras() {
        model = Objects.requireNonNull(getIntent().getExtras()).getParcelable("data");
    }

    private void displayPhoto() {
        GlideApp.with(this)
                .asBitmap()
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .placeholder(R.drawable.ic_placeholder)
                .load(model.getPhoto_url())
                .into(ivNew);
    }

    @OnClick(R.id.img_header)
    public void ivClick() {
        finish();
    }
}
