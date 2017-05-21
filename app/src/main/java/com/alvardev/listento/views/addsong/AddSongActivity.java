package com.alvardev.listento.views.addsong;

import android.os.Bundle;

import com.alvardev.listento.R;
import com.alvardev.listento.bases.BaseAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddSongActivity extends BaseAppCompatActivity implements AddSongContract.View{

    private AddSongContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);
        ButterKnife.bind(this);

        mPresenter = new AddSongPresenter(this, this);
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
        mPresenter.searchSongs("In the end");
    }
}
