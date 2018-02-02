package com.example.pandey.quicklift_master.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pandey.quicklift_master.R;
import com.example.pandey.quicklift_master.holders.LocationsViewHolder;
import com.example.pandey.quicklift_master.model.Destination;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pandey on 1/2/18.
 */

public class LocationsAdapter extends RecyclerView.Adapter<LocationsViewHolder> {

    private ArrayList<Destination> locations;

    public LocationsAdapter(ArrayList<Destination> locations) {
        this.locations = locations;
    }

    @Override
    public void onBindViewHolder(LocationsViewHolder holder, int position) {
        final Destination destination = locations.get(position);
        holder.updateUI(destination);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Load detail page
            }
        });
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    @Override
    public LocationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_location,parent,false);
        return new LocationsViewHolder(card);
    }
}
