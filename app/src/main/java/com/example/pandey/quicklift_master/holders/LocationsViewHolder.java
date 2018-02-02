package com.example.pandey.quicklift_master.holders;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pandey.quicklift_master.R;
import com.example.pandey.quicklift_master.model.Destination;

/**
 * Created by pandey on 1/2/18.
 */

public class LocationsViewHolder extends RecyclerView.ViewHolder {

    private ImageView locationImage;
    private TextView locationTitle;
    private TextView locationAddress;

    public LocationsViewHolder(View itemView) {
        super(itemView);
        locationImage = (ImageView)itemView.findViewById(R.id.locationImage);
        locationTitle = (TextView)itemView.findViewById(R.id.locactionTitle);
        locationAddress = (TextView)itemView.findViewById(R.id.locationAddress);
    }

    public void updateUI(Destination destinations){
        String uri = destinations.getImageURL();
        int resource = locationImage.getResources().getIdentifier(uri,null,locationImage.getContext().getPackageName());
        locationImage.setImageResource(resource);
        locationTitle.setText(destinations.getLocationTitle());
        locationAddress.setText(destinations.getLocationAddress());
    }
}
