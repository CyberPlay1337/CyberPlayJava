package ru.startandroid.develop.cyberplayjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class StateNadeAdapter extends RecyclerView.Adapter<StateNadeAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<StateNade> states;

    StateNadeAdapter(Context context, List<StateNade> states) {
        this.states = states;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public StateNadeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item_nade, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StateNadeAdapter.ViewHolder holder, int position) {
        StateNade state = states.get(position);
        // holder.nadeResource.setImageResource(R.drawable.oneway);
        Picasso.get().load(state.getNadeResource()).into(holder.nadeResource);
        holder.name.setText(state.getName());
        //new DownloadImageTask(holder.nadeResource).execute(state.getNadeResource());

    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView nadeResource;
        public TextView name;
        ViewHolder(View view){
            super(view);
            nadeResource = (ImageView)view.findViewById(R.id.nadeImg);
            name = (TextView) view.findViewById(R.id.nadeName);
        }
    }
}
