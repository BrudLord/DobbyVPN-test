plugins {
    kotlin("multiplatform") version "1.9.10"
    id("org.jetbrains.compose") version "1.5.2"
    id("dev.hydraulic.conveyor") version "1.12"
}

group = "com.example"
version = "1.0"

repositories {
    mavenCentral()
    google()
}

kotlin {
    jvm {
        withJava()
        compilations.all {
            kotlinOptions {
                jvmTarget = "17" // Укажите поддерживаемую версию, например, "11" или "17"
            }
            jvmToolchain {
                languageVersion.set(JavaLanguageVersion.of(17))
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.google.code.gson:gson:2.10.1")
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation("net.java.dev.jna:jna:5.13.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

                implementation("org.jetbrains.skiko:skiko-awt-runtime-windows-x64:0.7.97")
                implementation("org.jetbrains.skiko:skiko-awt-runtime-macos-x64:0.7.97")
                implementation("org.jetbrains.skiko:skiko-awt-runtime-macos-arm64:0.7.97")
                implementation("org.jetbrains.skiko:skiko-awt-runtime-linux-x64:0.7.97")
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Dmg,
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Msi,
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Deb,
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Exe
            )

            packageName = "CombinedVPNClient"
            packageVersion = "1.0.0"

            macOS {
                iconFile.set(project.file("logos/logo.icns"))
            }
            windows {
                iconFile.set(project.file("logos/logo.ico"))
            }
            linux {
                iconFile.set(project.file("logos/logo.png"))
            }
            appResourcesRootDir.set(project.file("src/jvmMain/resources"))
        }
    }

}