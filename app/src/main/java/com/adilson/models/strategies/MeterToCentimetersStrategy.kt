package com.adilson.models.strategies

class MeterToCentimetersStrategy : CalculationStrategy {
    override fun calculate(value: Double): Double  = value * 100

    override fun getResultLabel(isPlural: Boolean): String = if (isPlural) "Centimeters" else "Centimeter"


}