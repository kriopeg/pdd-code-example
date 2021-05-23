package ru.kriopeg.example.data.repoimpl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import ru.kriopeg.example.data.db.dao.CommonDao
import ru.kriopeg.example.data.Constants
import ru.kriopeg.example.domain.models.Ticket
import ru.kriopeg.example.domain.repositories.TicketsRepository
import javax.inject.Inject

class DbTicketsRepository @Inject constructor(private val commonDao: CommonDao) : TicketsRepository {
    override fun getTickets(category: Int): LiveData<List<Ticket>> {
        val ticketsEntitiesLiveData = commonDao.queryTicketsStats(category = category)
        val mediatorLiveData = MediatorLiveData<List<Ticket>>()

        mediatorLiveData.addSource(ticketsEntitiesLiveData) { ticketsEntities ->
            val tickets = mutableListOf<Ticket>()

            ticketsEntities.forEach {
                var ticket = tickets.find { ticket -> ticket.number == it.question.ticket }
                if (ticket == null) {
                    ticket = Ticket(number = it.question.ticket,
                        questionsCount = 0,
                        rightAnswersCount = 0)
                    tickets.add(ticket)
                }

                ticket.questionsCount++

                val stat = it.stats.find { questionStatsEntity -> questionStatsEntity.scenario == Constants.SCENARIO_TICKETS }
                stat?.let { questionStatsEntity ->
                    val increment = if (questionStatsEntity.rightInARow > 0) 1 else 0
                    ticket.rightAnswersCount += increment
                }
            }
            mediatorLiveData.value = tickets.sortedBy { it.number }
        }

        return mediatorLiveData
    }
}