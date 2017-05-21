package com.alvardev.listento.views.login;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alvardev.listento.R;
import com.alvardev.listento.views.songs.SongsActivity;

public class LoginActivity extends AppCompatActivity {

    private View mainContent;
    private Button btnLogin;
    private TextInputEditText tieNameLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();
        setActions();
    }

    private void findViews(){
        mainContent = findViewById(R.id.main_content);
        btnLogin = (Button) findViewById(R.id.btn_login);
        tieNameLogin = (TextInputEditText) findViewById(R.id.tie_name_login);
    }

    private void setActions(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = tieNameLogin.getText().toString();
                if(!name.isEmpty()){
                    goToSongs(name);
                }else{
                    showSnack();
                }
            }
        });
    }

    private void goToSongs(String name){
        Intent intent = new Intent(LoginActivity.this, SongsActivity.class);
        intent.putExtra("name" , name);
        startActivity(intent);
        finish();
    }

    private void showSnack(){
        Snackbar.make(mainContent, getString(R.string.s_empty_name), Snackbar.LENGTH_LONG)
                .setAction("", null)
                .show();
    }


}
