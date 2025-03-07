package com.example.sensors.SensorsPackage;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Handler;
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
        if(location!=null)
        {
            Log.d(getTAG(), "onLocationChanged Longitude: " + location.getLongitude() + "Latitude: " + location.getLatitude());
            textView.setText(String.format("%.3f",location.getLatitude()));
            textViewTwo.setText(String.format("%.3f",location.getLongitude()));
        }
        else
        {
            textView.setText("Exception");
            textViewTwo.setText("Exception");
            editTextLog.setText(editTextLog.getText()+"Postion Sensor is NOT wokring\n");
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!getLocation())
                        handler.postDelayed(this, 4000);
                    else
                        handler.removeCallbacksAndMessages(null);
                }
            }, 4000);

        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        getLocation();

    }

    @Override
    public void onProviderEnabled(String provider) {
        getLocation();

    }

    @Override
    public void onProviderDisabled(String provider) {
        getLocation();
    }


}
