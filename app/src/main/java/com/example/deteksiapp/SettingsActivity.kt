package com.example.deteksiapp
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.deteksiapp.databinding.ActivitySettingsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore (name = "settings")

class SettingsActivity : AppCompatActivity() {

    private lateinit var sharedPred: SharedPreferences
    private var kodeBahasa = 0
    private var isDarkModeOn = false
    private lateinit var binding: ActivitySettingsBinding
    private lateinit var dataStore: DataStore<Preferences>
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_settings)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        dataStore = applicationContext.dataStore

        val pref = SettingPreferences.getInstance(dataStore)
        val settingsViewModel = ViewModelProvider(this, ViewModelFactory(pref))
            .get(SettingsViewModel::class.java)

        sharedPred = getSharedPreferences("User", Context.MODE_PRIVATE)

        binding.apply {
            settingsViewModel.getThemeSettings()
                .observe(this@SettingsActivity) { isDarkModeActive ->
                    if (isDarkModeActive) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        switchTheme.isChecked = true
                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        switchTheme.isChecked = false
                    }
                }
            switchTheme.setOnCheckedChangeListener { _, isChecked ->
                settingsViewModel.saveThemeSetting(isChecked)
            }

            tentang.setOnClickListener {
                val intent = Intent(this@SettingsActivity, TentangActivity::class.java)
                startActivity(intent)
            }
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigationView.selectedItemId = R.id.bottom_settings
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_home -> {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                    true
                }
                R.id.bottom_photo -> {
                    startActivity(Intent(applicationContext, PhotoActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                    true
                }
                R.id.bottom_settings -> true
                else -> true
            }

        }
    }
}