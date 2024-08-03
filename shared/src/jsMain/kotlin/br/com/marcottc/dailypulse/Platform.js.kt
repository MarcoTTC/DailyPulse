package br.com.marcottc.dailypulse

actual class Platform {
    actual val osName: String
        get() = "Unknown"
    actual val osVersion: String
        get() = "Unknown"
    actual val deviceModel: String
        get() = "Unknown"
    actual val density: Int
        get() = 0

    actual fun logSystemInfo() {
        println("($osName, $osVersion, $deviceModel, $density)")
    }

}