package com.alvardev.listento.views.login;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.alvardev.listento.R;
import com.alvardev.listento.bases.BaseAppCompatActivity;
import com.alvardev.listento.views.songs.SongsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;

public class LoginActivity extends BaseAppCompatActivity {

    @BindView(R.id.main_content) protected View mainContent;
    @BindView(R.id.tie_name_login) protected TextInputEditText tieNameLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    protected void onClickLogin(){
        login();
    }

    @OnEditorAction(R.id.tie_name_login)
    protected boolean onLogin(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            hideKeyboard();
        }
        return false;
    }

    private void login(){
        String name = tieNameLogin.getText().toString();
        if(!name.isEmpty()){
            goToSongs(name);
        }else{
            showSnack();
        }
    }

    private void hideKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    private void goToSongs(String name){
        saveCurrentUser(name);
        startActivity(new Intent(LoginActivity.this, SongsActivity.class));
        finish();
    }

    private void showSnack(){
        Snackbar.make(mainContent, getString(R.string.s_empty_name), Snackbar.LENGTH_LONG)
                .setAction("", null)
                .show();
    }


}
