package com.danielvilha.cryptocurrency.util

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.danielvilha.cryptocurrency.R
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Created by Daniel Ferreira de Lima Vilha 26/11/2023.
 */
class Utils {
    companion object {
        fun textIsActive(isActive: Boolean): String =
            if (isActive) "active" else "inactive"

        fun toDateString(date: String?): String {
            val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
            val output = SimpleDateFormat("dd MMM, yyyy", Locale.ENGLISH)

            if (date.isNullOrEmpty())
                return "No date"

            val dateFormatter = input.parse(date)
            return output.format(dateFormatter!!)
        }

        fun aboutThisApp(context: Context) : AlertDialog {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(context.getString(R.string.about_this_app))
            builder.setMessage(context.getString(R.string.message_description))

            return builder.create()
        }
    }
}
