package com.example.flowsdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import com.example.flowsdemo.databinding.FlowActivityBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class FlowActivity:AppCompatActivity() {

    val weatherForecast = listOf(3,4,5)

    lateinit var flow: Flow<Int>
    lateinit var binding: FlowActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FlowActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

         lionAcross()
        lionCollect()

        binding.btnClick.setOnClickListener {
            combineFlows()
        }

    }

    private fun combineFlows() = runBlocking{
        val ask = flowOf(1,2,3).delayEach(1000)
        val letters = flowOf("A","B","C").delayEach(1000)

        ask.zip(letters){ askNo,letterNo ->
            "$askNo$letterNo"
        }.collect {
            binding.txtCombine.text = it
        }
    }

    private fun lionCollect() {
        CoroutineScope(Dispatchers.Main).launch {
              flow.collect {
                  binding.txtNumber.text = it.toString()
              }
        }
    }

    private fun lionAcross(){
        flow = flow {
            for (i in weatherForecast){
                delay(1000)
                emit(i)
            }
        }.flowOn(Dispatchers.Default)
    }
}