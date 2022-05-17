package com.example.thebugs

import android.app.Application
import com.example.thebugs.database.ScoreDatabase

class App : Application() {
    val database: ScoreDatabase by lazy { ScoreDatabase.getDatabase(this) }
}