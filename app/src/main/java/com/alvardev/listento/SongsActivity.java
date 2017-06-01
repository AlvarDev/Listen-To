package com.alvardev.listento;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.View;
import android.widget.ImageView;

import com.alvardev.listento.adapters.SongsAdapter;
import com.alvardev.listento.models.Song;
import com.alvardev.listento.utils.Const;

import java.util.List;

public class SongsActivity extends AppCompatActivity {

    /**
     * Não esqueca colocar as librerias no (app) build.gradle pra poder importar as clases
     *
     * Criar os objetos que vamos a utilizar
     * **/
    private static final String TAG = "SongsAct";
    private Toolbar toolbar;
    private RecyclerView rviSongs;

    private List<Song> songs;//Lista de musicas

    /**
     * onCreate é o primeiro metodo em ser chamado quando o Activity é criado
     * **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        String name = getIntent().getExtras().getString("name");//name foi enviado pelo LoginActivity
        songs = Const.getSongs(); //os dados são fixos, eles ficam no arquivo utils/Const
        findViews();
        setToolbar(name);
        setRecyclerView();
    }

    /**
     * Enlazar os objetos com os id colocados no Layout
     * **/
    private void findViews(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rviSongs = (RecyclerView) findViewById(R.id.rvi_songs);
    }

    /**
     * Colocamos o nome recebido no Toolbar
     * **/
    private void setToolbar(String name){
        toolbar.setTitle(getString(R.string.s_hi) + name);
        setSupportActionBar(toolbar);
    }

    /**
     * Neste metodo o importante é criar o Adapter e falar pra ele qual é a lista com os dados a mostrar.
     * Também é possivel indicar uma ação cada vez que o usuário escolhe um item
     * **/
    private void setRecyclerView(){
        rviSongs.setHasFixedSize(true);
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rviSongs.setLayoutManager(mLayoutManager);

        SongsAdapter songsAdapter = new SongsAdapter(songs, SongsActivity.this);//Criar o adapter e falar pra ele a lista de dados (songs)
        songsAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = rviSongs.getChildLayoutPosition(view); //obter a posição do item que foi escolhido (0, 1, 2... n-1)
                ImageView img = (ImageView) view.findViewById(R.id.ivi_cover);//Para fazer a animação, simplesmente identificamos o view que vai fazer a animação
                Bundle bundle = new Bundle();
                bundle.putSerializable("song", songs.get(position));//obtemos o objeto escolidho pelo usuário
                goToPlaySong(img, bundle);//esse metodo indica pra onde vão ser enviados os dados
            }
        });

        rviSongs.setAdapter(songsAdapter);
        rviSongs.setItemAnimator(new DefaultItemAnimator());
    }


    /**
     * Valida se o sistema operacional soporta Material Design e coloca o view e o objeto que vão ser enviados
     * **/
    private void goToPlaySong(View view, Bundle bundle){
        Intent intent = new Intent(SongsActivity.this, PlaySongActivity.class);
        if (bundle != null) {
            intent.putExtra("extra", bundle);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(new Explode());
            ActivityOptions options = view != null ?
                    ActivityOptions.makeSceneTransitionAnimation(this, view, "view") :
                    ActivityOptions.makeSceneTransitionAnimation(this);
            startActivity(intent, options.toBundle());//Chamar PlaySonActivity com animação

        } else {
            startActivity(intent);//Chamar PlaySonActivity sem animação
        }
    }



}
