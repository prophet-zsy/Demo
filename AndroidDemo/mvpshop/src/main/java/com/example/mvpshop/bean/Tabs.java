package com.example.mvpshop.bean;
import java.util.List;

public class Tabs {
    private String name;
    private List<String> pictures;
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }
    public List<String> getPictures() {
        return pictures;
    }

    @Override
    public String toString() {
        return "Tabs{" +
                "name='" + name + '\'' +
                ", pictures=" + pictures +
                '}';
    }
}
