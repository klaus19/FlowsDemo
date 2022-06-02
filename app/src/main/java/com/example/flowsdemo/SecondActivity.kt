package com.example.flowsdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.flowsdemo.databinding.SecondBinding

class SecondActivity:AppCompatActivity() {

    private lateinit var binding: SecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}