package com.alvardev.listento.models;

import java.util.List;

/**
 * Created by alvardev on 21/05/17.
 * Album Spotify
 */

public class AlbumSpotify {

    private String id;
    private List<ImageSpotify> images;

    public AlbumSpotify() {
    }

    public AlbumSpotify(String id, List<ImageSpotify> images) {
        this.id = id;
        this.images = images;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ImageSpotify> getImages() {
        return images;
    }

    public void setImages(List<ImageSpotify> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "AlbumSpotify{" +
                "id='" + id + '\'' +
                ", images=" + images +
                '}';
    }
}
