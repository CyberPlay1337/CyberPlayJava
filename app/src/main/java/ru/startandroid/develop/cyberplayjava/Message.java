package ru.startandroid.develop.cyberplayjava;

public class Message {
    private String id;
    private String name; // название
    private String videoLink;
    private String nadeResource; // ресурс флага


    public String getId() { return this.id;}
    public void setId(String id) { this.id = id; }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public String getNadeResource() { return this.nadeResource; }
    public void setNadeResource(String nadeResource) { this.nadeResource = nadeResource; }

    public String getVideoLink() {return this.videoLink;}
    public void setVideoLink(String link) {this.videoLink = link;}
}
