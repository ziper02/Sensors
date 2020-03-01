package com.example.sensors.MySensorsPackage;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sensors.Builder.MySensorBuilder;
import com.example.sensors.R;
import com.example.sensors.SensorItem;


public abstract class MySensor extends com.example.sensors.MySensorsPackage.Sensor implements SensorEventListener //Builder,Prototype,Factory(With Abstract class)
{



    protected static SensorManager sensorManager=null;
    protected Sensor sensor=null;
    protected Sensor sensorTwo=null;
    protected Context context;
    protected TextView editTextLog;


    public MySensor(MySensorBuilder builder)
    {
        if(sensorManager==null)
            this.sensorManager=(SensorManager)builder.getContext().getSystemService(Context.SENSOR_SERVICE);
        this.context=builder.getContext();
        this.sensor=sensorManager.getDefaultSensor(builder.getSensor());
        if(builder.getSensorTwo()!=-1)
            this.sensorTwo=sensorManager.getDefaultSensor(builder.getSensorTwo());
        editTextLog = (TextView)((AppCompatActivity)context).findViewById(R.id.editTextLog);
    }






    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }

    @Override
    public void showDetails()
    {
        Log.d(getTAG(),"Sensor Name="+sensor.getName());
    }

    public void activeSensor()
    {
        sensorManager.registerListener(MySensor.this,this.sensor,SensorManager.SENSOR_DELAY_NORMAL);
        if(sensorTwo!=null)
            sensorManager.registerListener(MySensor.this,this.sensorTwo,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public abstract void onSensorChanged(SensorEvent event);

    @Override
    public abstract void onAccuracyChanged(Sensor sensor, int accuracy);
}
