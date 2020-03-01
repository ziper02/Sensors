package com.example.sensors.SensorsPackage;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sensors.MySensorsPackage.MySensor;
import com.example.sensors.Builder.MySensorBuilder;
import com.example.sensors.R;

public class Proximity extends MySensor implements SensorEventListener
{
    private TextView textViewProximity = (TextView)((AppCompatActivity)super.context).findViewById(R.id.textViewProximity);


    public Proximity(MySensorBuilder builder) {
        super(builder);
        editTextLog.setText(editTextLog.getText()+"Proximity Sensor start working\n");
        if(sensor==null)
        {
            textViewProximity.setText("Exception");
            editTextLog.setText(editTextLog.getText()+"Proximity Sensor is NOT working\n");
        }
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
        Log.d(super.getTAG(),"onSensorChanged : Proximity:"+ sensorEvent.values[0]);
        textViewProximity.setText(Float.toString(sensorEvent.values[0]));
    }
}
