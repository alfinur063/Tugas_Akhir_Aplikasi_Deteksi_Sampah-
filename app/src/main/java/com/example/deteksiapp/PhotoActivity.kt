package com.example.deteksiapp

//import android.annotation.SuppressLint
//import android.content.Context
import android.content.Intent
//import android.content.pm.PackageManager
//import android.graphics.SurfaceTexture
//import android.hardware.camera2.CameraCaptureSession
//import android.hardware.camera2.CameraDevice
//import android.hardware.camera2.CameraManager
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.os.Handler
//import android.os.HandlerThread
//import android.view.Surface
//import android.view.TextureView
//import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.*
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.FileUtils
import android.os.Handler
import android.os.HandlerThread
import android.view.Surface
import android.view.TextureView
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.deteksiapp.databinding.ActivityPhotoBinding
import org.tensorflow.lite.support.common.FileUtil
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp


class PhotoActivity : AppCompatActivity() {

    private lateinit var activityPhotoBinding: ActivityPhotoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityPhotoBinding = ActivityPhotoBinding.inflate(layoutInflater)
        setContentView(activityPhotoBinding.root)



        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigationView.selectedItemId = R.id.bottom_photo
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_home -> {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                    true
                }
                R.id.bottom_photo -> true

                R.id.bottom_settings -> {
                    startActivity(Intent(applicationContext, SettingsActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                    true
                }
                else -> false
            }
        }

    }





override fun onBackPressed() {
    if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) {
        // Workaround for Android Q memory leak issue in IRequestFinishCallback$Stub.
        // (https://issuetracker.google.com/issues/139738913)
        finishAfterTransition()
    } else {
        super.onBackPressed()
    }
}
}