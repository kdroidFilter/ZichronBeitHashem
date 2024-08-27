plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.kdroid.zichronbeithashem"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.kdroid.zichronbeithashem"
        minSdk = 28
        targetSdk = 35
        versionCode = 20242709
        versionName = "0.2.2"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        setProperty("archivesBaseName", applicationId + "(" + versionName + ")")
    }

    buildTypes {
        release {
            isMinifyEnabled = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            isDebuggable = false
            signingConfig = signingConfigs.getByName("debug")
        }
        debug {
            isDebuggable = true

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    //Compose Widget
    implementation(libs.glance.appwidget)
    implementation(libs.glance.material)
    implementation(libs.androidx.glance.preview)

    //ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    //Navigation
   // implementation(libs.navigation.compose)
   // implementation(libs.koin.androidx.compose.navigation)

    //Serialization
    implementation(libs.kotlinx.serialization.json)

    //Koin
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.android.compose)

    //Ui extension
    implementation(libs.androidx.ui.text.google.fonts)
    implementation(libs.androidx.material.icons.extended)

    //Zmanim Api
    implementation(libs.zmanim)

    //Splash Screen
    implementation(libs.androidx.core.splashscreen)

    //Jetpack Datastore
    implementation(libs.androidx.datastore.preferences)

    //Worker
    implementation(libs.androidx.work.runtime.ktx)

    //Navigation
    implementation(libs.androidx.compose.material3.adaptive.navigation.suite)
    implementation(libs.androidx.compose.material3.adaptive.navigation)
    implementation(libs.androidx.compose.material3.adaptive)




    testImplementation(libs.junit)
    testImplementation (libs.mockito.core)
    testImplementation (libs.mockito.inline)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}