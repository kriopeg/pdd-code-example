package ru.kriopeg.example.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.kriopeg.example.data.db.AppDatabase
import ru.kriopeg.example.data.db.Contracts
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext app: Context) : AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, Contracts.DB_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideCommonDao(appDatabase: AppDatabase) = appDatabase.commonDao()

}