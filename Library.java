package com.UdemyJava;

import java.util.ArrayList;
import java.util.LinkedList;

public class Library {

    private String libraryName;
    private ArrayList<Album> library;

    public Library(String libraryName) {
        this.libraryName = libraryName;
        this.library = new ArrayList<Album>();
    }

    public void addAlbum(String albumName) {
        this.library.add(new Album(albumName));
    }

    public void addAlbum(Album album){
        this.library.add(album);
    }

    public LinkedList<Song> addSongToPlayList (LinkedList<Song> playList, String songName) {
        Song foundSong = findSongOnAlbum(songName);
        if (foundSong != null) {
            playList.add(foundSong);
            return playList;
        }
        return null;
    }

    public Song findSongOnAlbum (String songName) {
        for (int i = 0; i < this.library.size(); i++) {
            Album album = this.library.get(i);
            Song foundSong = album.findSong(songName);
            if (foundSong!= null) {
                return foundSong;
            }
        }
        return null;
    }

    public Album findAlbum (String albumName) {
        for (int i = 0; i < this.library.size(); i++) {
            Album foundAlbum = this.library.get(i);
            if (foundAlbum.getAlbumName().equals(albumName)) {
                return foundAlbum;
            }
        }
        return null;
    }
}
