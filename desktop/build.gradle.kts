import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatformPlugin)
}

kotlin {
    jvm {
        withJava()
    }

    sourceSets {
        named("jvmMain") {
            dependencies {
                implementation(projects.shared)
                implementation(compose.desktop.currentOs)
                implementation(libs.koin.core)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)

            packageName = "br.com.marcottc.dailypulse"
            packageVersion = "1.0.0"

            macOS {
                bundleID = "br.com.marcottc.dailypulse"
            }

            windows {

            }

            linux {

            }
        }
    }
}