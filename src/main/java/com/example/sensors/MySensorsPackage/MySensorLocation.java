package com.example.sensors.MySensorsPackage;

import android.Manifest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;


import androidx.core.app.ActivityCompat;

import com.example.sensors.Builder.MySensorLocationBuilder;
import com.example.sensors.SensorItem;

import static androidx.core.content.ContextCompat.checkSelfPermission;


public class MySensorLocation extends Sensor implements LocationListener,Cloneable, SensorItem  //Builder
{
    private static LocationManager locationManager = null;
    private Location location;
    private Context context;
    public MySensorLocation(MySensorLocationBuilder builder)
    {
        this.context=builder.getContext();
        if (locationManager == null)
            this.locationManager = (LocationManager) builder.getContext().getSystemService(Context.LOCATION_SERVICE);

        Boolean bool= checkSelfPermission(builder.getContext(),Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED;
        Boolean bool2=  checkSelfPermission(context,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED;


        if (ActivityCompat.checkSelfPermission(context,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //Any number can be used

            //return;
        }
        this.location = locationManager.getLastKnownLocation(builder.getLocation());


    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        context.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
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

    @Override
    public void showDetails()
    {
        Log.d(getTAG(),"Sensor Name="+location);
    }

    @Override
    public void activeSensor() {
        onLocationChanged(location);
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}
