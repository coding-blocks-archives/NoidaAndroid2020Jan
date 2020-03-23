package com.pulkit.mapslocationsensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class SensorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor)
        val sm = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val sensorList = sm.getSensorList(Sensor.TYPE_ALL)

        sensorList.forEach {
            Log.d("Sensors", """
                Sensor Name : ${it.name},
                Sensor Vendor : ${it.vendor}
            """.trimIndent())
        }
    }
}
