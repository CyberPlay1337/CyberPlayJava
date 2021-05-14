package ru.startandroid.develop.cyberplayjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class StateTrainingAdapter extends ArrayAdapter<StateTraining> {
    private LayoutInflater inflater;
    private int layout;
    private List<StateTraining> states;

    public StateTrainingAdapter(Context context, int resource, List<StateTraining> states) {
        super(context, resource, states);
        this.states = states;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent, false);

        TextView nameView = (TextView) view.findViewById(R.id.trainingItemTxt);
        ImageView imageView = (ImageView) view.findViewById(R.id.trainingItemImg);
        StateTraining state = states.get(position);

        new DownloadImageTask(imageView).execute(state.getImgLink());

        //tut Img
        nameView.setText(state.getName());

        return view;
    }

}
