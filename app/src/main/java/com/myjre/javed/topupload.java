package com.myjre.javed;

public class topupload {
    String name;
    String Classes;
    String image;

    public topupload() {
    }

    public topupload(String name, String Classes, String image) {
        this.name = name;
        this.Classes = Classes;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClasses() {
        return Classes;
    }

    public void setClas(String Classes) {
        this.Classes = Classes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
       this.image = image;
    }
}
