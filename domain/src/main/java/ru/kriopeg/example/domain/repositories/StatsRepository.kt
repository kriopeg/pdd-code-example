package ru.kriopeg.example.domain.repositories

import androidx.lifecycle.LiveData

interface StatsRepository {

    fun getSolvedQuestionsCount(category: Int) : LiveData<Int>

}