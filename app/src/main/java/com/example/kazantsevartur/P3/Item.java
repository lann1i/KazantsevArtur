package com.example.kazantsevartur.P3;

public class Item {
    private String title;
    private int image;
    public Item(String title, int image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }
}
