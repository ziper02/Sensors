package com.example.sensors.SensorsPackage;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

import com.example.sensors.MySensor;
import com.example.sensors.MySensorBuilder;

public class Light extends MySensor implements SensorEventListener
{


    public Light(MySensorBuilder builder) {
        super(builder);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i)
    {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent)
    {
        Log.d(super.getTAG(),"onSensorChanged : Light:"+ sensorEvent.values[0]);
    }
}
