package com.alvardev.listento.views.addsong;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alvardev.listento.R;
import com.alvardev.listento.adapters.TracksAdapter;
import com.alvardev.listento.bases.BaseAppCompatActivity;
import com.alvardev.listento.models.Track;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class AddSongActivity extends BaseAppCompatActivity implements AddSongContract.View{

    private static final String TAG = "AddSongAct";

    @BindView(R.id.rvi_tracks_result) protected RecyclerView rviTracksResult;

    private AddSongContract.Presenter mPresenter;
    private TracksAdapter mAdapter;
    private RealmResults<Track> tracks;
    private RealmChangeListener<RealmResults<Track>> realmListener = new RealmChangeListener<RealmResults<Track>>() {
        @Override
        public void onChange(RealmResults<Track> element) {
            mAdapter.notifyDataSetChanged();
            rviTracksResult.setVisibility(tracks.size() > 0 ? View.VISIBLE : View.GONE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);
        ButterKnife.bind(this);

        mPresenter = new AddSongPresenter(this, this);
        setRecyclerView();
    }

    @Override
    public void setPresenter(AddSongContract.Presenter presenter) {
        //this method is useful when use fragments
        mPresenter = presenter;
    }

    @Override
    public void onLoading(boolean active) {

    }

    @OnClick(R.id.ibu_search)
    protected void onSearch(){
        mPresenter.searchTracksOnSpotify("In the end");
    }

    private void setRecyclerView(){
        rviTracksResult.setHasFixedSize(true);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(AddSongActivity.this);
        rviTracksResult.setLayoutManager(mLayoutManager);

        Realm realm = Realm.getDefaultInstance();
        tracks = realm.where(Track.class).findAll();
        mAdapter = new TracksAdapter(tracks, AddSongActivity.this);
        mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "track clicked");
            }
        });

        rviTracksResult.setAdapter(mAdapter);
        rviTracksResult.setItemAnimator(new DefaultItemAnimator());
        tracks.addChangeListener(realmListener);
    }

}
