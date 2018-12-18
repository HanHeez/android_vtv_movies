package com.vtvfillm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

//  {
//          "description": "12345",
//          "image": "",
//          "thumbnails": [],
//          "_id": "5c074c1b3118100a3c528bd0",
//          "name": "Phim mới"
//          },
public class Category implements Serializable {

    public Category(String description, String image, String name) {
        this.description = description;
        this.image = image;
        this.name = name;
    }

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("image")
    @Expose
    private String image;

    //TODO change Object to Class
    @SerializedName("thumbnails")
    @Expose
    private List<String> thumbnails = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("movies")
    @Expose
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public boolean useShadow() {
        return true;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(List<String> thumbnails) {
        this.thumbnails = thumbnails;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
