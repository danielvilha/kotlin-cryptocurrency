package com.danielvilha.cryptocurrency

import com.danielvilha.cryptocurrency.ui.binding.toDateString
import org.junit.Test

/**
 * Created by danielvilha on 24/10/21
 * https://github.com/danielvilha
 */
class ToDateStringUnitTest {

    @Test
    fun to_date_string() {
        val date = toDateString("2021-10-24T00:00:00Z")

        assert(date != "No date")
    }
}