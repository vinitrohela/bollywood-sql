package com.allstate.entities;

import javax.persistence.*;

@Entity
@Table(name = "movies")

public class Movie {
    private int id;
    private int version;
    private String title;
    private boolean watched;
    private String rating;
    private int movieLength;



    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Version
    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }

    @Column(nullable = false)
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }



    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getMovieLength() {
        return movieLength;
    }

    public void setMovieLength(int length) {
        this.movieLength = movieLength;
    }
}
