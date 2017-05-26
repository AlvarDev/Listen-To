package com.alvardev.listento.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.alvardev.listento.R;
import com.alvardev.listento.models.Track;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by alvardev on 21/05/17.
 * Adapter for Tracks from Spotify
 */

public class TracksAdapter extends RecyclerView.Adapter<TracksAdapter.ViewHolder> {

    private List<Track> mData;
    private Context context;
    private TrackInterface mListener;

    public TracksAdapter(List<Track> myData, Context context) {
        this.mData = myData;
        this.context = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iviCover;
        TextView tviBand;
        TextView tviName;
        ImageButton ibuPlay;
        ImageButton ibuShare;

        ViewHolder(View v) {
            super(v);
            iviCover = (ImageView) v.findViewById(R.id.ivi_cover);
            tviBand = (TextView) v.findViewById(R.id.tvi_band);
            tviName = (TextView) v.findViewById(R.id.tvi_name);
            ibuPlay = (ImageButton) v.findViewById(R.id.ibu_play);
            ibuShare = (ImageButton) v.findViewById(R.id.ibu_share);
        }
    }

    @Override
    public TracksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.track_layout, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.ibuPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    mListener.onPlaySong(holder.getAdapterPosition());
                }
            }
        });
        holder.ibuShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    mListener.onShareSong(holder.getAdapterPosition());
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String urlCover =  mData.get(position).getAlbum().getImages().get(0).getUrl() == null ?
                "" : mData.get(position).getAlbum().getImages().get(0).getUrl();
        Picasso.with(context)
                .load(urlCover)
                .placeholder(R.drawable.lp_logo)
                .error(R.drawable.lp_logo)
                .into(holder.iviCover);

        holder.tviBand.setText(mData.get(position).getArtists().get(0).getName());
        holder.tviName.setText(mData.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public void setOnActionsListener(TrackInterface mListener){
        this.mListener = mListener;
    }

    public interface TrackInterface {

        void onPlaySong(int position);

        void onShareSong(int position);

    }

}