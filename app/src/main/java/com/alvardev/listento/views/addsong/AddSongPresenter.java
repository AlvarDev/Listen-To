package com.alvardev.listento.views.addsong;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

import com.alvardev.listento.models.Song;
import com.alvardev.listento.models.Track;
import com.alvardev.listento.models.response.TracksResponse;
import com.alvardev.listento.rest.ApiClient;
import com.alvardev.listento.rest.ApiClientInterface;
import com.alvardev.listento.utils.Util;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alvardev on 21/05/17.
 * Presenter for Add Song
 */

class AddSongPresenter implements AddSongContract.Presenter, MediaPlayer.OnPreparedListener{

    private static final String TAG = "AddSongPres";
    private AddSongContract.View mView;
    private Context context;
    private MediaPlayer mMediaPlayer;

    private DatabaseReference mDatabase;

    AddSongPresenter(AddSongContract.View mView, Context context) {
        this.mView = mView;
        this.context = context;
        mView.setPresenter(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mMediaPlayer = new MediaPlayer();
    }

    @Override
    public void start() {

    }

    @Override
    public void addSongToFirebase(String id, String urlCover, String band, String name, String user, String previewUrl) {
        mDatabase.child("songs")
                .child(id)
                .setValue(new Song(id, urlCover, band, name, user, previewUrl));
        mView.onShareSongSuccess();
    }

    @Override
    public void searchTracksOnSpotify(String q) {
        String type = "track";
        q = q.trim().replace(" ", "+");

        mView.onLoading(true);
        ApiClientInterface apiService = ApiClient.getClient().create(ApiClientInterface.class);
        Call<TracksResponse> call = apiService.gettracksFromSpotify(q, type);
        call.enqueue(new Callback<TracksResponse>() {
            @Override
            public void onResponse(Call<TracksResponse> call, Response<TracksResponse> response) {
                if(response.body() != null && response.body().getTracks().getItems().size() > 0){
                    cleanRealm();
                    saveOnRealm(response.body().getTracks().getItems());
                }else{
                    String message = ApiClient.manageResponseErrors(response, context);
                    Log.i(TAG, "code obtained " + response.code());
                    Log.i(TAG, message);
                    cleanRealm();
                    mView.onLoading(false);
                }

            }

            @Override
            public void onFailure(Call<TracksResponse> call, Throwable t) {
                Log.i(TAG, Util.manageThrowable(t));
                mView.onLoading(false);
            }
        });

    }

    @Override
    public void playSong(String urlPreview) {
        if(urlPreview != null && !urlPreview.isEmpty()){
            mView.onLoading(true);
            resetMediaPlayer();
            try {
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mMediaPlayer.setDataSource(urlPreview);
                mMediaPlayer.setOnPreparedListener(this);
                mMediaPlayer.prepareAsync(); // prepare async to not block main thread
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            mView.onNoPreviewFound();
        }
    }

    @Override
    public void onStop() {
        if(mMediaPlayer.isPlaying()){
            mMediaPlayer.stop();
        }
    }

    private void cleanRealm(){
        Realm mRealm = Realm.getDefaultInstance();
        final RealmResults<Track> tracksToDelete = mRealm.where(Track.class).findAll();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                tracksToDelete.deleteAllFromRealm();
            }
        });
    }

    private void resetMediaPlayer(){
        if(mMediaPlayer != null){
            if(mMediaPlayer.isPlaying()){
                mMediaPlayer.stop();
            }
            mMediaPlayer = new MediaPlayer();
        }
    }

    private void saveOnRealm(List<Track> trackList){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(trackList);
        realm.commitTransaction();
        mView.onLoading(false);
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        if(mMediaPlayer.isPlaying()){
            mMediaPlayer.stop();
        }
        mMediaPlayer.start();
        mView.onLoading(false);
    }


}
