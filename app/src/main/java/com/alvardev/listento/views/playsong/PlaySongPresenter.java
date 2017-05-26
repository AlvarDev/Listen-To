package com.alvardev.listento.views.playsong;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import com.alvardev.listento.R;

import java.io.IOException;

/**
 * Created by alvarom on 26/05/2017.
 * Presenter for Play song
 */

public class PlaySongPresenter implements PlaySongContract.Presenter, MediaPlayer.OnPreparedListener {

    private static final String TAG = "PlaySongPres";
    private PlaySongContract.View mView;
    private Context context;
    private MediaPlayer mMediaPlayer;
    private boolean loadingOrPlaying;

    PlaySongPresenter(PlaySongContract.View mView, Context context) {
        this.mView = mView;
        this.context = context;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void playSong(String urlPreview) {
        if (urlPreview != null && !urlPreview.isEmpty()) {
            mView.onLoading(true);
            loadingOrPlaying = true;
            try {
                mMediaPlayer = new MediaPlayer();
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mMediaPlayer.setDataSource(urlPreview);
                mMediaPlayer.setOnPreparedListener(this);
                mMediaPlayer.prepareAsync(); // prepare async to not block main thread
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            mView.onNoPreviewFound();
        }
    }

    @Override
    public void onStop() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
        }
        loadingOrPlaying = false;
        mView.onSetIcon(R.drawable.ic_play_arrow_black_24px);
    }

    @Override
    public boolean isLoadingOrPlaying() {
        return loadingOrPlaying;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
        }
        mMediaPlayer.start();
        mView.onLoading(false);
        loadingOrPlaying = true;
        mView.onSetIcon(R.drawable.ic_stop_black_24px);
    }


}
