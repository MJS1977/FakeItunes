package com.example.task4itunes.models;

public class GetRandomArtist { //class to get random artists

    private String name;

    public GetRandomArtist(){
    }

    public GetRandomArtist(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
