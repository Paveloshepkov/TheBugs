package com.example.thebugs.database


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ScoreDao {

    @Query("select * from Score ORDER BY Score DESC")
    fun getScore(): Flow<List<Score>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(score: Score)
}

