package ru.kriopeg.example.domain.models

data class Ticket(val number: Int,
                  var questionsCount: Int,
                  var rightAnswersCount: Int)