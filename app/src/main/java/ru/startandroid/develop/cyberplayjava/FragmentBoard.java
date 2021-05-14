package ru.startandroid.develop.cyberplayjava;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentBoard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentBoard extends Fragment {

    public FragmentBoard() {
        // Required empty public constructor
    }

    public static FragmentBoard newInstance() {

        return new FragmentBoard();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_board, container, false);
    }
}