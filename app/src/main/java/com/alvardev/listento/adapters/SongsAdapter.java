package com.alvardev.listento.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alvardev.listento.R;
import com.alvardev.listento.models.Song;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by alvardev on 18/05/17.
 * Adapter for RecyclerView
 *
 * Eu sei, é o mais chato do Android, mas sempre é a mesma estrutura, só muda os ids
 */

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.ViewHolder>
        implements View.OnClickListener {

    /**
     * Não esqueca colocar as librerias no (app) build.gradle pra poder importar as clases
     *
     * Criar os objetos que vamos a utilizar
     * **/
    private List<Song> mData;//ele contem a data que vai ser mostrada no recycler view
    private View.OnClickListener listener;//ele simplesmente fala para o Activity que um item foi escolhido
    private Context context;//para poder utilizar o Picasso

    /**
     * Cosntrutor para obter as informações que precisamos
     * **/
    public SongsAdapter(List<Song> myData, Context context) {
        this.mData = myData;
        this.context = context;
    }

    /**
     * Como é uma lista de objetos, cada objeto é como um Activity pequeno
     * então precisamos enlazar os views para ese objeto,
     * Simplesmente coloquem os view que foram utilizadas no layout (layout/song_layout.xml)
     * **/
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iviCover;
        TextView tviBand;
        TextView tviName;

        ViewHolder(View v) {
            super(v);
            iviCover = (ImageView) v.findViewById(R.id.ivi_cover);//enlazamos com o id colocado no layout
            tviBand = (TextView) v.findViewById(R.id.tvi_band);
            tviName = (TextView) v.findViewById(R.id.tvi_name);
        }
    }

    /**
     * Com os objetos já enlazados, damos acções
     * **/
    @Override
    public SongsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_layout, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    /**
     * onBindViewHolder é o equivalente a 'setInfo()'  o metodo que a gente utilizava para colocar
     * informações nos views
     * **/
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String urlCover = mData.get(position).getUrlCover() == null ?
                "" : mData.get(position).getUrlCover();
        Picasso.with(context)
                .load(urlCover)
                .placeholder(R.drawable.lp_logo)
                .error(R.drawable.lp_logo)
                .into(holder.iviCover);

        holder.tviBand.setText(mData.get(position).getBand());
        holder.tviName.setText(mData.get(position).getName());
    }

    /**Pra saber o total de objetos que tem nossa lista**/
    @Override
    public int getItemCount() {
        return mData.size();
    }

    /** damos acções, nem precisa mexer aqui **/
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    /** damos acções, nem precisa mexer aqui **/
    @Override
    public void onClick(View v) {
        if (listener != null)
            listener.onClick(v);
    }



}
