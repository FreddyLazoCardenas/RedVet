package com.papps.freddy_lazo.redvet.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.NewsFragmentView;
import com.papps.freddy_lazo.redvet.interfaces.RegisterFragmentView;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerNewsFragmentComponent;
import com.papps.freddy_lazo.redvet.model.NewsModel;
import com.papps.freddy_lazo.redvet.presenter.NewsFragmentPresenter;
import com.papps.freddy_lazo.redvet.view.activity.HomeActivity;
import com.papps.freddy_lazo.redvet.view.adapter.NewsAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class NewsFragment extends BaseFragment implements NewsFragmentView, NewsAdapter.OnClickAdapter {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private HomeActivity activity;

    public static Fragment newInstance() {
        return new NewsFragment();
    }

    @Inject
    NewsFragmentPresenter presenter;

    @Inject
    NewsAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildInjection();
    }

    private void buildInjection() {
        DaggerNewsFragmentComponent.builder()
                .applicationComponent(getAndroidApplication().getApplicationComponent())
                .build().inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (HomeActivity) getActivity();
        initUI();
    }

    @Override
    public void initUI() {
        setUpRecycler();
        presenter.setView(this);
        presenter.getNews();
    }

    private void setUpRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(adapter);
        adapter.setView(this);
    }

    @Override
    public Context context() {
        return getActivity();
    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void showErrorNetworkMessage(String message) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public void listData(List<NewsModel> data) {
        adapter.bindList(data);
    }

    @Override
    public String getApiToken() {
        return activity.getModel().getApi_token();
    }

    @Override
    public void itemClicked(NewsModel model) {
        navigator.navigateToNewsDetailActivity(activity, model);
    }

    @Override
    public void showLoading() {
        activity.showLoading();
    }

    @Override
    public void hideLoading() {
        activity.hideLoading();
    }
}
