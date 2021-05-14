package ru.startandroid.develop.cyberplayjava;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class FragmentItemTrainings extends Fragment {

    ArrayList<StateTraining> states = new ArrayList();
    ListView trainingList;

    public FragmentItemTrainings() {}

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
        setInitialData();
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
        return root;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    private void setInitialData(){
        states.clear();
        states.add(new StateTraining("Aim",  "https://sun9-57.userapi.com/impg/sf663OVYdPk0TOPnTIzHZs06S4_Q4x9CbDpaGg/HWdcIo1prJY.jpg?size=1080x1080&quality=96&sign=ae5ea0f5e4b954ccfca0a1c57348d8c2&type=album"));
        states.add(new StateTraining("Aim by s1mple",  "https://sun9-57.userapi.com/impg/sf663OVYdPk0TOPnTIzHZs06S4_Q4x9CbDpaGg/HWdcIo1prJY.jpg?size=1080x1080&quality=96&sign=ae5ea0f5e4b954ccfca0a1c57348d8c2&type=album"));
        states.add(new StateTraining("Индивидуальная тренировка",  "https://sun9-57.userapi.com/impg/sf663OVYdPk0TOPnTIzHZs06S4_Q4x9CbDpaGg/HWdcIo1prJY.jpg?size=1080x1080&quality=96&sign=ae5ea0f5e4b954ccfca0a1c57348d8c2&type=album"));
    }
}
