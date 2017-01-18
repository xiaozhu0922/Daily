package com.cins.daily.mvp.presenter.impl;

import com.cins.daily.listener.RequestCallBack;
import com.cins.daily.mvp.entity.NewsSummary;
import com.cins.daily.mvp.interactor.NewsListInteractor;
import com.cins.daily.mvp.interactor.impl.NewsListInteractorImpl;
import com.cins.daily.mvp.presenter.NewsListPresenter;
import com.cins.daily.mvp.view.NewsListView;

import java.util.List;

/**
 * Created by Eric on 2017/1/17.
 */

public class NewsListPresenterImpl extends BasePresenterImpl<NewsListView,List<NewsSummary>>
        implements NewsListPresenter,RequestCallBack<List<NewsSummary>>{

    private NewsListInteractor<List<NewsSummary>> mNewsListInteractor;
    private String mNewsType;
    private String mNewsId;
    private int mStartPage;

    /**
     * 新闻页首次加载完毕
     */
    private boolean mIsLoaded;

    public NewsListPresenterImpl(NewsListView newsListView, String newsType, String newsId) {
        mView = newsListView;
        mNewsType = newsType;
        mNewsId = newsId;
        mNewsListInteractor = new NewsListInteractorImpl();
    }

    @Override
    public void onCreate() {
        if (mView != null) {
            mNewsListInteractor.loadNews(this, mNewsType, mNewsType, mStartPage);
        }
    }

    @Override
    public void success(List<NewsSummary> items) {
        mIsLoaded = true;
        if (mView != null) {
            mView.setItems(items);
            mView.hideProgress();
        }
    }

    @Override
    public void onError(String errorMsg) {
        mView.showErrorMsg(errorMsg);
    }

    @Override
    public void onItemClicked(int position) {

    }

    @Override
    public void onDestroy() {
        mView = null;
    }
}
