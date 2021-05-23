package ru.kriopeg.example.data.repoimpl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import ru.kriopeg.example.domain.models.Ticket
import ru.kriopeg.example.domain.repositories.TicketsRepository
import javax.inject.Inject
import kotlin.random.Random

class MockTicketsRepository @Inject constructor() : TicketsRepository {
    override fun getTickets(category: Int): LiveData<List<Ticket>> {
        val liveData = MutableLiveData<List<Ticket>>()

        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            val ticketsList = mutableListOf<Ticket>()
            for (index in 1..10) {
                ticketsList.add(Ticket(number = index,
                    questionsCount = 20,
                    rightAnswersCount = Random.nextInt(from = 0, until = 21)))
            }
            withContext(Dispatchers.Main) {
                liveData.value = ticketsList
            }
        }

        return liveData
    }
}