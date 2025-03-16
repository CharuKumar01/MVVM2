package com.example.mvvm2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuoteViewModel(private val repo: QuoteRepo): ViewModel() {
    private val _quote = MutableLiveData<Quote>()
    val quote: LiveData<Quote> get() = _quote

    init {
        if (_quote.value == null){
            fetchRandomQuote()
        }
    }

    fun fetchRandomQuote(){
        _quote.value = repo.getRandomQuote()
    }
}