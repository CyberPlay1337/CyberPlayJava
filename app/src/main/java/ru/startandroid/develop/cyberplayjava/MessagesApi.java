package ru.startandroid.develop.cyberplayjava;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MessagesApi {

    @GET("NadesDust2.json")
    Call<List<Message>> messagesDust2();

    @GET("NadesInferno.json")
    Call<List<Message>> messagesInferno();

    @GET("NadesMirage.json")
    Call<List<Message>> messagesMirage();

    @GET("NadesTrain.json")
    Call<List<Message>> messagesTrain();

    @GET("NadesOverpass.json")
    Call<List<Message>> messagesOverpass();

    @GET("NadesVertigo.json")
    Call<List<Message>> messagesVertigo();

    @GET("NadesNuke.json")
    Call<List<Message>> messagesNuke();


    @GET("SoloTraining.json")
    Call<List<MessageTraining>> messagesSoloTraining();

    @GET("TeamTraining.json")
    Call<List<MessageTraining>> messagesTeamTraining();

}
