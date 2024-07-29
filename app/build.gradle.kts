plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

}

android {
    namespace = "teno.app.loginandregisterandroidstudio"
    compileSdk = 34

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    defaultConfig {
        applicationId = "teno.app.loginandregisterandroidstudio"
        minSdk = 28
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
            buildConfigField ("String", "API_BASE_URL", "\"https://api.parental.cl\"")
        }
        debug {
            buildConfigField ("String", "API_BASE_URL", "\"http://192.168.0.26:8080\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    //dependencia consumier api
    //se debe agregar :
    //<uses-permission android:name="android.permission.INTERNET"/>
    //<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    //en AndroidManifest.xml

    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    //implementation(libs.retrofit2.converter.gson)
    /*implementation ("com.squareup.retrofit2:retrofit:2.1.0")
    implementation ("com.google.code.gson:gson:2.6.2")
    implementation ("com.squareup.retrofit2:converter-gson:2.1.0")*/

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    //material
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)




    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)



}
