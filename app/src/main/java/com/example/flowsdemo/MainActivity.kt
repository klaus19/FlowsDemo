package com.example.flowsdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.flowsdemo.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var flow: Flow<Int>
    val TAG:String = "Main"
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupFlows()
        setupClicks()

        goingToNextActivity()

    }

    private fun goingToNextActivity() {
        binding.btnNext.setOnClickListener {
               startActivity(Intent(this@MainActivity, SecondActivity::class.java))
        }
    }

    private fun setupClicks() {
        binding.btnLaunch.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch{
                flow.collect {
                    Log.d(TAG,it.toString())
                }
            }
        }

    }

    private fun setupFlows() {
        flow = flow {
            Log.d(TAG, "Start Flow")
            (0..10).forEach {
                // emit data after every 500 milliseconds
                delay(500)
                Log.d(TAG,"Emitting $it")
                emit(it)
            }
        }  //Usage of .map is
            .map {
            it*it
        }.flowOn(Dispatchers.Default)

    }
}