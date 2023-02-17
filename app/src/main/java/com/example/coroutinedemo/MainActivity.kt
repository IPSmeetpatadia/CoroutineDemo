package com.example.coroutinedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    lateinit var counter: TextView
    lateinit var btnStart: Button
    lateinit var btnReset: Button
    lateinit var btnStop: Button

    var number = 0
    lateinit var runnable: Runnable
    var handler = Handler(Looper.myLooper()!!)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        counter = findViewById(R.id.txt_counter)
        btnStart = findViewById(R.id.btn_startCounter)
        btnReset = findViewById(R.id.btn_resetCounter)
        btnStop = findViewById(R.id.btn_stopCounter)

        btnStart.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch() {
                runnable.run()
            }
            runnable = Runnable {
                number += 1
                handler.postDelayed(runnable, 1000)
                counter.text = number.toString()
            }
        }

        btnStop.setOnClickListener {
            handler.removeCallbacks(runnable)
            counter.text = number.toString()
        }

        btnReset.setOnClickListener {
            handler.removeCallbacks(runnable)
            number = 0
            counter.text = number.toString()
        }


    }
}