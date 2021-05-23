package ru.kriopeg.example.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.kriopeg.example.data.db.Contracts

@Entity(tableName = Contracts.Tables.QuestionStats.TABLE_NAME)
data class QuestionStatsEntity(@ColumnInfo(name = Contracts.Tables.QuestionStats.FIELD_ID) @PrimaryKey(autoGenerate = true) val id: Int?,
                               @ColumnInfo(name = Contracts.Tables.QuestionStats.FIELD_SCENARIO) val scenario: Int,
                               @ColumnInfo(name = Contracts.Tables.QuestionStats.FIELD_QUESTION_ID) val questionId: Int,
                               @ColumnInfo(name = Contracts.Tables.QuestionStats.FIELD_ANSWERS_COUNT) val answersCount: Int,
                               @ColumnInfo(name = Contracts.Tables.QuestionStats.FIELD_RIGHT_COUNT) val rightCount: Int,
                               @ColumnInfo(name = Contracts.Tables.QuestionStats.FIELD_RIGHT_IN_A_ROW) val rightInARow: Int)