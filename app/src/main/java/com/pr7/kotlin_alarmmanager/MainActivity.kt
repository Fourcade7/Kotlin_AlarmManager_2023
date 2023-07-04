package com.pr7.kotlin_alarmmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import com.pr7.kotlin_alarmmanager.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private val calendar = Calendar.getInstance()
    private lateinit var alarmIntent: PendingIntent
    lateinit var binding: ActivityMainBinding
    lateinit var alarmManager: AlarmManager
    lateinit var pendingIntent: PendingIntent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        Log.d("PR77777", "Alarm message: axaxaxa")

        alarmIntent = Intent(this@MainActivity, AlarmReceiver::class.java).let { intent ->
            intent.putExtra("key", "Hello!")
            PendingIntent.getBroadcast(this@MainActivity, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        }

            binding.apply {
            timepicker.setIs24HourView(true)

                buttonschedulealarm.setOnClickListener {
                    calendar.set(Calendar.HOUR_OF_DAY, timepicker.hour)
                    calendar.set(Calendar.MINUTE, timepicker.minute)
                    alarmManager?.setExact(
                        AlarmManager.RTC_WAKEUP,
                        calendar.timeInMillis,
                        alarmIntent
                    )
                }
                buttonschedulerepeatingalarm.setOnClickListener {
                    alarmManager?.setInexactRepeating(
                        AlarmManager.ELAPSED_REALTIME_WAKEUP,
                        SystemClock.elapsedRealtime(),
                        1*60 * 1000,
                        alarmIntent
                    )
                }
        }
        
    }
}