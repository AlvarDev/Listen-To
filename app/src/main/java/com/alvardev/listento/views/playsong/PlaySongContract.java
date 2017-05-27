package com.alvardev.listento.views.playsong;

import com.alvardev.listento.bases.BasePresenter;
import com.alvardev.listento.bases.BaseView;

/**
 * Created by alvarom on 26/05/2017.
 * Contract for Play song
 */

interface PlaySongContract {

    interface View extends BaseView<Presenter> {

        void onLoading(boolean active);

        void onNoPreviewFound();

        void onSetIcon(int id);

        void setAllowToStop(boolean allowToStop);

        void setIsPlaying(boolean isPlaying);

    }

    interface Presenter extends BasePresenter {

        void playSong(String urlPreview);

        void onStop();

    }

}
