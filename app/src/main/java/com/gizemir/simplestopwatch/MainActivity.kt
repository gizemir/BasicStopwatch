package com.gizemir.simplestopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.gizemir.simplestopwatch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var number = 0
    var runnable: Runnable = Runnable{}
    var handler: Handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
    fun start(view: View){
        number = 0
        runnable = object : Runnable{
            override fun run() {
                number = number  + 1
                binding.textView.text = "Time: ${number}"
                handler.postDelayed(this, 1000) //sayı her bir arttıktan sonra 1 saniye geçikmeli olarak ekrana diğer artan sayı yazacak.
            }

        }
        handler.post(runnable) // runnable'ı handler yardımıyla başlattık
        binding.button.isEnabled = false // start'a tıklandığında sayaç saymaya devam ederken, start butonu aktif olmayacak

    }
    fun stop(view: View){
        binding.button.isEnabled = true //stop butonuna tıkladıktan sonra start butonuna tıklamayı aktif hale getirdik
        number = 0
        binding.textView.text = "Time: 0"
        handler.removeCallbacks(runnable) //handler yardımıyla runnable'ın çalısmasını durdurduk

    }
}