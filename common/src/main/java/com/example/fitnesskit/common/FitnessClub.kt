package com.example.fitnesskit.common

data class FitnessClub(
    val lessons: List<Lesson>,
    val option: Option,
    val tabs: List<Tab>,
    val trainers: List<Trainer>
)