plugins {
    alias(libs.plugins.android.application)
}
val spotifyClientId = providers.environmentVariable("SPOTIFY_CLIENT_ID").getOrElse("")

android {
    namespace = "com.jench.vanek"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        // ...your existing applicationId, minSdk, targetSdk, versionCode, etc...
        buildConfigField("String", "SPOTIFY_CLIENT_ID", "\"$spotifyClientId\"")
    }

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.jench.vanek"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = true
                packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    implementation(files("libs/spotify-app-remote-release-0.8.0.aar"))
    implementation("com.google.code.gson:gson:2.10.1")
}