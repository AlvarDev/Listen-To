package com.alvardev.listento.views.addsong;

import android.content.Context;

import com.alvardev.listento.models.Song;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

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
    public void searchSongs() {
        String idSong = String.valueOf(Calendar.getInstance().getTimeInMillis());
        mDatabase.child("songs")
                .child(idSong)
                .setValue(new Song(idSong,
                        "https://i.scdn.co/image/66ff51342a9b250bf5b998fd0ec8e977671468bc",
                        "Heavy",
                        "Linkin Park"));
    }


}
