package com.sachet.couroutinedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private var count = 0
    private lateinit var textDownloadResult: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dwnldBtn = findViewById<Button>(R.id.dwnload_btn)
        val incrBtn = findViewById<Button>(R.id.increment_btn)
        val textView = findViewById<TextView>(R.id.count_value)
        textDownloadResult = findViewById<TextView>(R.id.textDownloadResult)
        incrBtn.setOnClickListener {
            textView.text = count ++.toString()
        }
        dwnldBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                downloadData()
            }
        }
    }

    private suspend fun downloadData(){
        withContext(Dispatchers.Main){
            for (i in 1..200000){
                textDownloadResult.text = "Hello from ${Thread.currentThread().name} $i"
            }
        }
    }

    /**
     * whenever a coroutine is suspended, the current stack frame of the function is copied and saved
     * in the memory
     * when the function resumes after completing its task, the stack frame is copied back from where it was saved
     * and starts running again
     * Suspending functions provided by kotlin coroutine api
     * withContext              delay
     * withTimeOut              await
     * withTimeOutOrNull        supervisorScope
     * join                     coroutineScope
     * -> Room and retrofit also provides suspending functions to allow us to work with coroutine
     * -> A suspending function, can be invoked from a coroutine block or from another suspending
     *    function only
     * -> A coroutine can invoke both suspending and non suspending function
     */

}