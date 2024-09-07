package com.example.internettelevision

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity()
{
  companion object
  {
    private var instance : MainActivity? = null
    fun getInstance() : MainActivity
    {
      return instance!!
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val navController = Navigation.findNavController(this, R.id.fragment)

    var bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

    bottomNavigationView.setupWithNavController(navController)
  }
}