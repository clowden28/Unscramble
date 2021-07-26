package com.example.android.unscramble.ui.game

import android.text.Spannable
import android.text.SpannableString
import android.text.style.TtsSpan
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
//Adds a lateinit modifier to _currentScrambledWord
private lateinit var _currentScrambledWord: String



class GameViewModel : ViewModel() {

    private fun increaseScore(){
        _score.value = (_score.value)?.plus(SCORE_INCREASE)
    }
    fun isUserWordCorrect(playerWord: String): Boolean {
        if (playerWord.equals(currentWord, true)) {
            increaseScore()
            return true
        }
    return false
    }

    init {
        Log.d("GameFragment", "GameViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed!")

    }
    private var scpre = 0
    private val _currentWordCount = MutableLiveData(0)
    val currentWordCount: LiveData<Int>
        get() = _currentWordCount

    private val _currentScrambledWord = MutableLiveData<String>()
    val currentScrambledWord: LiveData<Spannable> = Transformations.map(_currentScrambledWord) {
        if (it == null) {
            SpannableString("")
        } else {
            val scrambledWord = it.toString()
            val spannable: Spannable = SpannableString(scrambledWord)
            spannable.setSpan(
                TtsSpan.VerbatimBuilder(scrambledWord).build(),
                0,
                scrambledWord.length,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )
            spannable
        }
    }

    //Creates a new class variable to hold a list of words and avoid repetitions
    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String
    private val _score = MutableLiveData(0)
    val score: LiveData<Int>
        get() = _score

    /**
     * Updates currentWord and currentScrambledWord with the next word.
     */
    private fun getNextWord() {
        currentWord = allWordsList.random()
        val tempWord = currentWord.toCharArray()
        tempWord.shuffle()

        //while loop to continue until the scrabmled word is not the same as the original word
        while (tempWord.toString().equals(currentWord, false)) {
            tempWord.shuffle()
        }
        //if-else block to check if the word is already used
            if (wordsList.contains(currentWord)) {
                getNextWord()
            } else {
                _currentScrambledWord.value = String(tempWord)
                _currentWordCount.value = (_currentWordCount.value)?.inc()
                wordsList.add(currentWord)
            }

    }

    init  {
        Log.d("GameFragment", "GameViewModel created!")
        getNextWord()

    }
    /**
    * Returns true if the current word count is less than MAX_NO_OF_WORDS.
    * Updates the next word.
    */
    fun nextWord(): Boolean {
        return if (_currentWordCount.value!! < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        }else false
    }
    /*
* Re-initializes the game data to restart the game.
*/
    fun reinitializeData() {
        _score.value = 0
        _currentWordCount.value = 0
        wordsList.clear()
        getNextWord()
    }

}
