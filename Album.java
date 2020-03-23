package com.UdemyJava;

import java.util.ArrayList;

public class Album {

    private String albumName;
    private ArrayList<Song> songs;

    public Album(String albumName) {
        this.albumName = albumName;
        this.songs = new ArrayList<Song>();
    }

    public String getAlbumName() {
        return albumName;
    }

    public void addSong (String songName, int length) {
        this.songs.add(new Song(songName, length));
    }

    public Song findSong (String songName) {
        for (int i = 0; i < this.songs.size(); i ++) {
            Song foundSong = this.songs.get(i);
            if (foundSong.getName().equals(songName)) {
                return foundSong;
            }
        }
        return null;
    }
}
