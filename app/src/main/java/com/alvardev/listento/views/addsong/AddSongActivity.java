package com.alvardev.listento.views.addsong;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.alvardev.listento.R;
import com.alvardev.listento.adapters.TracksAdapter;
import com.alvardev.listento.bases.BaseAppCompatActivity;
import com.alvardev.listento.models.Track;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class AddSongActivity extends BaseAppCompatActivity implements AddSongContract.View{

    private static final String TAG = "AddSongAct";

    @BindView(R.id.main_content) protected View mainContent;
    @BindView(R.id.rvi_tracks_result) protected RecyclerView rviTracksResult;
    @BindView(R.id.tvi_no_results) protected TextView tviNoResults;
    @BindView(R.id.til_search) protected TextInputLayout tilSearch;
    @BindView(R.id.tie_search) protected TextInputEditText tieSearch;
    @BindView(R.id.inc_loading) protected View incLoading;
    @BindView(R.id.toolbar) protected Toolbar toolbar;


    private AddSongContract.Presenter mPresenter;
    private TracksAdapter mAdapter;
    private RealmResults<Track> tracks;
    private RealmChangeListener<RealmResults<Track>> realmListener = new RealmChangeListener<RealmResults<Track>>() {
        @Override
        public void onChange(RealmResults<Track> element) {
            mAdapter.notifyDataSetChanged();
            rviTracksResult.setVisibility(tracks.size() > 0 ? View.VISIBLE : View.GONE);
            tviNoResults.setVisibility(tracks.size() > 0 ? View.GONE : View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);
        ButterKnife.bind(this);

        mPresenter = new AddSongPresenter(this, this);
        setToolbar();
        setRecyclerView();
    }

    @Override
    public void setPresenter(AddSongContract.Presenter presenter) {
        //this method is useful when use fragments
        mPresenter = presenter;
    }

    @Override
    public void onLoading(boolean active) {
        incLoading.setVisibility(active ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onShareSongSuccess() {
        showSnack(getString(R.string.s_share_song_success));
    }

    @Override
    public void onNoPreviewFound() {
        showSnack(getString(R.string.s_no_preview_found));
    }

    @OnClick(R.id.ibu_search)
    protected void onSearch(){
        searchSongs();
    }

    @OnEditorAction(R.id.tie_search)
    protected boolean search(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            searchSongs();
        }
        return false;
    }

    @OnClick(R.id.inc_loading)
    protected void doNothing(){

    }

    private void setToolbar(){
        toolbar.setTitle(getString(R.string.s_hi) + getCurrentUser());
        setSupportActionBar(toolbar);
    }

    private void setRecyclerView(){
        rviTracksResult.setHasFixedSize(true);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(AddSongActivity.this);
        rviTracksResult.setLayoutManager(mLayoutManager);

        Realm realm = Realm.getDefaultInstance();
        tracks = realm.where(Track.class).findAll();
        mAdapter = new TracksAdapter(tracks, AddSongActivity.this);
        mAdapter.setOnActionsListener(new TracksAdapter.TrackInterface() {
            @Override
            public void onPlaySong(int position) {
                Log.i(TAG, "onPlaySong: " + position);
                hideKeyboard();
                mPresenter.playSong(tracks.get(position).getPreviewUrl());
            }

            @Override
            public void onShareSong(int position) {
                hideKeyboard();
                Track track = tracks.get(position);
                mPresenter.addSongToFirebase(track.getId(),
                        track.getAlbum().getImages().get(0).getUrl(),
                        track.getArtists().get(0).getName(),
                        track.getName(),
                        getCurrentUser(),
                        track.getPreviewUrl());
            }
        });

        rviTracksResult.setAdapter(mAdapter);
        rviTracksResult.setItemAnimator(new DefaultItemAnimator());
        tracks.addChangeListener(realmListener);

        rviTracksResult.setVisibility(tracks.size() > 0 ? View.VISIBLE : View.GONE);
        tviNoResults.setVisibility(tracks.size() > 0 ? View.GONE : View.VISIBLE);
    }

    private void searchSongs(){
        tviNoResults.setVisibility(View.GONE);
        String query = tieSearch.getText().toString();
        if(!query.isEmpty()){
            mPresenter.searchTracksOnSpotify(query);
            hideKeyboard();
        }else{
            tilSearch.setError(getString(R.string.s_field_required));
        }
    }

    private void hideKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
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
