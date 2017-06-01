package com.alvardev.listento.models;

import java.io.Serializable;

/**
 * Created by alvardev on 18/05/17.
 * Song model
 *
 * É um objeto que tem todos os atributos que no Adapter (adapters/SongAdapter) precisa pra mostrar no recycler view
 */

public class Song implements Serializable{

    private String urlCover;
    private String band;
    private String name;

    public Song() {
    }

    public Song(String urlCover, String band, String name) {
        this.urlCover = urlCover;
        this.band = band;
        this.name = name;
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

    @Override
    public String toString() {
        return "Song{" +
                "urlCover='" + urlCover + '\'' +
                ", band='" + band + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
