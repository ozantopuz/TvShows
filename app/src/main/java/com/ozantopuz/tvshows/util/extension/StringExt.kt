package com.ozantopuz.tvshows.util.extension

import java.text.SimpleDateFormat
import java.util.*

fun String?.ignoreNull(defaultValue: String = ""): String = this ?: defaultValue
fun String?.toDate(): Date? =
    SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(this.ignoreNull())
