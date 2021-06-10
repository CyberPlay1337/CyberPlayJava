package ru.startandroid.develop.cyberplayjava;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentItemNades extends Fragment{

    ArrayList<StateNade> states = new ArrayList();
    RecyclerView nadesList;

    String nowState;


    public FragmentItemNades(String nowState) {
        this.nowState = nowState;

    }

    public FragmentItemNades()
    {

    }
    public static FragmentItemNades newInstance() { return new FragmentItemNades(); }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_item_nade, container, false);
        SharedPreferences myPreferences
                = PreferenceManager.getDefaultSharedPreferences(root.getContext());
        // VideoView videoView = (VideoView) root.findViewById(R.id.videoPlayer);
        YouTubePlayerView youTubePlayerView = root.findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);




       Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://94.228.115.44:80/api/nadeMaps/") // URL to Server
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MessagesApi messagesApi = retrofit.create(MessagesApi.class);
        Call<List<Message>> call;
        switch (nowState)
        {
            case "Dust 2":
                call = messagesApi.messagesDust2();
                break;
            case "Inferno":
                call = messagesApi.messagesInferno();
                break;
            case "Mirage":
                call = messagesApi.messagesMirage();
                break;
            case "Ancient":
                call = messagesApi.messagesTrain();
                break;
            case "Overpass":
                call = messagesApi.messagesOverpass();
                break;
            case "Vertigo":
                call = messagesApi.messagesVertigo();
                break;
            case "Nuke":
                call = messagesApi.messagesNuke();
                break;
            case "OneWay":
                call = messagesApi.messagesOneWay();
                break;
            case "FavNades":
                call = messagesApi.messagesFavNades(new ValidateUser(myPreferences.getString("jwt", "unknown")));
                break;
            default:
                call = messagesApi.messagesDust2();
                break;

        }


        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {

                List<Message> messages = response.body();
                try {
                    for (Message mes : messages)
                    {
                        // Log.i("Retrofit-add", "Adding");
                        setInitialData(mes.getId(),mes.getName(),mes.getNadeResource(),mes.getVideoLink());
                    }
                } catch (Exception e) {
                    // e.printStackTrace();
                }


                nadesList = (RecyclerView) root.findViewById(R.id.nadeList);
                StateNadeAdapter stateAdapter = new StateNadeAdapter(root.getContext(),states);
                nadesList.setLayoutManager(new GridLayoutManager(root.getContext(), 2));
                nadesList.setAdapter(stateAdapter);
                nadesList.addOnItemTouchListener(
                        new RecyclerItemClickListener(root.getContext(), nadesList ,new RecyclerItemClickListener.OnItemClickListener() {
                            @Override public void onItemClick(View view, int position) {

                                StateNade sn = (StateNade) states.get(position);
                                // videoView.setVideoURI(Uri.parse(sn.getVideoLink()));

                                Toast.makeText(getActivity(),"Был выбран пункт " + sn.getName() ,Toast.LENGTH_SHORT).show();
                                // videoView.setMediaController( new MediaController(root.getContext()));

                                // videoView.start();

                                youTubePlayerView.getYouTubePlayerWhenReady(youTubePlayer -> {
                                    youTubePlayer.loadVideo(sn.getVideoLink(),0);
                                });
                            }

                            @Override public void onLongItemClick(View view, int position) {

                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl("http://94.228.115.44:80/api/") // URL to Server
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();
                                MessagesApi messagesApi = retrofit.create(MessagesApi.class);


                                StateNade sn = (StateNade) states.get(position);

                                Call<ValidateResponse> call_fav;

                                VFavNade fnade = new VFavNade(myPreferences.getString("jwt", "unknown"),sn.getId());
                                //Log.d("retrofit",myPreferences.getString("jwt", "unknown"));

                                call_fav = messagesApi.addFavNade(fnade);
                                call_fav.enqueue(new Callback<ValidateResponse>() {
                                    @Override
                                    public void onResponse(Call<ValidateResponse> call, Response<ValidateResponse> response) {
                                        //Log.d("Retrofit", response.body().getMessage());
                                        if(response.body().getMessage().equals("Nade was added."))
                                            Toast.makeText(getActivity(),"Граната добавлена в избранные",Toast.LENGTH_SHORT).show();
                                        if(response.body().getMessage().equals("Nade was deleted."))
                                            Toast.makeText(getActivity(),"Граната удалена из избранного",Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onFailure(Call<ValidateResponse> call, Throwable t) {
                                        //Log.d("Retrofit", "textF");
                                    }
                                });
                            }
                        })
                );
            }


            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                // Log.i("Retrofit-add", t.getMessage());
            }
        });


        return root;
    }




    private void setInitialData(String id,String name, String linkImg, String linkVideo){
        states.add(new StateNade(id,name,  linkImg, linkVideo));
    }
}
