package com.example.mytestapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.mytestapp.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit
import kotlin.system.exitProcess

class MainActivity: AppCompatActivity(){
    val TAG = "MainActivity"
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val navHomeFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        navController = navHomeFragment.findNavController()
    }

    override fun onBackPressed() {
        when (navController.currentDestination?.id) {
            R.id.homeMainFragment -> {
                finishAndRemoveTask()
                Handler(Looper.getMainLooper()).postDelayed(
                    forceStopRunnable, TimeUnit.MILLISECONDS.toMillis(800)
                )
            }
            else -> super.onBackPressed()
        }
    }

    private var forceStopRunnable = Runnable {
        exitProcess(0)
    }
}