package com.alvardev.listento;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SongsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private View incSong1;
    private View incSong2;
    private View incSong3;
    private View incSong4;
    private View incSong5;
    private View incSong6;
    private ImageView iviLogo;
    private FloatingActionButton facAddSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        String name = getIntent().getExtras().getString("name");
        findViews();
        setActions();
        setToolbar(name);
    }

    private void findViews(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        incSong1 = findViewById(R.id.inc_song_1);
        incSong2 = findViewById(R.id.inc_song_2);
        incSong3 = findViewById(R.id.inc_song_3);
        incSong4 = findViewById(R.id.inc_song_4);
        incSong5 = findViewById(R.id.inc_song_5);
        incSong6 = findViewById(R.id.inc_song_6);

        iviLogo = (ImageView) findViewById(R.id.ivi_logo);
        facAddSong = (FloatingActionButton) findViewById(R.id.fac_add_song);
    }

    private void setActions(){
        incSong1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPlaySong(iviLogo);
            }
        });

        incSong2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPlaySong(null);
            }
        });

        incSong3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPlaySong(null);
            }
        });

        incSong4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPlaySong(null);
            }
        });

        incSong5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPlaySong(null);
            }
        });

        incSong6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPlaySong(null);
            }
        });

        facAddSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SongsActivity.this, AddSongActivity.class));
            }
        });
    }

    private void goToPlaySong(View view){
        Intent intent = new Intent(SongsActivity.this, PlaySongActivity.class);

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
