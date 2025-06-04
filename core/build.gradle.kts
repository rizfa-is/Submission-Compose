plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.issog.submissioncompose.core"
    compileSdk = rootProject.extra.get("compileSdk") as Int?

    defaultConfig {
        minSdk = rootProject.extra.get("minSdk") as Int?

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        externalNativeBuild {
            cmake {
                cppFlags("")
            }
        }
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
    externalNativeBuild {
        cmake {
            path(file("src/main/cpp/CMakeLists.txt"))
            version = "3.22.1"
        }
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    api(libs.androidx.core.ktx)
    api(libs.androidx.lifecycle.runtime.ktx)
    api(libs.androidx.activity.compose)
    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.ui)
    api(libs.androidx.ui.graphics)
    api(libs.androidx.ui.tooling.preview)
    api(libs.androidx.material3)

    // fragment
    api(libs.androidx.navigation.compose)

    // room
    api(libs.androidx.room)
    ksp(libs.androidx.roomcompiler)
    api(libs.sql.cipher)

    // retrofit
    api(libs.retrofit2)
    api(libs.retrofit2.gsonconverter)
    api(libs.okhttp3.logging)

    // koin
    api(libs.koin.core)
    api(libs.koin.android)
    api(libs.koin.compose)

    //lifecycle
    api(libs.lifecycle.viewmodel.ktx)
    api(libs.lifecycle.livedata.ktx)

    // paging
    api(libs.androidx.paging.runtime.ktx)
    api(libs.androidx.paging.compose)

    // chucker
    debugImplementation(libs.chucker.debug)
    releaseImplementation(libs.chucker.release)

    // coil
    api(libs.coil.compose)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}