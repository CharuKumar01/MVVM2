package com.example.mvvm2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var cvm: CountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        countViewModel = ViewModelProvider(this).get(CountViewModel::class.java) older way to get viewmodel
        cvm = ViewModelProvider(this)[CountViewModel::class.java]

        val tvCount = findViewById<TextView>(R.id.tvCount)
        val btnCount  = findViewById<Button>(R.id.btnCount)

        cvm.count.observe(this){ value ->
            tvCount.text = value.toString()
        }

        btnCount.setOnClickListener{
            cvm.increment()
        }
    }
}