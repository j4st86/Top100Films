package com.example.top100films

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.lang.Error

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val favoriteButton: Button = findViewById(R.id.favoriteButton)
        favoriteButton.setOnClickListener {
            val intent = Intent(this, FavoriteActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            startActivity(intent)
        }
        observeInternetConnection()
    }


    private fun observeInternetConnection() {
        viewModel.hasInternetConnection.observe(this) { hasInternetConnection ->
            val noInternetImage: ImageView = findViewById(R.id.no_internet_iv)
            val noInternetText: TextView = findViewById(R.id.error_text_tv)
            if (!hasInternetConnection) {
                noInternetImage.setImageResource(R.drawable.no_internet_img)

                noInternetText.text =
                    "Произошла ошибка при загрузке данных, проверьте подключение к сети"

                val retryButton: Button = findViewById(R.id.retryButton)
                retryButton.setOnClickListener {
                    viewModel.hasInternetConnection.observe(this) { hasInternetConnection ->
                        if (!hasInternetConnection) {
                            Toast.makeText(this@MainActivity, "Все еще ошибка", Toast.LENGTH_SHORT)
                                .show()

                        } else {
                            noInternetImage.setImageDrawable(null)
                            noInternetText.text = null
                        }
                    }
                }
            } else {
                noInternetImage.setImageDrawable(null)
                noInternetText.text = null
            }
        }
    }
}