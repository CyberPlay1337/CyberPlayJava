package ru.startandroid.develop.cyberplayjava;

public class StateTraining {
    private String name;
    private String imgLink;

    public StateTraining(String name, String imgLink)
    {
        this.name = name;
        this.imgLink = imgLink;
    }

    public String getName() {
        return name;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public void setName(String name) {
        this.name = name;
    }
}
