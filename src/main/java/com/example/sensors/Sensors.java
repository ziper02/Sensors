package com.example.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.location.LocationManager;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sensors.Builder.MySensorBuilder;
import com.example.sensors.Builder.MySensorLocationBuilder;
import com.example.sensors.MySensorsPackage.MySensor;
import com.example.sensors.SensorsPackage.Compass;
import com.example.sensors.SensorsPackage.Light;
import com.example.sensors.SensorsPackage.Postion;
import com.example.sensors.SensorsPackage.Pressure;
import com.example.sensors.SensorsPackage.Proximity;

import java.util.ArrayList;
import java.util.List;

public class Sensors  //For Composite and Facade
{
    private List<SensorItem> sensors=new ArrayList<SensorItem>();

    private Light light;
    private Proximity proximity;
    private Pressure pressure;
    private Compass compass;
    private Postion postion;

    public Sensors(Context context)
    {
        createAllSensors(context);
        compositeAllSensors();
        activeSensor();
    }




    private void createAllSensors(Context context)
    {
        Log.d(MySensor.getTAG(),"Create all objects of Sensors");
        this.light=(Light)new MySensorBuilder().sensor(Sensor.TYPE_LIGHT).context(context).textView((TextView)((AppCompatActivity)context).findViewById(R.id.textViewLight)).build();
        this.proximity=(Proximity)new MySensorBuilder().sensor(Sensor.TYPE_PROXIMITY).textView((TextView)((AppCompatActivity)context).findViewById(R.id.textViewProximity)).context(context).build();
        this.pressure=(Pressure)new MySensorBuilder().sensor(Sensor.TYPE_PRESSURE).textView((TextView)((AppCompatActivity)context).findViewById(R.id.textViewPressure)).context(context).build();
        this.compass=(Compass)new MySensorBuilder().sensor(Sensor.TYPE_MAGNETIC_FIELD).textView((TextView)((AppCompatActivity)context).findViewById(R.id.textViewCompass))
                .sensorTwo(Sensor.TYPE_ACCELEROMETER).context(context).build();
        this.postion=(Postion)new MySensorLocationBuilder().textView((TextView)((AppCompatActivity)context).findViewById(R.id.textViewLatitude))
                .textViewTwo((TextView)((AppCompatActivity)context).findViewById(R.id.textViewLongitude)).location(LocationManager.NETWORK_PROVIDER).context(context).build();
    }

    private void compositeAllSensors()
    {
        Log.d(MySensor.getTAG(),"Compositing all Sensors");
        sensors.add(light);
        sensors.add(proximity);
        sensors.add(pressure);
        sensors.add(compass);
        sensors.add(postion);
    }
    public void add(SensorItem sensorItem)
    {
        this.sensors.add(sensorItem);
    }

    public void remove(SensorItem sensorItem)
    {
        this.sensors.remove(sensorItem);
    }


    public void showDetails() {
        Log.d(MySensor.getTAG(),"The Details of all sensors : \n");
        for(SensorItem sensorItem: sensors)
            sensorItem.showDetails();
    }


    public void activeSensor()
    {
        Log.d(MySensor.getTAG(),"Activate all Sensors");
        for(SensorItem sensorItem: sensors)
            sensorItem.activeSensor();
    }

}
