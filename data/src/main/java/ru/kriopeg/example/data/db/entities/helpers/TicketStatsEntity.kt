package ru.kriopeg.example.data.db.entities.helpers

import androidx.room.Embedded
import androidx.room.Relation
import ru.kriopeg.example.data.db.Contracts
import ru.kriopeg.example.data.db.entities.QuestionStatsEntity

data class TicketStatsEntity(@Embedded val question: QuestionSimple,
                             @Relation(
                                parentColumn = Contracts.Tables.Question.FIELD_ID,
                                entityColumn = Contracts.Tables.QuestionStats.FIELD_QUESTION_ID
                             ) val stats: List<QuestionStatsEntity>)