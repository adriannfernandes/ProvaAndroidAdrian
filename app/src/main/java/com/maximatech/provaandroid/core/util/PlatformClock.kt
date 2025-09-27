package com.maximatech.provaandroid.core.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun formatSmartDate(rawDate: String): String {
    return try {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
        val dateTime = OffsetDateTime.parse(rawDate, inputFormatter)

        val today = OffsetDateTime.now(ZoneId.systemDefault())

        return if (dateTime.toLocalDate().isEqual(today.toLocalDate())) {
            dateTime.format(DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault()))
        } else {
            dateTime.format(DateTimeFormatter.ofPattern("dd MMM", Locale("pt", "BR")))
        }
    } catch (e: Exception) {
        rawDate
    }
}