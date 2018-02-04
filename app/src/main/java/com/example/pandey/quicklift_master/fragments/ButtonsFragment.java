package com.example.pandey.quicklift_master.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pandey.quicklift_master.R;
import com.example.pandey.quicklift_master.adapter.LocationsAdapter;
import com.example.pandey.quicklift_master.services.DataServices;
import com.example.pandey.quicklift_master.services.SourceDataService;

public class ButtonsFragment extends Fragment {

    public ButtonsFragment() {
        // Required empty public constructor
    }
    public static ButtonsFragment newInstance() {
        ButtonsFragment fragment = new ButtonsFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_locations_list, container, false);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_locations);
        recyclerView.setHasFixedSize(true);

        LocationsAdapter adapter = new LocationsAdapter(SourceDataService.getInstance().getDestinationsLocationWithn10Miles(786005));
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

}
