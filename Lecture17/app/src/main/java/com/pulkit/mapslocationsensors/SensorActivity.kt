package com.pulkit.mapslocationsensors

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sensor.*

class SensorActivity : AppCompatActivity() {
    lateinit var sm: SensorManager
    lateinit var sensorEventListener: SensorEventListener
    lateinit var proximitySensor: Sensor
    lateinit var accSensor: Sensor
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
        accSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorEventListener = object : SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                Log.d("SENSOR", "onAccuracyChanged : $accuracy")
            }

            override fun onSensorChanged(event: SensorEvent?) {
                event?.values?.let {
                    val bgColor = accelToColor(it[0], it[1], it[2])
                    bgLayout.setBackgroundColor(bgColor)
                }
            }
        }
    }

    private fun accelToColor(ax: Float, ay: Float, az: Float): Int {
        // -12 to +12
        val R = (((ax + 12) / 24) * 255).toInt()
        val G = (((ay + 12) / 24) * 255).toInt()
        val B = (((az + 12) / 24) * 255).toInt()

        return Color.rgb(R,G,B)

    }

    override fun onResume() {
        super.onResume()
        sm.registerListener(
            sensorEventListener,
            accSensor,
            100 * 100
        )
    }

    override fun onPause() {
        sm.unregisterListener(sensorEventListener)
        super.onPause()
    }
}
