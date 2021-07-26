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
    /*
     * Updates currentWord and currentScrambledWord with the next word.
     */
    private fun getNextWord() {
        currentWord = allWordsList.random()
        val tempWord = currentWord.toCharArray()
        tempWord.shuffle()

        //while loop to continue until the scrabmled word is not the same as the original word
        while (tempWord.toString().equals(currentWord, false)){
            tempWord.shuffle()

        //if-else block to check if the word is already used
            if (wordList.contains(currentWord)) {
                getNextWord()
            } else {
                _currentScrambledWord = String(tempWord)
                ++currentWordCount
                wordsList.add(currentWord)
                }
             }
        }
    }
