package br.com.marcottc.dailypulse

import kotlin.test.Test
import kotlin.test.assertTrue

class IosGreetingTest {

    @Test
    fun testExample() {
        assertTrue(Platform().osName.contains("iOS"), "Check iOS is mentioned")
    }
}