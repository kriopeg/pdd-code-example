package ru.kriopeg.example.data.db.entities.helpers

import androidx.room.ColumnInfo
import ru.kriopeg.example.data.db.Contracts

data class QuestionSimple(@ColumnInfo(name = Contracts.Tables.Question.FIELD_ID) val id: Int,
                          @ColumnInfo(name = Contracts.Tables.Question.FIELD_TICKET) val ticket: Int,
                          @ColumnInfo(name = Contracts.Tables.Question.FIELD_NUMBER) val weight: Int,
                          @ColumnInfo(name = Contracts.Tables.Question.FIELD_CATEGORY) val category: Int)