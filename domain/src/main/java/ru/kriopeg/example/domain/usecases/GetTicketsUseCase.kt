package ru.kriopeg.example.domain.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import ru.kriopeg.example.domain.models.Ticket
import ru.kriopeg.example.domain.repositories.TicketsRepository
import javax.inject.Inject

class GetTicketsUseCase @Inject constructor(private val ticketsRepository: TicketsRepository) {

    sealed class Result {
        object Loading : Result()
        object Empty : Result()
        data class Success(val tickets: List<Ticket>) : Result()
    }

    fun execute(category: Int) : LiveData<Result> {
        val liveData = MediatorLiveData<Result>()
        liveData.value = Result.Loading
        liveData.addSource(ticketsRepository.getTickets(category)) {
            if (it.isNotEmpty()) {
                liveData.value = Result.Success(it)
            } else {
                liveData.value = Result.Empty
            }
        }
        return liveData
    }

}