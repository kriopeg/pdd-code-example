package ru.kriopeg.example.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.kriopeg.example.data.db.Contracts

@Entity(tableName = Contracts.Tables.QuestionWeight.TABLE_NAME)
data class QuestionWeightStatsEntity(@ColumnInfo(name = Contracts.Tables.QuestionWeight.FIELD_ID) @PrimaryKey val id: Int,
                                     @ColumnInfo(name = Contracts.Tables.QuestionWeight.FIELD_RIGHT_IN_A_ROW) val rightInARow: Int,
                                     @ColumnInfo(name = Contracts.Tables.QuestionWeight.FIELD_WEIGHT) val weight: Float)