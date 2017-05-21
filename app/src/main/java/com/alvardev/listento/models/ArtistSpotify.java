package com.alvardev.listento.models;

import io.realm.RealmObject;

/**
 * Created by alvardev on 21/05/17.
 * Artist Spotify
 */

public class ArtistSpotify extends RealmObject{

    private String id;
    private String name;

    public ArtistSpotify() {
    }

    public ArtistSpotify(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ArtistSpotify{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
