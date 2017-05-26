package com.alvardev.listento.views.songs;

import android.content.Context;
import android.util.Log;

import com.alvardev.listento.models.Song;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alvardev on 21/05/17.
 * Presenter for Songs
 */

class SongsPresenter implements SongsContract.Presenter{

    private static final String TAG = "SongPres";
    private SongsContract.View mView;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("songs");

    SongsPresenter(SongsContract.View mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void getSongs() {
        mView.onLoading(true);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Song> songs = new ArrayList<>();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    songs.add(snapshot.getValue(Song.class));
                }
                mView.onLoading(false);
                mView.onSongsObtained(songs);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
                mView.onLoading(false);
            }
        });
    }

    @Override
    public void start() {

    }

}
