package ru.startandroid.develop.cyberplayjava;

public class StateTraining {
    private String name;
    private String imgLink;
    private String text;


    public StateTraining(String name, String imgLink,String text)
    {
        this.name = name;
        this.imgLink = imgLink;
        this.text = text;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
