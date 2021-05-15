package ru.startandroid.develop.cyberplayjava;

public class MessageTraining {
    private long id;
    private String name; // название
    private String text;
    private String imgLink; // ресурс флага


    public long getId() { return this.id;}
    public void setId(long id) { this.id = id; }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public String getText() { return this.text; }
    public void setText(String text) { this.text = text; }

    public String getImgLink() {return this.imgLink;}
    public void setImgLink(String link) {this.imgLink = link;}
}
