package com.adilson.models.strategies

class CentimetersToKilometerStrategy : CalculationStrategy {
    override fun calculate(value: Double): Double  = value / 100_000

    override fun getResultLabel(isPlural: Boolean): String = if (isPlural) "kilometers" else "kilometer"

}