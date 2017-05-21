package com.alvardev.listento.views.playsong;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.alvardev.listento.R;
import com.alvardev.listento.models.Song;
import com.squareup.picasso.Picasso;

public class PlaySongActivity extends AppCompatActivity {

    private ImageView iviImageSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);

        Song song = (Song) getIntent().getBundleExtra("extra").getSerializable("song");
        findViews();
        setInfo(song);
        validateTransitions(iviImageSound);
    }

    private void findViews(){
        iviImageSound = (ImageView) findViewById(R.id.ivi_image_sound);
    }

    private void setInfo(Song song){
        String urlCover = song.getUrlCover() == null ?
                "" : song.getUrlCover();
        Picasso.with(PlaySongActivity.this)
                .load(urlCover)
                .placeholder(R.drawable.lp_logo)
                .error(R.drawable.lp_logo)
                .into(iviImageSound);
    }

    private void validateTransitions(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setTransitionName("view");
        }
    }
}
