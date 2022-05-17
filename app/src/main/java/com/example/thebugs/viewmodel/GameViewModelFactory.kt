package com.example.thebugs.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.thebugs.database.ScoreDao

class GameViewModelFactory(private val scoreDao: ScoreDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameViewModel(scoreDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
