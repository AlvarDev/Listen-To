package com.alvardev.listento;

import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.alvardev.listento.models.Song;
import com.squareup.picasso.Picasso;

public class PlaySongActivity extends AppCompatActivity {

    /**
     * Não esqueca colocar as librerias no (app) build.gradle pra poder importar as clases
     *
     * Criar os objetos que vamos a utilizar
     * **/
    protected CollapsingToolbarLayout ctToolbar;
    private ImageView iviImageSound;

    /**
     * onCreate é o primeiro metodo em ser chamado quando o Activity é criado
     * **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);

        Song song = (Song) getIntent().getBundleExtra("extra").getSerializable("song");//song foi enviado pelo SongsActivity
        findViews();
        setInfo(song);
        validateTransitions(iviImageSound);
    }

    /**
     * Enlazar os objetos com os id colocados no Layout
     * **/
    private void findViews(){
        ctToolbar = (CollapsingToolbarLayout) findViewById(R.id.ct_toolbar);
        iviImageSound = (ImageView) findViewById(R.id.ivi_image_sound);
    }

    /**
     * Colocamos os dados recebidos
     * **/
    private void setInfo(Song song){
        ctToolbar.setTitle(song.getBand());
        ctToolbar.setCollapsedTitleTextColor(ContextCompat.getColor(PlaySongActivity.this, R.color.colorWhite));
        ctToolbar.setExpandedTitleColor(ContextCompat.getColor(PlaySongActivity.this, R.color.colorTransparent));

        String urlCover = song.getUrlCover() == null ?
                "" : song.getUrlCover();

        Picasso.with(PlaySongActivity.this)
                .load(urlCover)
                .placeholder(R.drawable.lp_logo)
                .error(R.drawable.lp_logo)
                .into(iviImageSound);
    }

    /**
     * Valida se o sistema operacional soporta Material Design e coloca o view que foi enviado pelo SonsActivity
     * **/
    private void validateTransitions(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setTransitionName("view");
        }
    }
}
