package ru.startandroid.develop.cyberplayjava;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentItemTrainings extends Fragment {

    ArrayList<StateTraining> states = new ArrayList();
    ListView trainingList;
    int choosenStateId;

    public FragmentItemTrainings(int id) {
        choosenStateId = id;
    }

    public FragmentItemTrainings()
    {}


    public static FragmentItemTrainings newInstance() {
        return new FragmentItemTrainings();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_item_training, container, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://94.228.115.44:80/api/trainings/") // URL to Server
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MessagesApi messagesApi = retrofit.create(MessagesApi.class);
        Call<List<MessageTraining>> call;
        switch (choosenStateId)
        {
            case 0:
                call = messagesApi.messagesSoloTraining();
                break;
            case 1:
                call = messagesApi.messagesTeamTraining();
                break;
            default:
                call = messagesApi.messagesSoloTraining();
                break;

        }
        call.enqueue(new Callback<List<MessageTraining>>() {
            @Override
            public void onResponse(Call<List<MessageTraining>> call, Response<List<MessageTraining>> response) {

                List<MessageTraining> messages = response.body();
                for (MessageTraining mes : messages)
                {
                    // Log.i("Retrofit-add", "Adding");
                    setInitialData(mes.getName(),mes.getImgLink(),mes.getText());
                }

                trainingList = (ListView) root.findViewById(R.id.trainingList);
                // создаем адаптер
                StateTrainingAdapter stateAdapter = new StateTrainingAdapter(root.getContext(), R.layout.list_item_training, states);
                trainingList.setAdapter(stateAdapter);

                AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                        // получаем выбранный пункт
                        StateTraining selectedState = (StateTraining)parent.getItemAtPosition(position);

                        Toast.makeText(getActivity(),"Был выбран пункт " + selectedState.getName(),
                                Toast.LENGTH_SHORT).show();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fl_content, new FragmentInfoTraining(selectedState));
                        fragmentTransaction.commit();
                    }
                };
                trainingList.setOnItemClickListener(itemListener);
            }


            @Override
            public void onFailure(Call<List<MessageTraining>> call, Throwable t) {

            }
        });



        return root;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    private void setInitialData(String name, String imgLink, String text){
        states.add(new StateTraining(name,imgLink,text));
    }
}
