package com.example.sensors.SensorsPackage;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.location.Location;
import android.location.LocationListener;

import android.os.Bundle;
import android.util.Log;

import com.example.sensors.Builder.MySensorLocationBuilder;
import com.example.sensors.MySensorsPackage.MySensor;
import com.example.sensors.MySensorsPackage.MySensorLocation;

public class Postion extends MySensorLocation implements LocationListener
{



    public Postion(MySensorLocationBuilder builder)
    {
        super(builder);
    }


    @Override
    public void showDetails()
    {
        Log.d(MySensor.getTAG(),"Sensor Name= Postion");
    }



    @Override
    public void onLocationChanged(Location location)
    {

        Log.d(getTAG(),"onLocationChanged Longitude: " + location.getLongitude() + "Latitude: "+ location.getLatitude());

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
