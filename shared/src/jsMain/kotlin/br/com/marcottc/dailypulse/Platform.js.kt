package br.com.marcottc.dailypulse

import kotlinx.browser.window

actual class Platform {
    actual val osName: String
        get() = window.navigator.platform
    actual val osVersion: String
        get() = ""
    actual val deviceModel: String
        get() = window.navigator.userAgent
    actual val density: Int
        get() = window.devicePixelRatio.toInt()

    actual fun logSystemInfo() {
        println("($osName, $osVersion, $deviceModel, $density)")
    }

}