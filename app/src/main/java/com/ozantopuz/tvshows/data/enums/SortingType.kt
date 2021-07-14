package com.ozantopuz.tvshows.data.enums

enum class SortingType(val value: Int) {
    DEFAULT(value = 0),
    ALPHABETICAL(value = 1),
    POPULARITY(value = 2),
    DATE(value = 3),
    RATING(value = 4);

    companion object {
        fun from(findValue: Int): SortingType =
            values().firstOrNull { it.value == findValue } ?: DEFAULT
    }
}

