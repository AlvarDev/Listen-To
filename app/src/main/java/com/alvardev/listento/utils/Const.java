package com.alvardev.listento.utils;

import com.alvardev.listento.models.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alvardev on 18/05/17.
 * Constants
 */

public class Const {

    public static List<Song> getSongs(){
        List<Song> songs = new ArrayList<>();

        songs.add(new Song("http://is5.mzstatic.com/image/thumb/Music122/v4/50/89/c3/5089c3f8-ed7c-cdeb-fe3b-2931deee3a98/source/1200x630bb.jpg", "Heavy", "Linkin Park"));
        songs.add(new Song("https://images.genius.com/79a2201c67a9aa386cda76562666c62d.500x500x1.jpg", "American Idiot", "Green Day"));
        songs.add(new Song("https://upload.wikimedia.org/wikipedia/en/3/37/Followthereaper.jpg", "Children of the decadence", "Children of Bottom"));
        songs.add(new Song("https://images-na.ssl-images-amazon.com/images/I/61P0M5B0V0L.jpg", "The phantom of the opera", "Nightwish"));
        songs.add(new Song("https://i.ytimg.com/vi/WKrXsbjiKRA/hqdefault.jpg", "Guilty", "The Rasmus"));
        songs.add(new Song("https://upload.wikimedia.org/wikipedia/en/1/17/The_Killers_-_Hot_Fuss.png", "Somebody told me", "The Killers"));
        songs.add(new Song("https://upload.wikimedia.org/wikipedia/en/8/8a/EvFallencover01.jpg", "My Immortal - Band Version", ""));
        songs.add(new Song("https://upload.wikimedia.org/wikipedia/en/e/ec/PapaRoachFEAR.png", "Gravity", "Papa Roach"));
        songs.add(new Song("https://upload.wikimedia.org/wikipedia/en/9/95/Skilletawake2009albumart.jpg", "Awake and Alive", "Skillet"));
        songs.add(new Song("http://streamd.hitparade.ch/cdimages/the_script-breakeven_s_1.jpg", "Breakeven", "The Script"));

        return songs;
    }

}
