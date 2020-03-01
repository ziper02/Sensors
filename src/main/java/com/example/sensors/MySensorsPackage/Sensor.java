package com.example.sensors.MySensorsPackage;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sensors.R;
import com.example.sensors.SensorItem;

public abstract class Sensor implements Cloneable, SensorItem
{
    private static final String TAG="MainActivity";
    public static String getTAG() {
        return TAG;
    }
}
