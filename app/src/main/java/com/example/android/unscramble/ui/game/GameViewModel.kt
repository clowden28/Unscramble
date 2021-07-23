package com.example.android.unscramble.ui.game

import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private var scpre = 0
    private var currentWordCount = 0

    //Creates a backing property for currentScrabmledWord
    private var _currentScrambledWord = "test"
    val currentScrambledWord: String
        get() = _currentScrambledWord
}