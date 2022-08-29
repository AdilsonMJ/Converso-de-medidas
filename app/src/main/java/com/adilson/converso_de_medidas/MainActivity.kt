package com.adilson.converso_de_medidas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.adilson.converso_de_medidas.databinding.ActivityMainBinding
import com.adilson.models.CalculationStrategyHolder
import com.adilson.models.Calculator
import com.adilson.models.strategies.*
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val supportCalculationStrategies = arrayOf(
        CalculationStrategyHolder("Km to CM", KilometerToCentimetersStrategy()),
        CalculationStrategyHolder("Km to M", KilometerToMeterStrategy()),
        CalculationStrategyHolder("M to CM", MeterToCentimetersStrategy()),
        CalculationStrategyHolder("M to Km", MeterToKilimeterStrategy()),
        CalculationStrategyHolder("CM to Km", CentimetersToKilometerStrategy()),
        CalculationStrategyHolder("CM to M", CentimetersToMetersStrategy())
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var value = 0.0
        var position = 0

        savedInstanceState?.let{
            value = it.getDouble("VALUE")
            position = it.getInt("POSITION")
        }

        setUI(value = value, position = position)
        setActions()


    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)


        try{
            outState.putDouble("VALUE", binding.edtValue.text.toString().toDouble())
        }catch (e : NumberFormatException){

        }
        outState.putInt("POSITION", binding.spConversion.selectedItemPosition)

    }



    private fun setActions() {
        val btnConverter: Button = findViewById(R.id.btnConverte)

        btnConverter.setOnClickListener{

            try{

                val value = binding.edtValue.text.toString().toDouble()

                val selectedItemPosition = binding.spConversion.selectedItemPosition



                val calculationStrategy = supportCalculationStrategies[
                            selectedItemPosition]
                        .calculationStrategy

                Calculator.setCalculationStrategy(
                    calculationStrategy
                )

                val result = Calculator.calculate(value)
                val label = calculationStrategy.getResultLabel(result != 1.toDouble())


               showResult(result, label)

            }catch (e: NumberFormatException){
                binding.edtValue.error = "Valor invalido!"
                binding.edtValue.requestFocus()
            }



        }

        val btn: Button = findViewById(R.id.btnClean)
        btn.setOnClickListener {
            clearFillds()
        }


    }

    private fun clearFillds() {

            binding.edtValue.setText("0")
            binding.edtValue.error = ""
            binding.spConversion.setSelection(0)

    }

    private fun showResult(result: Double, label: String) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("RESULT", result)
        intent.putExtra("LABEL", label)

        startActivity(intent)
    }

    private fun setUI(value: Double, position : Int) {

        binding.edtValue.setText(value.toString())

        val spAdapter =  ArrayAdapter(this, R.layout.res_spinner_item, getSpinnerData())
        spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spConversion.adapter = spAdapter
        binding.spConversion.setSelection(position)
    }

    private fun getSpinnerData(): MutableList<String> {
        val spinnerData = mutableListOf<String>()

        supportCalculationStrategies.forEach {
            spinnerData.add(it.name)
        }

        return spinnerData
    }
}