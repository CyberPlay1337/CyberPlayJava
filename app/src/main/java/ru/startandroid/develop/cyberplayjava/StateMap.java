package ru.startandroid.develop.cyberplayjava;

public class StateMap {
    private String name; // название
    private int mapResource; // ресурс флага

    public StateMap(String name, int mapResource)
    {
        this.name = name;
        this.mapResource = mapResource;
    }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public int getMapResource() { return this.mapResource; }
    public void setMapResource(int mapResource) { this.mapResource = mapResource; }

}
