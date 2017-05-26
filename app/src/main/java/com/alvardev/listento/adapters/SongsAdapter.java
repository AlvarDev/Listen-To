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
 */

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.ViewHolder>
        implements View.OnClickListener {

    private List<Song> mData;
    private View.OnClickListener listener;
    private Context context;

    public SongsAdapter(List<Song> myData, Context context) {
        this.mData = myData;
        this.context = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iviCover;
        TextView tviBand;
        TextView tviName;
        TextView tviUser;

        ViewHolder(View v) {
            super(v);
            iviCover = (ImageView) v.findViewById(R.id.ivi_cover);
            tviBand = (TextView) v.findViewById(R.id.tvi_band);
            tviName = (TextView) v.findViewById(R.id.tvi_name);
            tviUser = (TextView) v.findViewById(R.id.tvi_user);
        }
    }

    @Override
    public SongsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_layout, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

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
        holder.tviUser.setText(mData.get(position).getUser());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null)
            listener.onClick(v);
    }

}
