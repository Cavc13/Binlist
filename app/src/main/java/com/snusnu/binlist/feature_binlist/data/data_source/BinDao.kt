package com.snusnu.binlist.feature_binlist.data.data_source

import kotlinx.coroutines.flow.Flow
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.snusnu.binlist.feature_binlist.domain.model.Bin

@Dao
interface BinDao {

    @Query("SELECT * FROM bin")
    fun getBins(): Flow<List<Bin>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBin(bin: Bin)
}