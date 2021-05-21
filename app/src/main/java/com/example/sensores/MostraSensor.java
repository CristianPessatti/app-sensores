package com.example.sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MostraSensor extends AppCompatActivity implements SensorEventListener {

    SensorManager mSensorManager;
    Sensor mSensor;
    TextView tvName, tvValues;
    int typeSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostra_sensor);

        Bundle bundle = getIntent().getExtras();
        typeSensor = bundle.getInt("sensorType");

        tvName = findViewById(R.id.tvName);
        tvValues = findViewById(R.id.tvValues);

        mSensorManager = (SensorManager) getSystemService(getApplicationContext().SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(typeSensor);
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);

        tvName.setText(mSensor.getName());
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        StringBuilder valores = new StringBuilder();

        for ( Float valor : event.values ) {
            valores.append(Float.toString(valor)).append(System.getProperty("line.separator"));
        }

        tvValues.setText(valores);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}