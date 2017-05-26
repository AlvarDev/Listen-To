package com.alvardev.listento.views.songs;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alvardev.listento.bases.BaseAppCompatActivity;
import com.alvardev.listento.views.addsong.AddSongActivity;
import com.alvardev.listento.views.playsong.PlaySongActivity;
import com.alvardev.listento.R;
import com.alvardev.listento.adapters.SongsAdapter;
import com.alvardev.listento.models.Song;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SongsActivity extends BaseAppCompatActivity implements SongsContract.View{

    private static final String TAG = "SongsAct";

    @BindView(R.id.toolbar) protected Toolbar toolbar;
    @BindView(R.id.rvi_songs) protected RecyclerView rviSongs;
    @BindView(R.id.inc_loading) protected View incLoading;
    @BindView(R.id.tvi_no_results) protected TextView tviNoResults;


    private List<Song> songs;

    private SongsContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);
        ButterKnife.bind(this);
        songs = new ArrayList<>();
        mPresenter = new SongsPresenter(this);
        setToolbar();
        setRecyclerView();
        mPresenter.getSongs();
    }

    @Override
    public void onLoading(boolean active){
        incLoading.setVisibility(active ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onSongsObtained(List<Song> songs) {
        this.songs = songs;
        // setRecyclerView() is wrong,
        // must use other method to update content
        // (for this demo is ok but must be improve)
        setRecyclerView();
        rviSongs.setVisibility(songs.size() > 0 ? View.VISIBLE : View.GONE);
        tviNoResults.setVisibility(songs.size() > 0 ? View.GONE : View.VISIBLE);
        Log.i(TAG, "Total songs: " + this.songs.size());
    }

    @Override
    public void setPresenter(SongsContract.Presenter presenter) {
        // this method is useful when use fragments
        mPresenter = presenter;
    }

    @OnClick(R.id.fac_add_song)
    protected void onFacClicked(){
        explodeToActivity(SongsActivity.this, AddSongActivity.class);
    }

    private void setToolbar(){
        toolbar.setTitle(getString(R.string.s_hi) + getCurrentUser());
        setSupportActionBar(toolbar);
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
                // Using Serializable just for this demo
                // Better send an ID and search the object on Realm
                // (this is a Demo)
                bundle.putSerializable("song", songs.get(position));
                explodeToActivity(SongsActivity.this, PlaySongActivity.class, img, bundle);
            }
        });

        rviSongs.setAdapter(songsAdapter);
        rviSongs.setItemAnimator(new DefaultItemAnimator());
    }







}
