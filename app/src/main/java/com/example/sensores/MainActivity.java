package com.example.sensores;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listSensors;
    SensorManager mSensorManager;
    List<Sensor> listSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listSensors = findViewById(R.id.listSensors);

        mSensorManager = (SensorManager) getSystemService(getApplicationContext().SENSOR_SERVICE);

        listSensor = mSensorManager.getSensorList(Sensor.TYPE_ALL);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onStart() {
        super.onStart();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                getSensorsNames()
        );

        listSensors.setAdapter(adapter);

        listSensors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mostraSensor(position);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ArrayList<String> getSensorsNames() {
        ArrayList<String> result = new ArrayList<>();

        for ( Sensor sensor : listSensor ) {
            result.add(sensor.getName());

        }

        return result;
    }

    private void mostraSensor(int position) {
        Intent intent = new Intent(getApplicationContext(), MostraSensor.class);
        intent.putExtra("sensorType", listSensor.get(position).getType());
        startActivity(intent);
    }
}