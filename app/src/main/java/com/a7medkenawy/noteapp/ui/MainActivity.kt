package com.a7medkenawy.noteapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.a7medkenawy.noteapp.R
import com.a7medkenawy.noteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpFab()
    }

    private fun setUpFab() {
        binding.addNoteFab.setOnClickListener {
            val intent=Intent(this, AddOrEditActivity::class.java)
            startActivity(intent)
        }
    }

}