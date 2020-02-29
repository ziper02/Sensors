package com.example.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;


public abstract class MySensor implements SensorEventListener,Cloneable,SensorItem //Builder,Prototype,Factory(With Abstract class)
{


    private static final String TAG="MainActivity";

    private static SensorManager sensorManager;
    private Sensor sensor;



    public MySensor(MySensorBuilder builder)
    {

        this.sensorManager=(SensorManager)builder.getContext().getSystemService(Context.SENSOR_SERVICE);
        this.sensor=sensorManager.getDefaultSensor(builder.getSensor());

    }




    public static String getTAG() {
        return TAG;
    }


    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }

    @Override
    public void showDetails()
    {
        Log.d(TAG,"Sensor Name="+sensor.getName());
    }

    public void activeSensor()
    {
        sensorManager.registerListener(MySensor.this,this.sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public abstract void onSensorChanged(SensorEvent event);

    @Override
    public abstract void onAccuracyChanged(Sensor sensor, int accuracy);
}
