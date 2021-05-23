package ru.kriopeg.example.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.kriopeg.example.data.Constants
import ru.kriopeg.example.domain.usecases.GetTicketsUseCase
import javax.inject.Inject

@HiltViewModel
class TicketsViewModel @Inject constructor(getTicketsUseCase: GetTicketsUseCase) : ViewModel() {

    val ticketsLiveData = getTicketsUseCase.execute(category = Constants.CAT_ABM)

}