package com.example.sensors;

import android.content.Context;
import android.hardware.Sensor;

import com.example.sensors.SensorsPackage.Light;
import com.example.sensors.SensorsPackage.Pressure;
import com.example.sensors.SensorsPackage.Proximity;

public class MySensorBuilder //Builder
{
    private int sensor;
    private Context context;


    public MySensor build()//The builder and also using for Factory for create Object
    {
        if(sensor==Sensor.TYPE_LIGHT)
            return new Light(this);
        else if(sensor==Sensor.TYPE_PROXIMITY)
            return new Proximity(this);
        else
            return new Pressure(this);
    }

    public MySensorBuilder sensor(int sensor)
    {
        this.sensor=sensor;
        return this;
    }

    public MySensorBuilder context(Context context)
    {
        this.context=context;
        return this;
    }


    public int getSensor() {
        return sensor;
    }


    public Context getContext() {
        return context;
    }

}
