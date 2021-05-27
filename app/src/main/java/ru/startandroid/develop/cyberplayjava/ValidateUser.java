package ru.startandroid.develop.cyberplayjava;

import com.google.gson.annotations.SerializedName;

public class ValidateUser {
    @SerializedName("jwt")
    String jwt;



    public ValidateUser(String jwt ) {
        this.jwt = jwt;
    }
}
