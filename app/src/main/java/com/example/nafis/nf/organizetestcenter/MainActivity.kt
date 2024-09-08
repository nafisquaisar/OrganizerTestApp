package com.example.nafis.nf.organizetestcenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nafis.nf.organizetestcenter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Replace fragment with HomeFragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.wrapper, HomeFragment())
            .commit()
    }
}
