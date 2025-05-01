package com.example.fitnesstrackerapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor stepSensor;
    private boolean isSensorPresent = false;
    private float totalSteps = 0;

    TextView textSteps, textDistance, textCalories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textSteps = findViewById(R.id.textSteps);
        textDistance = findViewById(R.id.textDistance);
        textCalories = findViewById(R.id.textCalories);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isSensorPresent = true;
        } else {
            textSteps.setText("Step Counter Sensor Not Available");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (isSensorPresent) {
            totalSteps = event.values[0];
            textSteps.setText("Steps: " + (int) totalSteps);

            float distance = totalSteps * 0.8f;  // 1 step ≈ 0.8 meters
            float calories = totalSteps * 0.04f; // 1 step ≈ 0.04 kcal

            textDistance.setText("Distance: " + String.format("%.2f", distance) + " m");
            textCalories.setText("Calories: " + String.format("%.2f", calories) + " kcal");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    @Override
    protected void onResume() {
        super.onResume();
        if (stepSensor != null)
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (stepSensor != null)
            sensorManager.unregisterListener(this);
    }
}
