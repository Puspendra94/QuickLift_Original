package com.example.pandey.quicklift_master.fragments;

import java.util.ArrayList;

import com.example.pandey.quicklift_master.model.Destination;
import com.example.pandey.quicklift_master.services.*;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pandey.quicklift_master.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    private MarkerOptions userMarker;
    private LocationsListFragment mListFragment;
    private ButtonsFragment mButtonsFragment;
    private EditText source,dest;
    private View view;
    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main, container, false);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mListFragment = (LocationsListFragment) getActivity()
                .getSupportFragmentManager()
                .findFragmentById(R.id.container_locations_list);

        mButtonsFragment = (ButtonsFragment)getActivity()
                .getSupportFragmentManager()
                .findFragmentById(R.id.container_locations_list);

        if (mListFragment == null){
            mListFragment = LocationsListFragment.newInstance();
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_locations_list,mListFragment)
                    .commit();
        }

        if (mButtonsFragment == null){
            mButtonsFragment = ButtonsFragment.newInstance();
        }

        source = (EditText)view.findViewById(R.id.source);
        dest = (EditText) view.findViewById(R.id.destination);

        source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_locations_list,mButtonsFragment)
                        .commit();
                showList(mButtonsFragment);
            }
        });

        dest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_locations_list,mListFragment)
                        .commit();
                showList(mListFragment);
            }
        });

        hideList(mListFragment);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        mMap.setMyLocationEnabled(true);
    }


    public void setUserMarker(LatLng latLng){
        if (userMarker == null){
            userMarker = new MarkerOptions().position(latLng).title("Current Location");
            mMap.addMarker(userMarker);
            Log.v("DONKEY","Current Location : "+latLng.latitude+" - "+latLng.longitude);
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,17));
    }

    private void hideList(Fragment fragment){
        getActivity().getSupportFragmentManager().beginTransaction().hide(fragment).commit();
    }

    private void showList(Fragment fragment){
        getActivity().getSupportFragmentManager().beginTransaction().show(fragment).commit();
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
