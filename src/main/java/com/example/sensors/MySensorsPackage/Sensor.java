package com.example.sensors.MySensorsPackage;

import com.example.sensors.SensorItem;

public abstract class Sensor implements Cloneable, SensorItem
{
    private static final String TAG="MainActivity";

    public static String getTAG() {
        return TAG;
    }
}
