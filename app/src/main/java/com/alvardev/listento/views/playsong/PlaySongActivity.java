package com.alvardev.listento.views.playsong;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.alvardev.listento.R;
import com.alvardev.listento.models.Song;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaySongActivity extends AppCompatActivity {

    private static final String TAG = "PlaySongAct";
    @BindView(R.id.ivi_image_sound) protected ImageView iviImageSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        ButterKnife.bind(this);

        Song song = (Song) getIntent().getBundleExtra("extra").getSerializable("song");
        setInfo(song);
        validateTransitions(iviImageSound);
    }

    private void setInfo(Song song){
        String urlCover = song.getUrlCover();
        if(urlCover != null && !urlCover.isEmpty()){
            Picasso.with(PlaySongActivity.this)
                    .load(urlCover)
                    .placeholder(R.drawable.lp_logo)
                    .error(R.drawable.lp_logo)
                    .into(iviImageSound);
        }

    }

    private void validateTransitions(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setTransitionName("view");
        }
    }
}
