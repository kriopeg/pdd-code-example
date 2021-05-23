package ru.kriopeg.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.kriopeg.example.data.db.dao.CommonDao
import ru.kriopeg.example.data.db.entities.QuestionEntity
import ru.kriopeg.example.data.db.entities.QuestionWeightStatsEntity
import ru.kriopeg.example.data.db.entities.QuestionStatsEntity

@Database(entities = [QuestionEntity::class,
                     QuestionStatsEntity::class,
                     QuestionWeightStatsEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun commonDao() : CommonDao

}