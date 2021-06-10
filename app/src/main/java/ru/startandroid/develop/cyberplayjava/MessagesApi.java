package ru.startandroid.develop.cyberplayjava;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

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

    @GET("OneWay.php")
    Call<List<Message>> messagesOneWay();

    @POST("FavNades.php")
    Call<List<Message>> messagesFavNades(@Body ValidateUser key_jwt);


    @GET("soloTraining.php")
    Call<List<MessageTraining>> messagesSoloTraining();

    @GET("teamTraining.php")
    Call<List<MessageTraining>> messagesTeamTraining();

    @POST("jwt_login.php")
    Call<LoginResponse> loginUser(@Body User user);

    @POST("jwt_validate.php")
    Call<ValidateResponse> validateUser(@Body ValidateUser key_jwt);

    @POST("create_user.php")
    Call<ValidateResponse> createUser(@Body User user);

    @POST("add_fav_nades.php")
    Call<ValidateResponse> addFavNade(@Body VFavNade favNade);
}
