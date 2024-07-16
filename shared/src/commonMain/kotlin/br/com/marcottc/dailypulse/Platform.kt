package br.com.marcottc.dailypulse

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform