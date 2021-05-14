package ru.startandroid.develop.cyberplayjava;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class FragmentInfoTraining extends Fragment {

    ArrayList<StateTraining> states = new ArrayList();
    StateTraining stateTraining;

    public FragmentInfoTraining(StateTraining state) {
        stateTraining = state;
    }

    public static FragmentInfoTraining newInstance(StateTraining state) {
        return new FragmentInfoTraining(state);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_info_train, container, false);
        TextView title = root.findViewById(R.id.titleTraining);
        title.setText(stateTraining.getName());
        ImageView iv = root.findViewById(R.id.titleImg);
        new DownloadImageTask(iv).execute(stateTraining.getImgLink());

        return root;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}
