package com.example.android.guesstheword.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ScoreViewModelFactory(private val finalScore: Int) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ScoreViewModel(finalScore) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}

class ScoreViewModel(finalScore: Int) : ViewModel() {
    private  val _playAgain = MutableLiveData<Boolean>()
    val playAgain : LiveData<Boolean>
        get() = _playAgain

    private val _score = MutableLiveData<Int>()
    val score : LiveData<Int>
        get() = _score

    init {
        _score.value = finalScore
    }

    fun restartTheGame(){
        _playAgain.value = true
    }

    fun onGameRestarted(){
        _playAgain.value = false
    }
}