package com.example.sensors.SensorsPackage;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sensors.MySensorsPackage.MySensor;
import com.example.sensors.Builder.MySensorBuilder;
import com.example.sensors.R;

public class Light extends MySensor implements SensorEventListener
{

    private TextView textViewLight = (TextView)((AppCompatActivity)super.context).findViewById(R.id.textViewLight);

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
        Log.d(super.getTAG(),"onSensorChanged : Light:"+ sensorEvent.values[0]);

        textViewLight.setText(Float.toString(sensorEvent.values[0]));
    }

}
