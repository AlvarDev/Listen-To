package com.alvardev.listento;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PlaySongActivity extends AppCompatActivity {

    private ImageView tviImageSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        findViews();
        validateTransitions(tviImageSound);
    }

    private void findViews(){
        tviImageSound = (ImageView) findViewById(R.id.tvi_image_sound);
    }

    private void validateTransitions(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setTransitionName("view");
        }
    }
}
