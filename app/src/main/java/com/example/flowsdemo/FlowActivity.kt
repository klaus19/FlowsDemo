package com.example.flowsdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import com.example.flowsdemo.databinding.FlowActivityBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

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