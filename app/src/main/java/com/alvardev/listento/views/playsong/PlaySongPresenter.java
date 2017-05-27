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

class PlaySongPresenter implements PlaySongContract.Presenter, MediaPlayer.OnPreparedListener {

    private static final String TAG = "PlaySongPres";
    private PlaySongContract.View mView;
    private Context context;
    private MediaPlayer mMediaPlayer;

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
            mView.setAllowToStop(false);
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
        if(mMediaPlayer != null){
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
            }
            mView.onSetIcon(R.drawable.ic_play_arrow_black_24px);
        }
    }


    @Override
    public void onPrepared(MediaPlayer mp) {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
        }
        mMediaPlayer.start();
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mView.setIsPlaying(false);
                mView.onSetIcon(R.drawable.ic_play_arrow_black_24px);
            }
        });
        mView.onLoading(false);
        mView.onSetIcon(R.drawable.ic_stop_black_24px);
        mView.setAllowToStop(true);
    }


}
