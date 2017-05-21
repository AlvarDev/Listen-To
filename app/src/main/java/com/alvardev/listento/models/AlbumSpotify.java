package com.alvardev.listento.models;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by alvardev on 21/05/17.
 * Album Spotify
 */

public class AlbumSpotify extends RealmObject{

    private String id;
    private RealmList<ImageSpotify> images;

    public AlbumSpotify() {
    }

    public AlbumSpotify(String id, RealmList<ImageSpotify> images) {
        this.id = id;
        this.images = images;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RealmList<ImageSpotify> getImages() {
        return images;
    }

    public void setImages(RealmList<ImageSpotify> images) {
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
