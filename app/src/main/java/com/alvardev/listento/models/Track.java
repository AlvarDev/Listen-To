package com.alvardev.listento.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by alvardev on 21/05/17.
 * From Spotify
 */

public class Track extends RealmObject{

    private String id;
    private AlbumSpotify album;
    private RealmList<ArtistSpotify> artists;
    @SerializedName("preview_url")
    private String previewUrl;
    private String name;

    public Track() {
    }

    public Track(String id, AlbumSpotify album, RealmList<ArtistSpotify> artists, String previewUrl, String name) {
        this.id = id;
        this.album = album;
        this.artists = artists;
        this.previewUrl = previewUrl;
        this.name = name;
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

    public RealmList<ArtistSpotify> getArtists() {
        return artists;
    }

    public void setArtists(RealmList<ArtistSpotify> artists) {
        this.artists = artists;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id='" + id + '\'' +
                ", album=" + album +
                ", artists=" + artists +
                ", previewUrl='" + previewUrl + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
