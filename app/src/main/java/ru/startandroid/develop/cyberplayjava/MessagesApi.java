package ru.startandroid.develop.cyberplayjava;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MessagesApi {

    @GET("Dust2.php")
    Call<List<Message>> messagesDust2();

    @GET("Inferno.php")
    Call<List<Message>> messagesInferno();

    @GET("Mirage.php")
    Call<List<Message>> messagesMirage();

    @GET("Ancient.php")
    Call<List<Message>> messagesTrain();

    @GET("Overpass.php")
    Call<List<Message>> messagesOverpass();

    @GET("Vertigo.php")
    Call<List<Message>> messagesVertigo();

    @GET("Nuke.php")
    Call<List<Message>> messagesNuke();


    @GET("soloTraining.json")
    Call<List<MessageTraining>> messagesSoloTraining();

    @GET("TeamTraining.json")
    Call<List<MessageTraining>> messagesTeamTraining();

}
