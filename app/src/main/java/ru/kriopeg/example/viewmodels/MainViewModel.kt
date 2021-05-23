package ru.kriopeg.example.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.kriopeg.example.data.Constants
import ru.kriopeg.example.domain.usecases.GetSolvedQuestionsCountUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(getSolvedQuestionsCountUseCase: GetSolvedQuestionsCountUseCase) : ViewModel() {

    val solvedCountLiveData = getSolvedQuestionsCountUseCase.execute(category = Constants.CAT_ABM)

}