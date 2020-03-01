package com.example.sensors.SensorsPackage;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

import com.example.sensors.MySensorsPackage.MySensor;
import com.example.sensors.Builder.MySensorBuilder;

public class Proximity extends MySensor implements SensorEventListener
{


    public Proximity(MySensorBuilder builder) {
        super(builder);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i)
    {

    }

    @Override
    public void showDetails()
    {
        Log.d(MySensor.getTAG(),"Sensor Name= Proximity");
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent)
    {
        //Log.d(super.getTAG(),"onSensorChanged : Proximity:"+ sensorEvent.values[0]);
    }
}
