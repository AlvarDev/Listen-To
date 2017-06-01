package com.alvardev.listento;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    /**
     * Não esqueca colocar as librerias no (app) build.gradle pra poder importar as clases
     *
     * Criar os objetos que vamos a utilizar
     * **/
    private View mainContent;
    private Button btnLogin;
    private TextInputEditText tieNameLogin;

    /**
     * onCreate é o primeiro metodo em ser chamado quando o Activity é criado
     * **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();
        setActions();
    }

    /**
     * Enlazar os objetos com os id colocados no Layout
     * **/
    private void findViews(){
        mainContent = findViewById(R.id.main_content);
        btnLogin = (Button) findViewById(R.id.btn_login);
        tieNameLogin = (TextInputEditText) findViewById(R.id.tie_name_login);
    }

    /**
     * Com os objetos já enlazados, damos acções
     * **/
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

        //Botão enter no teclado
        tieNameLogin.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    hideKeyboard();
                }
                return false;
            }
        });
    }

    /**
     * Cria um Intent (onde eu to, pra onde eu vou)
     * o Intent pode ter um dado pra levar de um Activity pra outro
     * **/
    private void goToSongs(String name){
        Intent intent = new Intent(LoginActivity.this, SongsActivity.class);
        intent.putExtra("name" , name);//mandamos este dado para o SongsActivity
        startActivity(intent);
        finish();
    }

    /**
     * Mostra uma mensagem
     * **/
    private void showSnack(){
        Snackbar.make(mainContent, getString(R.string.s_empty_name), Snackbar.LENGTH_LONG)
                .setAction("", null)
                .show();
    }

    /**
     * Oculta o teclado
     * **/
    private void hideKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }


}
