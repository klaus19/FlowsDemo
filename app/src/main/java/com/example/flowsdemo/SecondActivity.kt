package com.example.flowsdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.flowsdemo.databinding.SecondBinding

class SecondActivity:AppCompatActivity() {

    private lateinit var binding: SecondBinding
    lateinit var viewmodel: SecondActivityViewmodel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel =ViewModelProvider(this).get(SecondActivityViewmodel::class.java)



        setViewmodel()





    }

    private fun setViewmodel() {
        viewmodel.liveData.value
        binding.txtCount.text = viewmodel.number.toString()
    }


}