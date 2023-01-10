package com.snusnu.binlist.di

import android.app.Application
import androidx.room.Room
import com.snusnu.binlist.feature_binlist.data.data_source.BinDatabase
import com.snusnu.binlist.feature_binlist.data.network.api.BinApi
import com.snusnu.binlist.feature_binlist.data.repository.BinlistRepositoryImpl
import com.snusnu.binlist.feature_binlist.domain.repository.BinRepository
import com.snusnu.binlist.feature_binlist.domain.use_case.AddBinUseCase
import com.snusnu.binlist.feature_binlist.domain.use_case.BinUseCases
import com.snusnu.binlist.feature_binlist.domain.use_case.GetBinListUseCase
import com.snusnu.binlist.feature_binlist.domain.use_case.GetBinUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBinDatabase(app: Application): BinDatabase {
        return Room.databaseBuilder(
            app,
            BinDatabase::class.java,
            BinDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideBinRetrofit(): BinApi =
        Retrofit.Builder()
            .baseUrl(BIN_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BinApi::class.java)

    @Provides
    @Singleton
    fun provideBinRepository(db: BinDatabase, api: BinApi) : BinRepository {
        return BinlistRepositoryImpl(db.binDao, api)
    }

    @Provides
    @Singleton
    fun provideBinUseCases(repository: BinRepository) : BinUseCases {
        return BinUseCases(
            GetBinListUseCase(repository),
            AddBinUseCase(repository),
            GetBinUseCase(repository)
        )
    }

    const val BIN_URL = "https://lookup.binlist.net"
}