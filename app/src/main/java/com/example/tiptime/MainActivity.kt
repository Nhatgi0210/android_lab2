package com.example.tiptime

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener{ calculateTip() }
    }
    fun calculateTip(){
        val costString = binding.cost.text.toString()
        val cost = costString.toDouble();
        val selectedId = binding.tipOption.checkedRadioButtonId;
        val tipPercentage = when(selectedId){
            R.id.p20 -> 0.2
            R.id.p18 -> 0.18
            else -> 0.15
        }
        var tip = cost * tipPercentage;
        var roundUp = binding.roundSwitch.isChecked;
        if(roundUp){
            tip = ceil(tip);
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip);
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}