package fr.bmartel.youtubetv.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


// {
//         "_id": "5c08eb0f097afc0898506809",
//         "description": "sfdsaf",
//         "snippet": "",
//         "name": "Tập 12",
//         "src": "https://www.youtube.com/watch?v=TTFXamkTYSs",
//         "shortDescription": "ádfasdf"
//         },
public class Episode implements Serializable {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("snippet")
    @Expose
    private String snippet;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("src")
    @Expose
    private String src;
    @SerializedName("shortDescription")
    @Expose
    private String shortDescription;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    private String episode;

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
}
