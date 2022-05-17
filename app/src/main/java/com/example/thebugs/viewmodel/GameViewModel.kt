package com.example.thebugs.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.*
import com.example.thebugs.database.Score
import com.example.thebugs.database.ScoreDao
import kotlinx.coroutines.launch

class GameViewModel(private val scoreDao: ScoreDao) : ViewModel() {

    val allItems: LiveData<List<Score>> = scoreDao.getScore().asLiveData()

    private val _score = MutableLiveData(0)
    val score: LiveData<Int>
        get() = _score


    private val _time = MutableLiveData(0)
    val time: LiveData<Int>
        get() = _time

    private var booleanGreen: Boolean = true
    private var booleanRed: Boolean = true
    private var booleanBlue: Boolean = true
    private var booleanYellow: Boolean = true
    private var booleanPurple: Boolean = true
    private var booleanGrey: Boolean = true

    var deafTime: Long = 1000
    var boolean: Boolean = true

    fun check(x: Float, y: Float): Boolean {

        when {
            (x > 200f && x < 300f && y > 600f && y < 700f && booleanGreen) -> {
                checkGreen()
            }

            (x > 400f && x < 500f && y > 300f && y < 400f && booleanRed) -> {
                checkRed()
            }

            (x > 100f && x < 200f && y > 100f && y < 200f && booleanBlue) -> {
                checkBlue()
            }

            (x > 750f && x < 850f && y > 200f && y < 300f && booleanYellow) -> {
                if (booleanYellow) {
                    _score.value = _score.value?.plus(20)
                } else {
                    decrementScore()
                }
                booleanYellow = false
                object : CountDownTimer(deafTime, 1000) {

                    override fun onTick(seconds: Long) {
                        seconds / 1000
                    }

                    override fun onFinish() {
                        booleanYellow = true
                    }
                }.start()
            }

            (x > 700f && x < 800f && y > 500f && y < 600f && booleanPurple) -> {
                checkPurple()
            }

            (x > 800f && x < 900f && y > 800f && y < 900f && booleanGrey) -> {
                checkGrey()
            }

            else -> {
                decrementScore()
            }
        }
        return true
    }

    private fun decrementScore() {
        _score.value = _score.value?.minus(5)
    }

    private fun incrementScore() {
        _score.value = _score.value?.plus(20)
    }

    private fun checkGreen() {
        if (booleanGreen) {
            incrementScore()
        } else {
            decrementScore()
        }
        booleanGreen = false
        object : CountDownTimer(deafTime, 1000) {

            override fun onTick(seconds: Long) {
                seconds / 1000
            }

            override fun onFinish() {
                booleanGreen = true
            }
        }.start()
    }

    private fun checkRed() {
        if (booleanRed) {
            incrementScore()
        } else {
            decrementScore()
        }
        booleanRed = false
        object : CountDownTimer(deafTime, 1000) {

            override fun onTick(seconds: Long) {
                seconds / 1000
            }

            override fun onFinish() {
                booleanRed = true
            }
        }.start()
    }

    private fun checkBlue() {
        if (booleanBlue) {
            incrementScore()
        } else {
            decrementScore()
        }
        booleanBlue = false
        object : CountDownTimer(deafTime, 1000) {

            override fun onTick(seconds: Long) {
                seconds / 1000
            }

            override fun onFinish() {
                booleanBlue = true
            }
        }.start()
    }

    private fun checkPurple() {
        if (booleanPurple) {
            incrementScore()
        } else {
            decrementScore()
        }
        booleanPurple = false
        object : CountDownTimer(deafTime, 1000) {

            override fun onTick(seconds: Long) {
                seconds / 1000
            }

            override fun onFinish() {
                booleanPurple = true
            }
        }.start()
    }

    private fun checkGrey() {
        if (booleanGrey) {
            incrementScore()
        } else {
            decrementScore()
        }
        booleanGrey = false
        object : CountDownTimer(deafTime, 1000) {

            override fun onTick(seconds: Long) {
                seconds / 1000
            }

            override fun onFinish() {
                booleanGrey = true
            }
        }.start()
    }

    fun startGame() {
        _score.value = 0
    }

    private fun getNewScoreEntry(score: String): Score {
        return Score(
            score = score
        )
    }

    fun addNewScore(score: String) {
        val newScore = getNewScoreEntry(score)
        insertScore(newScore)
    }

    private fun insertScore(score: Score) {
        viewModelScope.launch {
            scoreDao.insert(score)
        }
    }
}