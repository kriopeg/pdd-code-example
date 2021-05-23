package ru.kriopeg.example.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.kriopeg.example.data.db.Contracts

@Entity(tableName = Contracts.Tables.Question.TABLE_NAME)
data class QuestionEntity(@ColumnInfo(name = Contracts.Tables.Question.FIELD_ID) @PrimaryKey val id: Int,
                          @ColumnInfo(name = Contracts.Tables.Question.FIELD_TICKET) val ticket: Int,
                          @ColumnInfo(name = Contracts.Tables.Question.FIELD_NUMBER) val number: Int,
                          @ColumnInfo(name = Contracts.Tables.Question.FIELD_BLOCK) val block: Int,
                          @ColumnInfo(name = Contracts.Tables.Question.FIELD_CATEGORY) val category: Int,
                          @ColumnInfo(name = Contracts.Tables.Question.FIELD_TEXT) val text: String,
                          @ColumnInfo(name = Contracts.Tables.Question.FIELD_HINT) val hint: String,
                          @ColumnInfo(name = Contracts.Tables.Question.FIELD_IMAGE_PATH) val imagePath: String?,
                          @ColumnInfo(name = Contracts.Tables.Question.FIELD_RIGHT_OPTION) val rightOption: Int)