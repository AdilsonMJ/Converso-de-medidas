package com.adilson.models

import com.adilson.models.strategies.CalculationStrategy
import java.lang.IllegalArgumentException

object Calculator {

    private var calculationStrategy : CalculationStrategy? = null

    fun setCalculationStrategy(calculationStrategy: CalculationStrategy) {
        this.calculationStrategy = calculationStrategy
    }

    fun calculate(value : Double) : Double{

        if (calculationStrategy == null){
            throw IllegalArgumentException("Calculation Strategy is not set")
        }

       return  calculationStrategy!!.calculate(value)
    }

}

