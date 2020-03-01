
//Design Patters that implemented: Builder,Prototype(But didnt use it),Composite,Facade
//Need to check factory
package com.example.sensors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import android.Manifest;
import android.app.Activity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Sensors sensors=new Sensors(this);

    }


}
