package com.alvardev.listento.views.addsong;

import android.content.Context;
import android.util.Log;

import com.alvardev.listento.models.Song;
import com.alvardev.listento.models.response.TracksResponse;
import com.alvardev.listento.rest.ApiClient;
import com.alvardev.listento.rest.ApiClientInterface;
import com.alvardev.listento.utils.Util;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

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
    public void searchSongs(String q) {
        String type = "track";
        q = q.trim().replace(" ", "+");

        mView.onLoading(true);
        ApiClientInterface apiService = ApiClient.getClient().create(ApiClientInterface.class);
        Call<TracksResponse> call = apiService.getSongsFromSpotify(q, type);
        call.enqueue(new Callback<TracksResponse>() {
            @Override
            public void onResponse(Call<TracksResponse> call, Response<TracksResponse> response) {
                if(response.body() != null){
                    Log.i(TAG, response.body().toString());
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


}
