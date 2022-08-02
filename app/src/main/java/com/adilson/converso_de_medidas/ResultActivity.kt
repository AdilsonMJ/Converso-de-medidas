package com.adilson.converso_de_medidas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_activity)


        val result = intent.getDoubleExtra("RESULT", 0.0)
        val label = intent.getStringExtra("LABEL")

        val tvValue : TextView = findViewById(R.id.tvValue)
        tvValue.text = result.toString()

        val tvLabel : TextView = findViewById(R.id.tvValueLabel)
        tvLabel.text  = label

        val btnCloser : Button = findViewById(R.id.btnClose)
        btnCloser.setOnClickListener{
            finish()
        }
    }
}