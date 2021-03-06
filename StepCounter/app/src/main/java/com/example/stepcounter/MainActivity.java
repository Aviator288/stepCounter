package com.example.stepcounter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
SensorManager sensorManager;
TextView tv_info;
boolean running = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_info = (TextView) findViewById(R.id.tv_info);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

    }
    protected void onResume(){
        super.onResume();
        running = false;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor != null){
          sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        }else{
            Toast.makeText(this, "  Sensor Not found", Toast.LENGTH_SHORT).show();
        }
    }
    protected void onPause(){
        super.onPause();
        running = false;
        //if you unregister the hardware will stop detecting steps
        //sensorManager.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
      if(running){
          tv_info.setText(String.valueOf(event.values[0]));
      }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
