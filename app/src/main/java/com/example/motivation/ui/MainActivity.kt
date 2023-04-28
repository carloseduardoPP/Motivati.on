package com.example.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.motivation.R
import com.example.motivation.data.Mock
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.databinding.ActivityMainBinding
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.infra.MotivationConstants.FILTER.INCLUSIVE

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var categoryId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        handleUserName()
        handleFilter(R.id.iv_inclusive)
        handleNextPhrase()

        binding.ivInclusive.setOnClickListener(this)
        binding.ivHappy.setOnClickListener(this)
        binding.ivSunny.setOnClickListener(this)
        binding.button.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button) {
            handleNextPhrase()
        } else if (view.id in listOf(R.id.iv_inclusive, R.id.iv_happy, R.id.iv_sunny)){
            handleFilter(view.id)
        }
    }

    private fun handleNextPhrase(){
        binding.tvMotivation.text = Mock().getPhrase(categoryId)
    }

    private fun handleFilter(id: Int) {
        binding.ivInclusive.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.ivHappy.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.ivSunny.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        when (id) {
            R.id.iv_inclusive -> {
                binding.ivInclusive.setColorFilter(ContextCompat.getColor(this, R.color.light_purple))
                categoryId = MotivationConstants.FILTER.INCLUSIVE
            }
            R.id.iv_happy -> {
                binding.ivHappy.setColorFilter(ContextCompat.getColor(this, R.color.light_purple))
                categoryId = MotivationConstants.FILTER.HAPPY
            }
            R.id.iv_sunny -> {
                binding.ivSunny.setColorFilter(ContextCompat.getColor(this, R.color.light_purple))
                categoryId = MotivationConstants.FILTER.SUNNY
            }
        }
    }

    private fun handleUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.textUserName.text = "Olá, $name!"
    }
}
