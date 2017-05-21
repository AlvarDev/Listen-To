package com.alvardev.listento.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by alvardev on 21/05/17.
 * From Spotify
 */

public class Track {

    private String id;
    private AlbumSpotify album;
    private List<ArtistSpotify> artists;
    @SerializedName("preview_url")
    private String previewUrl;

    public Track() {
    }

    public Track(String id, AlbumSpotify album, List<ArtistSpotify> artists, String previewUrl) {
        this.id = id;
        this.album = album;
        this.artists = artists;
        this.previewUrl = previewUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AlbumSpotify getAlbum() {
        return album;
    }

    public void setAlbum(AlbumSpotify album) {
        this.album = album;
    }

    public List<ArtistSpotify> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistSpotify> artists) {
        this.artists = artists;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id='" + id + '\'' +
                ", album=" + album +
                ", artists=" + artists +
                ", previewUrl='" + previewUrl + '\'' +
                '}';
    }
}
