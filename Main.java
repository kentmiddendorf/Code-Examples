package com.UdemyJava;

import javax.naming.LinkLoopException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Library myLibrary = new Library("My Library");

        Album firstAlbum = new Album("First Album");
        firstAlbum.addSong("Song One", 240);
        firstAlbum.addSong("Song Two", 70);
        firstAlbum.addSong("Song Three", 300);
        firstAlbum.addSong("Song Four", 270);
        firstAlbum.addSong("Song Five", 250);
        firstAlbum.addSong("Song Six", 230);

        Album prince = new Album("1999");
        prince.addSong("Little Red Corvette", 230);
        prince.addSong("Delirious", 230);
        prince.addSong("International Lover", 230);
        prince.addSong("1999", 230);
        prince.addSong("Lady Cab Driver", 230);
        prince.addSong("Let's Pretend We're Married", 230);
        prince.addSong("D.M.S.R", 230);

        myLibrary.addAlbum(firstAlbum);
        myLibrary.addAlbum(prince);

        LinkedList<Song> myPlayList = new LinkedList<Song>();

        myLibrary.addSongToPlayList(myPlayList, "Little Red Corvette");
        myLibrary.addSongToPlayList(myPlayList, "1999");
        myLibrary.addSongToPlayList(myPlayList, "Song Four");
        myLibrary.addSongToPlayList(myPlayList, "Lady Cab Driver");
        myLibrary.addSongToPlayList(myPlayList, "Song One");

        boolean quit = false;
        int option;
        Scanner scanner = new Scanner(System.in);
        ListIterator<Song> iterator = myPlayList.listIterator();
        boolean forward = true;
        String currentSong;
        String nextCurrentSong;


        if (myPlayList.isEmpty()) {
            System.out.println("Sorry - No songs on play list.");
        } else {
            printMenu();
            System.out.println("Playing Play List: My Play List");
            currentSong = iterator.next().getName();
            System.out.println("\tCurrent song playing: " + currentSong);
            System.out.println();

            while (!quit) {
                System.out.println("Enter Menu option:");
                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        if (!forward) {
                            if (iterator.hasNext()) {
                                iterator.next();
                            }
                        }
                        forward = true;
                        if (iterator.hasNext()) {
                            currentSong = iterator.next().getName();
                            System.out.println("Playing next song - " + currentSong);
                        } else {
                            System.out.println("End of playlist reached. No more songs.");
                        }
                        break;
                    case 2:
                        if (forward) {
                            if (iterator.hasPrevious()) {
                                iterator.previous();
                            }
                        }
                        forward = false;
                        if (iterator.hasPrevious()) {
                            currentSong = iterator.previous().getName();
                            System.out.println("Playing previous song - " + currentSong);
                        } else {
                            System.out.println("Beginning of playlist reached. No previous songs.");
                        }
                        break;
                    case 3:
                        System.out.println("Replaying current song - " + currentSong);
                        break;
                    case 4:
                        printPlayList(myPlayList);
                        break;
                    case 5:
                        printMenu();
                        break;
                    case 6:
                        if (myPlayList.size() > 0) {
                            System.out.println("Removing " + currentSong + " From Play List.");
                            iterator.remove();
                            if (iterator.hasNext()) {
                                currentSong = iterator.next().getName();
                                System.out.println("Now Playing " + currentSong);
                                forward = true;
                            } else if (iterator.hasPrevious()) {
                                currentSong = iterator.previous().getName();
                                System.out.println("Now Playing " + currentSong);
                                forward = false;
                            } else {
                                System.out.println("Playlist Is Now Empty!");
                                quitPlaylist();;
                                quit = true;
                            }
                        }
                        break;
                    case 0:
                        quitPlaylist();
                        quit = true;
                        break;
                }
            }
        }
    }

    private static void printPlayList(LinkedList<Song> playList) {
        System.out.println("Play List Songs:");
        for (int i = 0; i < playList.size(); i ++) {
            System.out.println("\t[" + (i + 1) + "] - " + playList.get(i).getName());
        }
    }

    private static void printMenu() {
        System.out.println("1 - Play Next Song");
        System.out.println("2 - Play Previous Song");
        System.out.println("3 - Replay Current Song");
        System.out.println("4 - Print Play List Songs");
        System.out.println("5 - Print Menu Options");
        System.out.println("6 - Remove Current Song From Play List");
        System.out.println("0 - Quit Play List");
        System.out.println();
    }

    public static void quitPlaylist() {
        System.out.println("Play list Application Shutting Down ...");
        System.out.println("Good Bye.");
    }
}

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
