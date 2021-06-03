package ru.startandroid.develop.cyberplayjava;

import com.google.gson.annotations.SerializedName;

public class VFavNade {
    @SerializedName("jwt")
    String jwt;

    @SerializedName("nade_id")
    String nade_id;



    public VFavNade(String jwt, String id) {
        this.jwt = jwt; this.nade_id =id;
    }
}
