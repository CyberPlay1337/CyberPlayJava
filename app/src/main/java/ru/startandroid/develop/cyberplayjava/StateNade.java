package ru.startandroid.develop.cyberplayjava;

public class StateNade {
    private String name; // название
    private String  videoLink;
    private int nadeResource; // ресурс флага

    public StateNade(String name, int nadeResource, String link)
    {
        this.name = name;
        this.nadeResource = nadeResource;
        this.videoLink = link;
    }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }


    public int getNadeResource() { return this.nadeResource; }
    public void setNadeResource(int nadeResource) { this.nadeResource = nadeResource; }

    public String getVideoLink() {return this.videoLink;}
    public void setVideoLink(String link) {this.videoLink = link;}

}
