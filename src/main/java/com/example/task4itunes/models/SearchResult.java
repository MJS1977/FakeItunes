package com.example.task4itunes.models;

public class SearchResult {

    private String albumId;
    private String composer;

    public SearchResult(){ // Class for song query, not functional...
    }

    public SearchResult(String albumId, String composer) {
        this.albumId = albumId;
        this.composer = composer;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }
}
