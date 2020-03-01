package com.example.sensors.Builder;

import android.content.Context;
import android.hardware.Sensor;

import com.example.sensors.MySensorsPackage.MySensor;
import com.example.sensors.SensorsPackage.Compass;
import com.example.sensors.SensorsPackage.Light;
import com.example.sensors.SensorsPackage.Pressure;
import com.example.sensors.SensorsPackage.Proximity;

public class MySensorBuilder //Builder
{
    private int sensor;
    private Context context;
    private int sensorTwo=-1;

    public MySensor build()//The builder and also using for Factory for create Object
    {
        if(sensor==Sensor.TYPE_LIGHT)
            return new Light(this);
        else if(sensor==Sensor.TYPE_PROXIMITY)
            return new Proximity(this);
        else if (sensor==Sensor.TYPE_PRESSURE)
            return new Pressure(this);
        else if((sensor== Sensor.TYPE_ACCELEROMETER && sensorTwo ==Sensor.TYPE_MAGNETIC_FIELD )||
                (sensor== Sensor.TYPE_MAGNETIC_FIELD && sensorTwo ==Sensor.TYPE_ACCELEROMETER ))
            return new Compass(this);
        else
            return null;
    }

    public MySensorBuilder sensor(int sensor)
    {
        this.sensor=sensor;
        return this;
    }

    public MySensorBuilder sensorTwo(int sensorTwo)
    {
        this.sensorTwo=sensorTwo;
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

    public int getSensorTwo() {
        return sensorTwo;
    }

    public Context getContext() {
        return context;
    }

}
