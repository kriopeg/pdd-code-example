package ru.kriopeg.example.data.repoimpl

import ru.kriopeg.example.data.db.dao.CommonDao
import ru.kriopeg.example.domain.repositories.StatsRepository
import javax.inject.Inject

class DbStatsRepository @Inject constructor(private val commonDao: CommonDao) : StatsRepository {
    override fun getSolvedQuestionsCount(category: Int) = commonDao.querySolvedQuestionsCount(category = category)
}