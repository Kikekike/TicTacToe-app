package com.example.tictactoe.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import com.example.tictactoe.databinding.ActivityMainBinding
import com.example.tictactoe.utils.viewBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding (ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}
