package ru.kriopeg.example.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.kriopeg.example.data.repoimpl.DbStatsRepository
import ru.kriopeg.example.data.repoimpl.DbTicketsRepository
import ru.kriopeg.example.data.repoimpl.MockStatsRepository
import ru.kriopeg.example.data.repoimpl.MockTicketsRepository
import ru.kriopeg.example.domain.repositories.StatsRepository
import ru.kriopeg.example.domain.repositories.TicketsRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

    @DbStatsRepositoryQualifier
    @Binds
    abstract fun bindDbStatsRepository(statsRepository: DbStatsRepository) : StatsRepository

    @DbTicketsRepositoryQualifier
    @Binds
    abstract fun bindDbTicketsRepository(ticketsRepository: DbTicketsRepository) : TicketsRepository

    @MockStatsRepositoryQualifier
    @Binds
    abstract fun bindMockStatsRepository(statsRepository: MockStatsRepository) : StatsRepository

    @MockTicketsRepositoryQualifier
    @Binds
    abstract fun bindMockTicketsRepository(ticketsRepository: MockTicketsRepository) : TicketsRepository
}