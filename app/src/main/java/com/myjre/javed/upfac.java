package com.myjre.javed;

public class upfac {
    String name;
    String qualificaion;
    String image;
    String post;

    public upfac() {
    }



    public upfac(String name, String qualificaion, String image, String post) {
        this.name = name;
        this.qualificaion = qualificaion;
        this.image = image;
        this.post=post;
    }
    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualificaion() {
        return qualificaion;
    }

    public void setQualificaion(String qualificaion) {
        this.qualificaion = qualificaion;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
