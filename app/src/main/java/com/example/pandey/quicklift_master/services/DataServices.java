package com.example.pandey.quicklift_master.services;

import com.example.pandey.quicklift_master.model.Destination;

import java.util.ArrayList;

/**
 * Created by pandey on 1/2/18.
 */

public class DataServices {
    private static final DataServices instance = new DataServices();

    public static DataServices getInstance() {
        return instance;
    }

    private DataServices() {
    }

    public ArrayList<Destination> getDestinationsLocationWithn10Miles(int zipCode){
        ArrayList<Destination> list = new ArrayList<>();
        list.add(new Destination(27.467f,94.953f,"Post Office","hanuman singhania road dibrugarh - 786001 assam","image"));
        list.add(new Destination(27.4728537f,94.9119411f,"Polytechnic","Dibrugarh Polytechnic,Dibrugarh,Lahoal,Assam","image"));
        list.add(new Destination(27.4728527f,94.9119421f,"Dibrugarh University","Dibrugarh University,Dibrugarh,786004,Assam","image"));
        list.add(new Destination(26.696538f,92.835030f,"Tezpur University","Tezpur University, Napaam, Napaam, Sonitpur, Assam 784028","image"));
        return list;
    }
}
