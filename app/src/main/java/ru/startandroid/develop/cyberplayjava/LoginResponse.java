package ru.startandroid.develop.cyberplayjava;

public class LoginResponse {
    private String message;
    private String jwt;

    public String getJwt() {
        return jwt;
    }

    public String getMessage() {
        return message;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
