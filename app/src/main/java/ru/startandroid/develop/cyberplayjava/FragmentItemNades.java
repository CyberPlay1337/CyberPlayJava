package ru.startandroid.develop.cyberplayjava;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
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

        // VideoView videoView = (VideoView) root.findViewById(R.id.videoPlayer);
        YouTubePlayerView youTubePlayerView = root.findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);




       Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:80/api/nadeMaps/") // URL to Server
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
            case "Train":
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
            default:
                call = messagesApi.messagesDust2();
                break;

        }


        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {

                List<Message> messages = response.body();
                for (Message mes : messages)
                {
                    // Log.i("Retrofit-add", "Adding");
                    setInitialData(mes.getName(),mes.getNadeResource(),mes.getVideoLink());
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
                                // do whatever
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




    private void setInitialData(String name, String linkImg, String linkVideo){
        states.add(new StateNade(name,  linkImg, linkVideo));
    }
}
