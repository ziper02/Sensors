package com.example.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.util.Log;

import com.example.sensors.SensorsPackage.Light;
import com.example.sensors.SensorsPackage.Pressure;
import com.example.sensors.SensorsPackage.Proximity;

import java.util.ArrayList;
import java.util.List;

public class Sensors implements SensorItem //For Composite and Facade
{

    private List<SensorItem> sensors=new ArrayList<SensorItem>();

    private Light light;
    private Proximity proximity;
    private Pressure pressure;

    public Sensors(Context context)
    {
        createAllSensors(context);
        compositeAllSensors();
    }


    private void createAllSensors(Context context)
    {
        Log.d(MySensor.getTAG(),"Create all objects of Sensors");
        this.light=(Light)new MySensorBuilder().sensor(Sensor.TYPE_LIGHT).context(context).build();
        this.proximity=(Proximity)new MySensorBuilder().sensor(Sensor.TYPE_PROXIMITY).context(context).build();
        this.pressure=(Pressure)new MySensorBuilder().sensor(Sensor.TYPE_PRESSURE).context(context).build();
    }

    private void compositeAllSensors()
    {
        Log.d(MySensor.getTAG(),"Compositing all Sensors");
        sensors.add(light);
        sensors.add(proximity);
        sensors.add(pressure);
    }
    public void add(SensorItem sensorItem)
    {
        this.sensors.add(sensorItem);
    }

    public void remove(SensorItem sensorItem)
    {
        this.sensors.remove(sensorItem);
    }

    @Override
    public void showDetails() {
        Log.d(MySensor.getTAG(),"The Details of all sensors : \n");
        for(SensorItem sensorItem: sensors)
            sensorItem.showDetails();
    }

    @Override
    public void activeSensor() {
        for(SensorItem sensorItem: sensors)
            sensorItem.activeSensor();
    }

}
