package com.example.sensors.Builder;


import android.content.Context;
import android.location.LocationManager;

import com.example.sensors.MySensorsPackage.MySensorLocation;
import com.example.sensors.SensorsPackage.Postion;


public class MySensorLocationBuilder //Builder
{
    private String location;
    private Context context;

    public MySensorLocation build()//The builder and also using for Factory for create Object
    {
        if(location.equals(LocationManager.NETWORK_PROVIDER))
            return new Postion(this);
        else
            return null;
    }

    public MySensorLocationBuilder location(String location)
    {
        this.location=location;
        return this;
    }


    public MySensorLocationBuilder context(Context context)
    {
        this.context=context;
        return this;
    }


    public String getLocation() {
        return location;
    }


    public Context getContext() {
        return context;
    }

}
