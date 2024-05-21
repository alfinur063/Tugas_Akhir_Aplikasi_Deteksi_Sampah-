import org.jetbrains.kotlin.gradle.internal.kapt.incremental.UnknownSnapshot
import org.jetbrains.kotlin.storage.CacheResetOnProcessCanceled.enabled


plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id ("androidx.navigation.safeargs")

}

android {
    namespace = "com.example.deteksiapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.deteksiapp"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
        mlModelBinding = true
    }

    androidResources {
        noCompress += "tflite"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }



}

dependencies {
    implementation ("de.hdodenhof:circleimageview:2.2.0")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation ("androidx.datastore:datastore-preferences:1.1.0")

    implementation("androidx.cardview:cardview:1.0.0")


    // CameraX core library
    implementation ("androidx.camera:camera-core:1.1.0-beta03")

    // CameraX Camera2 extensions
    implementation ("androidx.camera:camera-camera2:1.1.0-beta03")

    // CameraX Lifecycle library
    implementation ("androidx.camera:camera-lifecycle:1.1.0-beta03")

    // CameraX View class
    implementation ("androidx.camera:camera-view:1.1.0-beta03")

    //WindowManager
    implementation ("androidx.window:window:1.0.0-alpha09")

    implementation ("org.tensorflow:tensorflow-lite-task-vision:0.4.0")
    // Import the GPU delegate plugin Library for GPU inference
    implementation ("org.tensorflow:tensorflow-lite-gpu-delegate-plugin:0.4.0")
    implementation ("org.tensorflow:tensorflow-lite-gpu:2.9.0")

    implementation ("androidx.localbroadcastmanager:localbroadcastmanager:1.0.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")


    implementation ("com.google.guava:guava:31.0.1-android")

    // Navigation library
    implementation ("androidx.navigation:navigation-fragment-ktx:2.3.5")
    implementation ("androidx.navigation:navigation-ui-ktx:2.3.5")

    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0")

    implementation("androidx.core:core-ktx:1.13.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}