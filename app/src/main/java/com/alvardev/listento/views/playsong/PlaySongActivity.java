package com.alvardev.listento.views.playsong;

import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alvardev.listento.R;
import com.alvardev.listento.bases.BaseAppCompatActivity;
import com.alvardev.listento.models.Song;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlaySongActivity extends BaseAppCompatActivity implements PlaySongContract.View{

    private static final String TAG = "PlaySongAct";
    @BindView(R.id.main_content) protected View mainContent;
    @BindView(R.id.ivi_image_sound) protected ImageView iviImageSound;
    @BindView(R.id.ct_toolbar) protected CollapsingToolbarLayout ctToolbar;
    @BindView(R.id.progress_bar_bottom) protected View progressBarBottom;
    @BindView(R.id.fab_play_song) protected FloatingActionButton fabPlaySong;
    @BindView(R.id.tvi_name) protected TextView tviName;
    @BindView(R.id.tvi_user) protected TextView tviUser;

    private PlaySongContract.Presenter mPresenter;
    private Song song;
    private boolean isPlaying;
    private boolean isAllowToStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        ButterKnife.bind(this);

        song = (Song) getIntent().getBundleExtra("extra").getSerializable("song");
        mPresenter = new PlaySongPresenter(this, this);
        setToolBar();
        setInfo();
        validateTransitions(iviImageSound);
    }

    @OnClick(R.id.fab_play_song)
    protected void onPlaySong(){
        if(!isPlaying){
            isPlaying = true;
            mPresenter.playSong(song.getPreviewUrl());
        }else if(isAllowToStop){
            mPresenter.onStop();
            isPlaying = false;
        }

    }

    private void setToolBar() {
        ctToolbar.setTitle(song.getBand());
        ctToolbar.setCollapsedTitleTextColor(ContextCompat.getColor(PlaySongActivity.this, R.color.colorWhite));
        ctToolbar.setExpandedTitleColor(ContextCompat.getColor(PlaySongActivity.this, R.color.colorTransparent));
    }

    private void setInfo(){
        String urlCover = song.getUrlCover();
        if(urlCover != null && !urlCover.isEmpty()){
            Picasso.with(PlaySongActivity.this)
                    .load(urlCover)
                    .placeholder(R.drawable.logo_white)
                    .error(R.drawable.logo_white)
                    .into(iviImageSound);
        }

        tviName.setText(song.getName());
        tviUser.setText("(" + song.getUser() + ")");
    }

    private void validateTransitions(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setTransitionName("view");
        }
    }


    @Override
    public void setPresenter(PlaySongContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onLoading(boolean active) {
        progressBarBottom.setVisibility(active ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onNoPreviewFound() {
        showSnack(getString(R.string.s_no_preview_found));
    }

    @Override
    public void onSetIcon(int id) {
        fabPlaySong.setImageResource(id);
    }

    @Override
    public void setAllowToStop(boolean allowToStop) {
        isAllowToStop = allowToStop;
    }

    @Override
    public void setIsPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    private void showSnack(String message) {
        Snackbar.make(mainContent, message, Snackbar.LENGTH_SHORT)
                .setAction("", null)
                .show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mPresenter.onStop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }
}
