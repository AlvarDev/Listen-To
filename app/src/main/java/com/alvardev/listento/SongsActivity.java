package com.alvardev.listento;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.View;
import android.widget.ImageView;

import com.alvardev.listento.adapters.SongsAdapter;
import com.alvardev.listento.models.Song;
import com.alvardev.listento.utils.Const;

import java.util.List;

public class SongsActivity extends AppCompatActivity {

    private static final String TAG = "SongsAct";
    private Toolbar toolbar;
    private FloatingActionButton facAddSong;
    private RecyclerView rviSongs;

    private List<Song> songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        String name = getIntent().getExtras().getString("name");
        songs = Const.getSongs();
        findViews();
        setToolbar(name);
        setRecyclerView();
    }

    private void findViews(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rviSongs = (RecyclerView) findViewById(R.id.rvi_songs);
        facAddSong = (FloatingActionButton) findViewById(R.id.fac_add_song);
    }

    private void setRecyclerView(){
        rviSongs.setHasFixedSize(true);
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rviSongs.setLayoutManager(mLayoutManager);

        SongsAdapter songsAdapter = new SongsAdapter(songs, SongsActivity.this);
        songsAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = rviSongs.getChildLayoutPosition(view);
                ImageView img = (ImageView) view.findViewById(R.id.ivi_cover);
                Bundle bundle = new Bundle();
                bundle.putSerializable("song", songs.get(position));
                goToPlaySong(img, bundle);
            }
        });

        rviSongs.setAdapter(songsAdapter);
        rviSongs.setItemAnimator(new DefaultItemAnimator());
    }


    private void goToPlaySong(View view, Bundle bundle){
        Intent intent = new Intent(SongsActivity.this, PlaySongActivity.class);
        if (bundle != null) {
            intent.putExtra("extra", bundle);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(new Explode());
            ActivityOptions options = view != null ?
                    ActivityOptions.makeSceneTransitionAnimation(this, view, "view") :
                    ActivityOptions.makeSceneTransitionAnimation(this);
            startActivity(intent, options.toBundle());

        } else {
            startActivity(intent);
        }
    }

    private void setToolbar(String name){
        toolbar.setTitle(getString(R.string.s_hi) + name);
        setSupportActionBar(toolbar);
    }

}
