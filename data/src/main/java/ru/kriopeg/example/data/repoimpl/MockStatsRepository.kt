package ru.kriopeg.example.data.repoimpl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import ru.kriopeg.example.domain.repositories.StatsRepository
import javax.inject.Inject

class MockStatsRepository @Inject constructor() : StatsRepository {
    override fun getSolvedQuestionsCount(category: Int): LiveData<Int> {
        val liveData = MutableLiveData<Int>()

        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                liveData.value = 586
            }
        }

        return liveData
    }
}