package fr.bmartel.youtubetv.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

//{
//        "_id": "5c08a520c1fb68e85ddde795",
//        "name": "Phim 38",
//        "avatar": "https://vignette.wikia.nocookie.net/ex-naruto/images/2/25/8731a-3.png/revision/latest?cb=20150718020266",
//        "description": "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
//        "episodeNumber": 30,
//        "nation": "Nhật bản",
//        "releasedYear": 2018,
//        "screenShot": "http://genknews.genkcdn.vn/zoom/650_413/2018/7/22/photo-3-1502690983426-1532235535872303818279.jpg",
//        "shortDescription": "Lorem Ipsum is simply dummy text of the printing and typesetting industry",
//        "type": "series"
//        },
public class Movie implements Serializable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("episodeNumber")
    @Expose
    private Integer episodeNumber;
    @SerializedName("nation")
    @Expose
    private String nation;
    @SerializedName("releasedYear")
    @Expose
    private Integer releasedYear;
    @SerializedName("screenShot")
    @Expose
    private String screenShot;
    @SerializedName("shortDescription")
    @Expose
    private String shortDescription;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("subCategories")
    @Expose
    private List<Category> subCategories = null;

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public Integer getReleasedYear() {
        return releasedYear;
    }

    public void setReleasedYear(Integer releasedYear) {
        this.releasedYear = releasedYear;
    }

    public String getScreenShot() {
        return screenShot;
    }

    public void setScreenShot(String screenShot) {
        this.screenShot = screenShot;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    String subCategoryId;

    public Movie(String id, String subCategoryId, String name) {
        this.id = id;
        this.subCategoryId = subCategoryId;
        this.name = name;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public Movie() {
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }
}
