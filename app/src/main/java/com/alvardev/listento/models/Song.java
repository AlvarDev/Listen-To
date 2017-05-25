package com.alvardev.listento.models;

import java.io.Serializable;

/**
 * Created by alvardev on 18/05/17.
 * Song model
 */

public class Song implements Serializable{

    private String id;
    private String urlCover;
    private String band;
    private String name;
    private String user;

    public Song() {
    }

    public Song(String id, String urlCover, String band, String name, String user) {
        this.id = id;
        this.urlCover = urlCover;
        this.band = band;
        this.name = name;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrlCover() {
        return urlCover;
    }

    public void setUrlCover(String urlCover) {
        this.urlCover = urlCover;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id='" + id + '\'' +
                ", urlCover='" + urlCover + '\'' +
                ", band='" + band + '\'' +
                ", name='" + name + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
