package ru.startandroid.develop.cyberplayjava;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class FragmentTrain extends Fragment {

    Button ly1;
    public FragmentTrain() {
        // Required empty public constructor

    }

    public static FragmentTrain newInstance() {
        return new FragmentTrain();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_train, container, false);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ly1 = (Button) root.findViewById(R.id.lyTraining1);
        Button ly2 = (Button) root.findViewById(R.id.lyTraining2);


        ly1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction.replace(R.id.fl_content, new FragmentItemTrainings());
                fragmentTransaction.commit();
            }
        });
        ly2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction.replace(R.id.fl_content, new FragmentItemTrainings());
                fragmentTransaction.commit();
            }
        });
        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}