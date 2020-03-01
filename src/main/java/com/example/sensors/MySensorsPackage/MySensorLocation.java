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
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;


import androidx.core.app.ActivityCompat;


import com.example.sensors.Builder.MySensorLocationBuilder;

import com.example.sensors.SensorItem;




public class MySensorLocation extends Sensor implements LocationListener,Cloneable, SensorItem  //Builder
{

    protected static  final int REQUEST_LOCATION=1;
    private static LocationManager locationManager = null;
    protected Location location;
    private Context context;

    public MySensorLocation(MySensorLocationBuilder builder)
    {
        this.context=builder.getContext();
        ActivityCompat.requestPermissions((Activity)context,new String[]
            {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        ActivityCompat.requestPermissions((Activity)context,new String[]
                {Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_LOCATION);
        if (locationManager == null)
            this.locationManager = (LocationManager) builder.getContext().getSystemService(Context.LOCATION_SERVICE);

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            OnGPS();
        }
        else
        {
            getLocation();
        }
    }




    protected void OnGPS() {

        final AlertDialog.Builder builder= new AlertDialog.Builder(context);

        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        final AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    protected void getLocation()
    {
        if (ActivityCompat.checkSelfPermission(context,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) !=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions((Activity)context,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
        else {
            // getting  status
            Boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            Boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            Boolean isPassiveEnabled = locationManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER);
            Location LocationGps = null;
            Location LocationNetwork = null;
            Location LocationPassive = null;
            if (!isGPSEnabled && !isNetworkEnabled && !isPassiveEnabled)
            {
                Toast.makeText(context, "Can't Get Your Location", Toast.LENGTH_SHORT).show();
            }
            else
                {
                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
                    LocationNetwork = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    Log.d("Network", "Network Enabled");
                    if(LocationNetwork!=null)
                        location = LocationNetwork;

                }
                if (isGPSEnabled) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                    LocationGps = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    Log.d("GPS", "GPS Enabled");
                    if(LocationGps!=null)
                        location = LocationGps;
                    }
                if (isPassiveEnabled) {
                    locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0, this);
                    LocationPassive = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
                    Log.d("Passive", "Passive Enabled");
                    if(LocationPassive!=null)
                        location = LocationPassive;
                }
            }
            if(LocationGps==null && LocationNetwork==null && LocationPassive==null)
            {
                //TODO
            }
        }
    }

    @Override
    public void onLocationChanged(Location location)
    {

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


    public Context getContext() {
        return context;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}
