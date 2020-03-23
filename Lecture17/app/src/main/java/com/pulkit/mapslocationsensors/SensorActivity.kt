package com.pulkit.mapslocationsensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class SensorActivity : AppCompatActivity() {
    lateinit var sm: SensorManager
    lateinit var sensorEventListener: SensorEventListener
    lateinit var proximitySensor:Sensor
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor)
        sm = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val sensorList = sm.getSensorList(Sensor.TYPE_ALL)

        sensorList.forEach {
            Log.d(
                "Sensors", """
                Sensor Name : ${it.name},
                Sensor Vendor : ${it.vendor}
            """.trimIndent()
            )
        }
        proximitySensor = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        sensorEventListener = object : SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                Log.d("SENSOR", "onAccuracyChanged : $accuracy")
            }

            override fun onSensorChanged(event: SensorEvent?) {
                Log.d("SENSOR", "onSensorChanged : ${event?.values?.get(0)}")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        sm.registerListener(
            sensorEventListener,
            proximitySensor,
            1000 * 1000
        )
    }

    override fun onPause() {
        sm.unregisterListener(sensorEventListener)
        super.onPause()
    }
}
