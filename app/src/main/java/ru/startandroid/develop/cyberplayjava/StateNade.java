package ru.startandroid.develop.cyberplayjava;

public class StateNade {
    private String id;
    private String name; // название
    private String videoLink;
    private String nadeResource; // ресурс флага

    public StateNade(String id,String name, String nadeResource, String link)
    {
        this.id = id;
        this.name = name;
        this.nadeResource = nadeResource;
        this.videoLink = link;
    }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }


    public String getNadeResource() { return this.nadeResource; }
    public void setNadeResource(String nadeResource) { this.nadeResource = nadeResource; }

    public String getVideoLink() {return this.videoLink;}
    public void setVideoLink(String link) {this.videoLink = link;}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
