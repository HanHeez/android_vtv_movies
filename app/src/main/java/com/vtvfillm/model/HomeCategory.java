package com.vtvfillm.model;

import android.support.annotation.ColorRes;


public class HomeCategory {
    private String id;
    private String name;
    private @ColorRes int background;

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
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

    public HomeCategory(String id, String name, @ColorRes int background) {
        this.id = id;
        this.name = name;
        this.background = background;
    }
}
