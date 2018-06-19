package Objects;

import android.graphics.drawable.Drawable;

/**
 * Created by Mostafa on 2018/01/16.
 */

public class Item {
    private long id;
    private String name , about;
    private String image , captured;
    private String date;

    public Item(long id, String name, String about, String image, String captured, String date) {
        this.id = id;
        this.name = name;
        this.about = about;
        this.image = image;
        this.captured = captured;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCaptured() {
        return captured;
    }

    public void setCaptured(String captured) {
        this.captured = captured;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
