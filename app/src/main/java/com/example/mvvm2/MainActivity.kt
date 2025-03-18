package com.example.mvvm2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var bind: ActivityMainBinding
    private lateinit var qvm: QuoteViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        bind = DataBindingUtil.setContentView(this, R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        countViewModel = ViewModelProvider(this).get(CountViewModel::class.java) older way to get viewmodel
        val repo = QuoteRepo(applicationContext)
        qvm = ViewModelProvider(this, QuoteViewModelFactory(repo))[QuoteViewModel::class.java]

        val tvQuote = bind.tvQuote
        val tvAuthor = bind.tvAuthor
        val btnFetch = bind.btnFetch

        qvm.quote.observe(this) { quote ->
            tvQuote.text = quote.text
            tvAuthor.text = "~${quote.author}"
        }


        btnFetch.setOnClickListener {
            qvm.fetchRandomQuote()
        }
    }
}