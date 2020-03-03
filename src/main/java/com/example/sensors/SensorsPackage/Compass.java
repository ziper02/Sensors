package com.example.sensors.SensorsPackage;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import com.example.sensors.MySensorsPackage.MySensor;
import com.example.sensors.Builder.MySensorBuilder;


public class Compass extends MySensor implements SensorEventListener
{

    protected float[] gravity;
    protected float[] geomagnetic;

    public Compass(MySensorBuilder builder)
    {
        super(builder);
        editTextLog.setText(editTextLog.getText()+"Compass Sensor start working\n");
        if(sensor==null || sensorTwo==null)
        {
            textView.setText("Exception");
            editTextLog.setText(editTextLog.getText()+"Compass Sensor is NOT working\n");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i)
    {

    }

    @Override
    public void showDetails()
    {
        Log.d(MySensor.getTAG(),"Sensor Name= Compass");
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent)
    {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            gravity = sensorEvent.values;

        if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            geomagnetic = sensorEvent.values;

        if (geomagnetic != null && gravity != null)
        {
            float R[] = new float[9];
            float I[] = new float[9];

            if (SensorManager.getRotationMatrix(R, I, gravity, geomagnetic))
            {
                float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation);
                Log.d(super.getTAG(),"onSensorChanged : Compass:"+(int)(Math.toDegrees(orientation[0]) + 360) % 360 + " degree");
                textView.setText((int)(Math.toDegrees(orientation[0]) + 360) % 360 + " degree");

            }
        }
    }
}
