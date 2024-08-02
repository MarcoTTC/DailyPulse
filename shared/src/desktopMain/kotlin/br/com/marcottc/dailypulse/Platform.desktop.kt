package br.com.marcottc.dailypulse

actual class Platform {
    actual val osName: String
        get() = System.getProperty("os.name") ?: "Desktop"
    actual val osVersion: String
        get() = System.getProperty("os.version") ?: "Unknown"
    actual val deviceModel: String
        get() = "Desktop"
    actual val density: Int
        get() = 0

    actual fun logSystemInfo() {
        System.out.println("($osName, $osVersion, $deviceModel, $density)")
    }

}