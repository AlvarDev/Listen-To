package com.alvardev.listento.views.addsong;

import com.alvardev.listento.bases.BasePresenter;
import com.alvardev.listento.bases.BaseView;

/**
 * Created by alvardev on 21/05/17.
 * Contract for Add Song
 */

interface AddSongContract {

    interface View extends BaseView<Presenter> {

        void onLoading(boolean active);

        void onShareSongSuccess();

    }

    interface Presenter extends BasePresenter {

        void addSongToFirebase(String id, String urlCover, String band, String name, String user);

        void searchTracksOnSpotify(String q);

    }

}