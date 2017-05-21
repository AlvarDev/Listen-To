package com.alvardev.listento.models.response;

import com.alvardev.listento.models.Track;

import java.util.List;

/**
 * Created by alvardev on 21/05/17.
 * Response from Spotify
 */

public class TracksResponse {

    private Tracks tracks;

    public TracksResponse() {
    }

    public TracksResponse(Tracks tracks) {
        this.tracks = tracks;
    }

    public Tracks getTracks() {
        return tracks;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }

    @Override
    public String toString() {
        return "TracksResponse{" +
                "tracks=" + tracks +
                '}';
    }

    public class Tracks{
        private List<Track> items;

        public Tracks() {
        }

        public Tracks(List<Track> items) {
            this.items = items;
        }

        public List<Track> getItems() {
            return items;
        }

        public void setItems(List<Track> items) {
            this.items = items;
        }

        @Override
        public String toString() {
            return "Tracks{" +
                    "items=" + items +
                    '}';
        }
    }

}
