package com.alvardev.listento.views.songs;

import com.alvardev.listento.bases.BasePresenter;
import com.alvardev.listento.bases.BaseView;
import com.alvardev.listento.models.Song;

import java.util.List;

/**
 * Created by alvardev on 21/05/17.
 * Contract for Songs
 */

interface SongsContract {

    interface View extends BaseView<Presenter>{

        void onLoading(boolean active);

        void onSongsObtained(List<Song> songs);

    }

    interface Presenter extends BasePresenter{

        void getSongs();

    }

}
