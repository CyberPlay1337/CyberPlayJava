package ru.startandroid.develop.cyberplayjava;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentNades#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentNades extends Fragment {

    ArrayList<StateMap> states = new ArrayList();
    ListView mapList;

    public FragmentNades() {
        // Required empty public constructor
    }

    public static FragmentNades newInstance() {
        return new FragmentNades();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_nades, container, false);
        setInitialData();
        mapList = (ListView) root.findViewById(R.id.mapList);
        // создаем адаптер
        StateMapAdapter stateAdapter = new StateMapAdapter(root.getContext(), R.layout.list_item_map, states);
        mapList.setAdapter(stateAdapter);

        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                // получаем выбранный пункт
                StateMap selectedState = (StateMap)parent.getItemAtPosition(position);

                Toast.makeText(getActivity(),"Был выбран пункт " + selectedState.getName(),
                        Toast.LENGTH_SHORT).show();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fl_content, new FragmentItemNades(selectedState.getName()));
                fragmentTransaction.commit();
            }
        };
        mapList.setOnItemClickListener(itemListener);

        return root;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    private void setInitialData(){
        states.clear();
        states.add(new StateMap("Dust 2",  R.drawable.de_dust2));
        states.add(new StateMap("Inferno", R.drawable.de_inferno));
        states.add(new StateMap("Mirage", R.drawable.de_mirage));
        states.add(new StateMap("Ancient", R.drawable.de_ancient));
        states.add(new StateMap("Overpass",  R.drawable.de_overpass));
        states.add(new StateMap("Vertigo",  R.drawable.de_vertigo));
        states.add(new StateMap("Nuke",  R.drawable.de_nuke));
    }



   /* // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentNades() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentNades.
     */ /*
    // TODO: Rename and change types and number of parameters
    public static FragmentNades newInstance(String param1, String param2) {
        FragmentNades fragment = new FragmentNades();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nades, container, false);
    } */
}