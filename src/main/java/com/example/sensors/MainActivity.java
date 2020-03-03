
//Design Patters that implemented: Builder,Prototype(But didnt use it),Composite,Facade,factory

package com.example.sensors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;




public class MainActivity extends AppCompatActivity
{

    public static AppCompatActivity context;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;

        startService();

    }



    public void startService()
    {
        Intent serviceIntent = new Intent(this, SensorService.class);
        startService(serviceIntent);
    }



    public void stopService(View v) {
        Intent serviceIntent = new Intent(this, SensorService.class);
        stopService(serviceIntent);

        // exit from the activity
        ActivityCompat.finishAffinity(this);
        System.exit(0);

    }

}
