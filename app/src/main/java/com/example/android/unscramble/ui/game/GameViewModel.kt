package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    init {
        Log.d("GameFragment", "GameViewModel created!")
    }

    /**
     *
     */

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed!")
    }
    private var scpre = 0
    private var currentWordCount = 0

    //Creates a backing property for currentScrabmledWord
    private var _currentScrambledWord = "test"
    val currentScrambledWord: String
        get() = _currentScrambledWord
    //Creates a new class variable to hold a list of words and avoid repetitions
    private var wordList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String
    //
    private fun getNextWord() {
        currentWord = allWordsList.random()
        val tempWord = currentWord.toCharArray()
        tempWord.shuffle()
    }

}
