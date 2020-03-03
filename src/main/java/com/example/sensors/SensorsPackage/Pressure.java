package com.example.sensors.SensorsPackage;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;


import com.example.sensors.MySensorsPackage.MySensor;
import com.example.sensors.Builder.MySensorBuilder;


public class Pressure extends MySensor implements SensorEventListener
{


    public Pressure(MySensorBuilder builder) {
        super(builder);
        editTextLog.setText(editTextLog.getText()+"Pressure Sensor start working\n");
        if(sensor==null)
        {
            textView.setText("Exception");
            editTextLog.setText(editTextLog.getText()+"Pressure Sensor is NOT working\n");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i)
    {

    }

    @Override
    public void showDetails()
    {
        Log.d(MySensor.getTAG(),"Sensor Name= Pressure");
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent)
    {
        Log.d(super.getTAG(),"onSensorChanged : Pressure:"+ sensorEvent.values[0]);
        textView.setText(Float.toString(sensorEvent.values[0]));
    }
}
