package com.adilson.models.strategies

class KilometerToMeterStrategy : CalculationStrategy {

    override fun calculate(value: Double): Double {

        return value * 1_000

    }

    override fun getResultLabel(isPlural: Boolean): String = if(isPlural) "Meters" else "Meter"

}