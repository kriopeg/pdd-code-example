package ru.kriopeg.example.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import ru.kriopeg.example.data.db.Contracts
import ru.kriopeg.example.data.db.entities.helpers.TicketStatsEntity

@Dao
interface CommonDao {

    @Query("SELECT COUNT(${Contracts.Tables.QuestionWeight.FIELD_RIGHT_IN_A_ROW}) " +
            "FROM ${Contracts.Tables.Question.TABLE_NAME} " +
            "LEFT OUTER JOIN ${Contracts.Tables.QuestionWeight.TABLE_NAME} ON ${Contracts.Tables.Question.TABLE_NAME}.${Contracts.Tables.Question.FIELD_ID} = ${Contracts.Tables.QuestionWeight.TABLE_NAME}.${Contracts.Tables.QuestionWeight.FIELD_ID} " +
            "WHERE ${Contracts.Tables.QuestionWeight.FIELD_RIGHT_IN_A_ROW} > 0 AND ${Contracts.Tables.Question.FIELD_CATEGORY} = :category")
    fun querySolvedQuestionsCount(category: Int) : LiveData<Int>

    @Query("SELECT * FROM ${Contracts.Tables.Question.TABLE_NAME} WHERE ${Contracts.Tables.Question.FIELD_CATEGORY} = :category")
    fun queryTicketsStats(category: Int) : LiveData<List<TicketStatsEntity>>

}