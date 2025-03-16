package com.example.mvvm2

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

class QuoteRepo(private val context: Context) {
    private val quotes: List<Quote> by lazy { loadQuotes(context) }

    private fun loadQuotes(context: Context): List<Quote>{
        val inputStream = context.resources.openRawResource(R.raw.quotes)
        val reader = InputStreamReader(inputStream)

        val type = object: TypeToken<List<Quote>>() {}.type
        return Gson().fromJson(reader, type)
    }

    fun getRandomQuote(): Quote{
        return quotes.random()
    }
}