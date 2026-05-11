package com.example.kazantsevartur.P5.Part2;

public class Film {
    private int id;
    private String name;
    private float rate;
    private String genre;
    private int year;
    private String director;

    public Film(int id, String name, float rate, String genre, int year, String director) {
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.genre = genre;
        this.year = year;
        this.director = director;
    }

    // Геттеры
    public int getId() { return id; }
    public String getName() { return name; }
    public float getRate() { return rate; }
    public String getGenre() { return genre; }
    public int getYear() { return year; }
    public String getDirector() { return director; }

    @Override
    public String toString() {
        return String.format("%s (%d) | %s | ⭐ %.1f | Реж: %s",
                name, year, genre, rate, director);
    }
}
