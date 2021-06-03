package ru.startandroid.develop.cyberplayjava;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentProfile extends Fragment {

    public FragmentProfile() {
        // Required empty public constructor
    }


    public static FragmentProfile newInstance() {
        return new FragmentProfile();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView tvProfileNickName = root.findViewById(R.id.profileNickName);
        Button btnFavNades = root.findViewById(R.id.profileBtnFavNades);
        Button btnLogOut = root.findViewById(R.id.profileBtnLogOut);
        Button btnOneWay = root.findViewById(R.id.profileBtnOneWay);

        SharedPreferences myPreferences
                = PreferenceManager.getDefaultSharedPreferences(root.getContext());

        tvProfileNickName.setText(myPreferences.getString("login", "unknown"));


        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor myEditor = myPreferences.edit();
                myEditor.putString("jwt", "unknown");
                myEditor.putString("login", "unknown");
                myEditor.commit();

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fl_content, FragmentProfileSignInUp.newInstance());
                fragmentTransaction.commit();
            }
        });

        btnOneWay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fl_content, new FragmentItemNades ("OneWay"));
                fragmentTransaction.commit();
            }
        });

        btnFavNades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fl_content, new FragmentItemNades("FavNades"));
                fragmentTransaction.commit();
            }
        });
        return root;
    }
}