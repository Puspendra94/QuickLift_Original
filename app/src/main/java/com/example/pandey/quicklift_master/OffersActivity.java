package com.example.pandey.quicklift_master;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;

public class OffersActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent profile = new Intent(OffersActivity.this,ProfileActivity.class);
            startActivity(profile);
            finish();
        } else if (id == R.id.nav_rides) {
            Intent rides = new Intent(OffersActivity.this,RideActivity.class);
            startActivity(rides);
            finish();
        } else if (id == R.id.nav_free_rides) {
            Intent free_ride = new Intent(OffersActivity.this,FreeRideActivity.class);
            startActivity(free_ride);
            finish();
        } else if (id == R.id.nav_notif) {
            Intent notif = new Intent(OffersActivity.this,NotifActivity.class);
            startActivity(notif);
            finish();
        } else if (id == R.id.nav_offers) {
            Intent offers = new Intent(OffersActivity.this,OffersActivity.class);
            startActivity(offers);
            finish();
        } else if (id == R.id.nav_about) {
            Intent offers = new Intent(OffersActivity.this,AboutActivity.class);
            startActivity(offers);
            finish();
        } else if (id == R.id.nav_terms_cond) {
            Intent terms = new Intent(OffersActivity.this,TermsActivity.class);
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

        }else if (id == R.id.nav_home){
            Intent home = new Intent(OffersActivity.this, MainActivity.class);
            startActivity(home);
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
