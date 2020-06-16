package com.github.anovosvit.covidapp.global;

import com.github.anovosvit.covidapp.model.GlobalInfo;

public class GlobalContract {

    interface Model {

        interface OnFinishedListener {
            void onFinished(GlobalInfo globalInfo);

            void onFailure(Throwable t);
        }

        void getGlobalInfo(OnFinishedListener onFinishedListener);
    }

    interface View {
        void showProgress();

        void hideProgress();

        void setDataToViews(GlobalInfo globalInfo);

        void onResponseFailure(Throwable throwable);
    }

    interface Presenter {
        void loadGlobalInfo();

        void dropView();
    }
}
