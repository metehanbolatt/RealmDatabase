package com.metehanbolat.realmdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.metehanbolat.realmdatabase.databinding.ActivityEntranceBinding

class EntranceActivity : AppCompatActivity() {

    private lateinit var binding : ActivityEntranceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntranceBinding.inflate(layoutInflater)
        val view = binding.root
        setTheme(R.style.Theme_RealmDatabase)
        setContentView(view)

        object : CountDownTimer(2500, 1000) {
            override fun onTick(p0: Long) {}
            override fun onFinish() {
                Intent(this@EntranceActivity, MainActivity::class.java).apply {
                    startActivity(this)
                    finish()
                }
            }
        }.start()
    }
}