package com.example.shemajamebeli4redo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shemajamebeli4redo.databinding.ActivityMainBinding
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.bottomAppBar.showBadge(R.id.navigation_fav, 3)

        binding.backButton.setOnClickListener {
            exitProcess(-1)
        }
    }

}