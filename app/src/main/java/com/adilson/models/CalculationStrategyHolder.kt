package com.adilson.models

import com.adilson.models.strategies.CalculationStrategy

data class CalculationStrategyHolder (
    val name: String,
    val calculationStrategy: CalculationStrategy
)