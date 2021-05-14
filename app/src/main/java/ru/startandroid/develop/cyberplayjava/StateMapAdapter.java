package ru.startandroid.develop.cyberplayjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

public class StateMapAdapter extends ArrayAdapter<StateMap> {

    private LayoutInflater inflater;
    private int layout;
    private List<StateMap> states;

    public StateMapAdapter(Context context, int resource, List<StateMap> states) {
        super(context, resource, states);
        this.states = states;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent, false);

        ImageView flagView = (ImageView) view.findViewById(R.id.map);
        TextView nameView = (TextView) view.findViewById(R.id.name);

        StateMap state = states.get(position);

        flagView.setImageResource(state.getMapResource());
        nameView.setText(state.getName());

        return view;
    }

}
