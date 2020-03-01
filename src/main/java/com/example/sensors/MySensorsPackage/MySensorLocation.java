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
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.example.sensors.Builder.MySensorLocationBuilder;
import com.example.sensors.R;
import com.example.sensors.SensorItem;




public abstract class MySensorLocation extends Sensor implements LocationListener,Cloneable, SensorItem  //Builder
{

    protected static  final int REQUEST_LOCATION=1;
    private static LocationManager locationManager = null;
    protected Location location;
    private Context context;
    protected TextView editTextLog;

    public MySensorLocation(MySensorLocationBuilder builder)
    {
        this.context=builder.getContext();
        editTextLog = (TextView)((AppCompatActivity)context).findViewById(R.id.editTextLog);
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

    protected Boolean getLocation()
    {
        Boolean result =false;
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
                editTextLog.setText(editTextLog.getText()+"None of location services are working\nCompass Sensor is not wokring\n");
            }
            else
                {
                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
                    LocationNetwork = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    Log.d("Network", "Network Enabled");
                    if(LocationNetwork!=null && location==null)
                    {
                        location = LocationNetwork;
                        editTextLog.setText(editTextLog.getText()+"Postion Sensor start working by Network\n");
                        result=true;
                    }

                }
                if (isGPSEnabled) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                    LocationGps = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    Log.d("GPS", "GPS Enabled");
                    if(LocationGps!=null && location==null) {
                        location = LocationGps;
                        editTextLog.setText(editTextLog.getText() + "Postion Sensor start working by GPS\n");
                        result=true;
                    }
                    }
                if (isPassiveEnabled) {
                    locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0, this);
                    LocationPassive = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
                    Log.d("Passive", "Passive Enabled");
                    if(LocationPassive!=null && location==null) {
                        location = LocationPassive;
                        editTextLog.setText(editTextLog.getText() + "Postion Sensor start working by Passive\n");
                        result=true;
                    }
                }
            }
            if(LocationGps==null && LocationNetwork==null && LocationPassive==null)
            {
                editTextLog.setText(editTextLog.getText()+"Failed to get location verfiay the auth of app\nCompass Sensor is not wokring\n");
            }
        }
        return result;
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
