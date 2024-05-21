package com.example.deteksiapp

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.deteksiapp.databinding.ActivitySplashscreenBinding
import de.hdodenhof.circleimageview.BuildConfig.VERSION_NAME

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@SuppressLint("CustomSplashScreen")
class splashscreenActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySplashscreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        animation()
        verification()
        version()

        val pref = SettingPreferences.getInstance(this.dataStore)
        val settingsViewModel =
            ViewModelProvider(this, ViewModelFactory(pref)).get(SettingsViewModel::class.java)

        settingsViewModel.getThemeSettings().observe(this
        ) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
    private fun verification(){
        Handler(Looper.myLooper()!!).postDelayed({
            val token = TokenPreference(this)
            when{
                token.getToken().isNullOrBlank() -> startActivity(Intent(this,MainActivity::class.java))
                else -> startActivity(Intent(this,MainActivity::class.java))
            }
            finish()
        }, 2000)
    }
    private fun version(){
        val versi = getString(R.string.version)
        binding.version.text = VERSION_NAME
    }
    private fun animation(){
        val animation : ObjectAnimator = ObjectAnimator.ofInt(binding.progressBar, "progress", 0, 100)
        animation.duration = 1900
        animation.interpolator
        animation.start()
        return
    }
}