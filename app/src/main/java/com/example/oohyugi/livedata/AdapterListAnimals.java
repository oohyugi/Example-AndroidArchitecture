package com.example.oohyugi.livedata;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by oohyugi on 7/13/17.
 */
public class AdapterListAnimals extends
        RecyclerView.Adapter<AdapterListAnimals.ViewHolder> {

    private static final String TAG = AdapterListAnimals.class.getSimpleName();

    private Context context;
    private List<AnimalsDao> list;

    public AdapterListAnimals(Context context, List<AnimalsDao> list) {
        this.context = context;
        this.list = list;

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tvName);

        }


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.animals_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AnimalsDao item = list.get(position);

        //Todo: Setup viewholder for item
        holder.tvName.setText(item.getName());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


}