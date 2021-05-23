package ru.kriopeg.example.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DbStatsRepositoryQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DbTicketsRepositoryQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MockStatsRepositoryQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MockTicketsRepositoryQualifier