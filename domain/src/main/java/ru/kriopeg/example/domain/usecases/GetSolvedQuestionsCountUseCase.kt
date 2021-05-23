package ru.kriopeg.example.domain.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import ru.kriopeg.example.domain.repositories.StatsRepository
import javax.inject.Inject

class GetSolvedQuestionsCountUseCase @Inject constructor(private val statsRepository: StatsRepository) {

    sealed class Result {
        object Loading : Result()
        data class Success(val answersCount: Int) : Result()
    }

    fun execute(category: Int) : LiveData<Result> {
        val mediatorLiveData = MediatorLiveData<Result>()
        mediatorLiveData.value = Result.Loading
        mediatorLiveData.addSource(statsRepository.getSolvedQuestionsCount(category)) {
            mediatorLiveData.value = Result.Success(it)
        }
        return mediatorLiveData
    }

}