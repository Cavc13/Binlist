package com.snusnu.binlist.feature_binlist.data.data_source

import androidx.room.Database
import com.snusnu.binlist.feature_binlist.domain.model.Bin

@Database(
    entities = [Bin::class], version = 1
)
abstract class BinDatabase {
    abstract val binDao: BinDao

    companion object {
        const val DATABASE_NAME = "bin_db"
    }
}