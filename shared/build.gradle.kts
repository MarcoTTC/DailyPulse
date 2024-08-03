plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization") version "1.9.20"
    alias(libs.plugins.sqlDelight)
    alias(libs.plugins.composeMultiplatformPlugin)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    jvm("desktop")

    js(IR) {
        binaries.executable()
        browser()
    }

    sourceSets {
        val desktopMain by getting
        val jsMain by getting

        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.kotlinx.datetime)
            implementation(libs.koin.core)
            implementation(libs.sql.coroutines.extensions)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.components.resources)
            implementation(compose.material3)
            implementation(libs.compose.material)
            implementation(libs.koin.compose)
            implementation(libs.kamel.image)
            implementation(libs.voyager.navigator)
            implementation(libs.voyager.transitions)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        androidMain.dependencies {
            implementation(libs.androidx.lifecycle.viewmodel.ktx)
            implementation(libs.ktor.client.android)
            implementation(libs.sql.android.driver)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.sql.native.driver)
        }

        desktopMain.dependencies {
            implementation(libs.ktor.client.cio)
            implementation(libs.sql.desktop.driver)
        }

        jsMain.dependencies {
            implementation(libs.ktor.client.js)
        }
    }
}

android {
    namespace = "br.com.marcottc.dailypulse"
    compileSdk = 34
    defaultConfig {
        minSdk = 28
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

sqldelight {
    databases {
        create(name = "DailyPulseDatabase") {
            packageName.set("br.com.marcottc.dailypulse.db")
        }
    }
}

compose.experimental {
    web.application {}
}