// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
}
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Thêm dòng này
        classpath ("com.google.gms:google-services:4.3.15")  // Sử dụng phiên bản mới nhất
    }
}