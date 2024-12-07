plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.appquanlichitieu"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.appquanlichitieu"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    implementation ("androidx.room:room-runtime:2.6.1")
    ksp("androidx.room:room-compiler:2.5.0")
    implementation ("androidx.room:room-ktx:2.6.1")


    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")

    implementation ("androidx.navigation:navigation-fragment-ktx:2.9.0-alpha03")
    implementation ("androidx.navigation:navigation-ui-ktx:2.9.0-alpha03")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")


}

