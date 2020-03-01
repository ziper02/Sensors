package com.example.sensors.SensorsPackage;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

import com.example.sensors.MySensorsPackage.MySensor;
import com.example.sensors.Builder.MySensorBuilder;

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
    public void showDetails()
    {
        Log.d(MySensor.getTAG(),"Sensor Name= Light");
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent)
    {
        //Log.d(super.getTAG(),"onSensorChanged : Light:"+ sensorEvent.values[0]);
    }

}
