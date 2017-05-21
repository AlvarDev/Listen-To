package com.alvardev.listento.views.addsong;

import android.content.Context;
import android.util.Log;

import com.alvardev.listento.models.Song;
import com.alvardev.listento.models.Track;
import com.alvardev.listento.models.response.TracksResponse;
import com.alvardev.listento.rest.ApiClient;
import com.alvardev.listento.rest.ApiClientInterface;
import com.alvardev.listento.utils.Util;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
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

class AddSongPresenter implements AddSongContract.Presenter{

    private static final String TAG = "AddSongPres";
    private AddSongContract.View mView;
    private Context context;

    private DatabaseReference mDatabase;

    AddSongPresenter(AddSongContract.View mView, Context context) {
        this.mView = mView;
        this.context = context;
        mView.setPresenter(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void start() {

    }

    @Override
    public void addSongToFirebase() {
        String idSong = String.valueOf(Calendar.getInstance().getTimeInMillis());
        mDatabase.child("songs")
                .child(idSong)
                .setValue(new Song(idSong,
                        "https://i.scdn.co/image/66ff51342a9b250bf5b998fd0ec8e977671468bc",
                        "Heavy",
                        "Linkin Park"));
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

    private void saveOnRealm(List<Track> trackList){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(trackList);
        realm.commitTransaction();
    }


}
