import java.util.Properties
import java.io.File
import java.io.FileInputStream

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
    kotlin("plugin.serialization")
    id("org.jetbrains.kotlin.plugin.compose")
}

// Function to load local properties
fun gradleLocalProperties(rootDir: File): Properties {
    val properties = Properties()
    val localPropertiesFile = File(rootDir, "local.properties")
    if (localPropertiesFile.exists()) {
        FileInputStream(localPropertiesFile).use { fileInputStream ->
            properties.load(fileInputStream)
        }
    }
    return properties
}
android {
    namespace = "com.example.unsplash"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.unsplash"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }


        buildFeatures {
            buildConfig = true
        }

        buildTypes {
            getByName("release")
             {
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
        kotlinOptions {
            jvmTarget = "1.8"
        }
        buildFeatures {
            /*compose = true*/
        }
        composeCompiler {
            enableStrongSkippingMode = true
        }
        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
        //kotlin
        val properties:Properties = gradleLocalProperties(rootDir)
        buildConfigField ("String","API_KEY","\"${properties.getProperty("API_KEY")}\"")
    }
}
dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    implementation("androidx.activity:activity-compose:1.9.1")
    implementation(platform("androidx.compose:compose-bom:2024.06.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.06.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.10.0")
    implementation("com.squareup.retrofit2:converter-gson:2.7.2")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    //Coil for image loading
    implementation("io.coil-kt:coil-compose:2.6.0")

    //Kotlin Extensions and Coroutines support for room
    //implementation("androidx.room:room-ktx:2.6.1")

    //Room
    implementation("androidx.room:room-ktx:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-paging:2.6.1")

    //kotlinx-serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
//Paging
    implementation("androidx.paging:paging-compose:3.3.1")
    implementation("androidx.paging:paging-runtime-ktx:3.3.1")


    //Json-converter
    implementation ("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")

    //Navigation
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation("androidx.hilt:hilt-navigation:1.2.0")
    //dagger-hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    ksp("com.google.dagger:hilt-compiler:2.51.1")


    implementation("androidx.compose.foundation:foundation-android:1.6.8")
    implementation("androidx.compose.material:material-icons-core-android:1.6.8")
    implementation("androidx.compose.material:material-icons-extended:1.6.8")
}