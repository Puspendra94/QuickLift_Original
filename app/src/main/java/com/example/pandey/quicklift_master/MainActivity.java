package com.example.pandey.quicklift_master;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pandey.quicklift_master.fragments.MainFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import java.io.File;


public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener
        ,GoogleApiClient.ConnectionCallbacks
        , com.google.android.gms.location.LocationListener
        ,NavigationView.OnNavigationItemSelectedListener
        ,View.OnClickListener{

    final int PERMISION_LOCATION = 111;
    private GoogleApiClient mGoogleApiClient;
    private MainFragment mainFragment;
//    private static TextView fare;
//    private static Button ride,confirm,bike,car,shareCar,auto,shareAuto,rickshaw,shareRickshaw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
        mainFragment = (MainFragment)getSupportFragmentManager().findFragmentById(R.id.container_main);
        if (mainFragment == null){
            mainFragment = MainFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_main,mainFragment)
                    .commit();
        }

//        bike = (Button)findViewById(R.id.bike);
//        //bike.setOnClickListener(this);
//
//        car = (Button)findViewById(R.id.car);
//        //car.setOnClickListener(this);
//
//        shareCar = (Button)findViewById(R.id.shareCar);
//        //shareCar.setOnClickListener(this);
//
//        auto = (Button)findViewById(R.id.auto);
//        //auto.setOnClickListener(this);
//
//        shareAuto = (Button)findViewById(R.id.shareAuto);
//        //shareAuto.setOnClickListener(this);
//
//        rickshaw = (Button)findViewById(R.id.rickshaw);
//        //rickshaw.setOnClickListener(this);
//
//        shareRickshaw = (Button)findViewById(R.id.shareRickshaw);
//        //shareRickshaw.setOnClickListener(this);
//
//        fare = (TextView)findViewById(R.id.fare_main);
//        ride = (Button)findViewById(R.id.ride_button_main);
//        //ride.setOnClickListener(this);
//        confirm = (Button)findViewById(R.id.confirm_main);
//        //confirm.setOnClickListener(this);

        Toolbar toolbar = (Toolbar  ) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},PERMISION_LOCATION);
            Log.v("DONKEY","requesting permissions");
        }else{
            Log.v("DONKEY","Starting Location Services from onConnected");
            startLocationServices();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.v("DONKEY","Long : "+location.getLongitude()+" - Lat : "+location.getLatitude());
        mainFragment.setUserMarker(new LatLng(location.getLatitude(),location.getLongitude()));
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
//        ride.setVisibility(View.GONE);
//        fare.setVisibility(View.GONE);
//        confirm.setVisibility(View.GONE);
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISION_LOCATION:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    startLocationServices();
                    Log.v("DONKEY","permissions granted and starting location services from onRequestPermissionsGranted");
                }else{
                    Log.v("DONKEY","permissions not granted");
                }
            }
        }
    }

    public void startLocationServices(){
        Log.v("DONKEY","Starting Location Services Called");

        try{
            LocationRequest req = LocationRequest
                    .create()
                    .setPriority(LocationRequest.PRIORITY_LOW_POWER);
                    //.setInterval(30000)
                    //.setFastestInterval(30000);
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, req, this);
            Log.v("DONKEY","requesting location updates");
        }catch(SecurityException exeption){
            Log.v("DONKEY",exeption.toString());
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent profile = new Intent(MainActivity.this,ProfileActivity.class);
            startActivity(profile);
        } else if (id == R.id.nav_rides) {
            Intent rides = new Intent(MainActivity.this,RideActivity.class);
            startActivity(rides);
        } else if (id == R.id.nav_free_rides) {
            Intent free_ride = new Intent(MainActivity.this,FreeRideActivity.class);
            startActivity(free_ride);
            finish();
        } else if (id == R.id.nav_notif) {
            Intent notif = new Intent(MainActivity.this,NotifActivity.class);
            startActivity(notif);
            finish();
        } else if (id == R.id.nav_offers) {
            Intent offers = new Intent(MainActivity.this,OffersActivity.class);
            startActivity(offers);
            finish();
        } else if (id == R.id.nav_about) {
            Intent offers = new Intent(MainActivity.this,AboutActivity.class);
            startActivity(offers);
            finish();
        } else if (id == R.id.nav_terms_cond) {
            Intent terms = new Intent(MainActivity.this,TermsActivity.class);
            startActivity(terms);
            finish();
        }  else if (id == R.id.nav_share) {
            ApplicationInfo app = getApplicationContext().getApplicationInfo();
            String filePath = app.sourceDir;
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("application/vnd.android.package-archive");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(filePath)));
            startActivity(Intent.createChooser(intent, "Share app using :"));

        } else if (id == R.id.nav_logout) {
//            if (getIntent().getStringExtra("phone") !=  null){
//                AuthUI.getInstance().signOut(this)
//                        .addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                startActivity(new Intent(MainActivity.this,LoginActivity.class));
//                                finish();
//                            }
//                        });
//            }else{
//                Toast.makeText(this, "phone is empty", Toast.LENGTH_SHORT).show();
//            }
        }else if (id == R.id.nav_home){
            Intent home = new Intent(MainActivity.this, MainActivity.class);
            startActivity(home);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {

//        int id = view.getId();
//        switch (id) {
//            case R.id.bike:
//                Log.i("clicked Bike", "Hide fragment");
//                //transaction.detach(two);
//                //transaction.commit();
//                bike.setEnabled(false);
//                car.setEnabled(true);
//                shareCar.setEnabled(true);
//                auto.setEnabled(true);
//                shareAuto.setEnabled(true);
//                rickshaw.setEnabled(true);
//                shareRickshaw.setEnabled(true);
//                ride.setVisibility(View.VISIBLE);
//                fare.setVisibility(View.GONE);
//                confirm.setVisibility(View.GONE);
//                break;
//            case R.id.car:
//                bike.setEnabled(true);
//                car.setEnabled(false);
//                shareCar.setEnabled(true);
//                auto.setEnabled(true);
//                shareAuto.setEnabled(true);
//                rickshaw.setEnabled(true);
//                shareRickshaw.setEnabled(true);
//                ride.setVisibility(View.VISIBLE);
//                fare.setVisibility(View.GONE);
//                confirm.setVisibility(View.GONE);
//                break;
//            case R.id.shareCar:
//                bike.setEnabled(true);
//                car.setEnabled(true);
//                shareCar.setEnabled(false);
//                auto.setEnabled(true);
//                shareAuto.setEnabled(true);
//                rickshaw.setEnabled(true);
//                shareRickshaw.setEnabled(true);
//                ride.setVisibility(View.VISIBLE);
//                fare.setVisibility(View.GONE);
//                confirm.setVisibility(View.GONE);
//                break;
//            case R.id.auto:
//                bike.setEnabled(true);
//                car.setEnabled(true);
//                shareCar.setEnabled(true);
//                auto.setEnabled(false);
//                shareAuto.setEnabled(true);
//                rickshaw.setEnabled(true);
//                shareRickshaw.setEnabled(true);
//                ride.setVisibility(View.VISIBLE);
//                fare.setVisibility(View.GONE);
//                confirm.setVisibility(View.GONE);
//                break;
//            case R.id.shareAuto:
//                bike.setEnabled(true);
//                car.setEnabled(true);
//                shareCar.setEnabled(true);
//                auto.setEnabled(true);
//                shareAuto.setEnabled(false);
//                rickshaw.setEnabled(true);
//                shareRickshaw.setEnabled(true);
//                ride.setVisibility(View.VISIBLE);
//                fare.setVisibility(View.GONE);
//                confirm.setVisibility(View.GONE);
//                break;
//            case R.id.rickshaw:
//                bike.setEnabled(true);
//                car.setEnabled(true);
//                shareCar.setEnabled(true);
//                auto.setEnabled(true);
//                shareAuto.setEnabled(true);
//                rickshaw.setEnabled(false);
//                shareRickshaw.setEnabled(true);
//                ride.setVisibility(View.VISIBLE);
//                fare.setVisibility(View.GONE);
//                confirm.setVisibility(View.GONE);
//                break;
//            case R.id.shareRickshaw:
//                bike.setEnabled(true);
//                car.setEnabled(true);
//                shareCar.setEnabled(true);
//                auto.setEnabled(true);
//                shareAuto.setEnabled(true);
//                rickshaw.setEnabled(true);
//                shareRickshaw.setEnabled(false);
//                ride.setVisibility(View.VISIBLE);
//                fare.setVisibility(View.GONE);
//                confirm.setVisibility(View.GONE);
//                break;
//            case R.id.ride_button_main:
//                Log.i("ride button", "ride button clicked");
//                ride.setVisibility(View.GONE);
//                fare.setVisibility(View.VISIBLE);
//                confirm.setVisibility(View.VISIBLE);
//                break;
//            default:
//                bike.setEnabled(true);
//                car.setEnabled(true);
//                shareCar.setEnabled(true);
//                auto.setEnabled(true);
//                shareAuto.setEnabled(true);
//                rickshaw.setEnabled(true);
//                shareRickshaw.setEnabled(false);
//                ride.setVisibility(View.GONE);
//                fare.setVisibility(View.GONE);
//                confirm.setVisibility(View.GONE);
//        }
    }
}
