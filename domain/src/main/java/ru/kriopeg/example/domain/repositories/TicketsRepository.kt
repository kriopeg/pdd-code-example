package ru.kriopeg.example.domain.repositories

import androidx.lifecycle.LiveData
import ru.kriopeg.example.domain.models.Ticket

interface TicketsRepository {

    fun getTickets(category: Int) : LiveData<List<Ticket>>

}