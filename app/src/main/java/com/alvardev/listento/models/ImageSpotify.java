package com.alvardev.listento.models;

/**
 * Created by alvardev on 21/05/17.
 * Image Spotify
 */

public class ImageSpotify {

    private String url;
    private int height;
    private int width;

    public ImageSpotify() {
    }

    public ImageSpotify(int height, int width, String url) {
        this.height = height;
        this.width = width;
        this.url = url;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ImageSpotify{" +
                "height=" + height +
                ", width=" + width +
                ", url='" + url + '\'' +
                '}';
    }
}
