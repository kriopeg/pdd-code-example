package ru.kriopeg.example.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.kriopeg.example.domain.repositories.StatsRepository
import ru.kriopeg.example.domain.repositories.TicketsRepository
import ru.kriopeg.example.domain.usecases.GetSolvedQuestionsCountUseCase
import ru.kriopeg.example.domain.usecases.GetTicketsUseCase

@Module
@InstallIn(ViewModelComponent::class)
object UseCasesModule {

    @Provides
    fun provideSolvedQuestionsCountUseCase(@MockStatsRepositoryQualifier statsRepository: StatsRepository) =
        GetSolvedQuestionsCountUseCase(statsRepository = statsRepository)

    @Provides
    fun provideTicketsUseCase(@MockTicketsRepositoryQualifier ticketsRepository: TicketsRepository) =
        GetTicketsUseCase(ticketsRepository = ticketsRepository)

}