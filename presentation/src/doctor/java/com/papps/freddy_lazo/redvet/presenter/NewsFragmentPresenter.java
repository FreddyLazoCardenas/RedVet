package com.papps.freddy_lazo.redvet.presenter;

import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.interactor.PetLoverNews;
import com.papps.freddy_lazo.domain.model.News;
import com.papps.freddy_lazo.redvet.interfaces.NewsFragmentView;
import com.papps.freddy_lazo.redvet.interfaces.RegisterFragmentView;
import com.papps.freddy_lazo.redvet.model.mapper.NewsModelMapper;

import java.util.List;

import javax.inject.Inject;

public class NewsFragmentPresenter implements Presenter<NewsFragmentView> {

    private final PetLoverNews petLoverNews;
    private NewsFragmentView view;

    @Inject
    public NewsFragmentPresenter(PetLoverNews petLoverNews) {
        this.petLoverNews = petLoverNews;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        petLoverNews.unsubscribe();
    }

    public void getNews() {
        petLoverNews.bindParams("doctor");
        petLoverNews.execute(new NewsObservable());
    }

    @Override
    public void setView(NewsFragmentView view) {
        this.view = view;
    }

    private class NewsObservable extends DefaultObserver<List<News>> {

        @Override
        protected void onStart() {
            super.onStart();
        }

        @Override
        public void onNext(List<News> news) {
            super.onNext(news);
            view.listData(NewsModelMapper.transform(news));
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }
}
