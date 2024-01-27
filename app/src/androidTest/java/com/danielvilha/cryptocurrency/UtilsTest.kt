package com.danielvilha.cryptocurrency

import com.danielvilha.cryptocurrency.util.Utils.Companion.textIsActive
import com.danielvilha.cryptocurrency.util.Utils.Companion.toDateString
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Daniel Ferreira de Lima Vilha 26/01/2024.
 */
class UtilsTest {
    @Test
    fun testTextIsActive() {
        assertEquals("active", textIsActive(true))
        assertEquals("inactive", textIsActive(false))
    }

    @Test
    fun testToDateString() {
        val date = "2023-12-05T12:34:56Z"
        assertEquals("05 Dec, 2023", toDateString(date))

        val emptyDate: String? = null
        assertEquals("No date", toDateString(emptyDate))
    }
}