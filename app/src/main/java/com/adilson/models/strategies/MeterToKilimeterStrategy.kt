package com.adilson.models.strategies

class MeterToKilimeterStrategy : CalculationStrategy {
    override fun calculate(value: Double): Double {
        return value / 1_000
    }

    override fun getResultLabel(isPlural: Boolean): String = if (isPlural) "quilometros" else "quilometro"
}