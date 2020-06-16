package com.github.anovosvit.covidapp.global;

import com.github.anovosvit.covidapp.model.GlobalInfo;

public class GlobalPresenter implements GlobalContract.Presenter, GlobalContract.Model.OnFinishedListener {

    GlobalContract.Model model;
    GlobalContract.View view;

    public GlobalPresenter(GlobalContract.View view) {
        this.model = new GlobalModel();
        this.view = view;
    }

    @Override
    public void loadGlobalInfo() {
        if (view != null) {
            view.showProgress();
        }
        model.getGlobalInfo(this);
    }

    @Override
    public void dropView() {
        this.view = null;
    }

    @Override
    public void onFinished(GlobalInfo globalInfo) {
        view.setDataToViews(globalInfo);
        if (view != null) {
            view.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        view.onResponseFailure(t);
        if (view != null) {
            view.hideProgress();
        }
    }
}
