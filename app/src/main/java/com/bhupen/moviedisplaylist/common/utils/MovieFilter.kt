package com.bhupen.moviedisplaylist.common.utils

object MovieFilter {
    enum class SORT(val param: String) {
        ASCENDING("primary_release_date.asc"),
        DESCENDING("primary_release_date.desc")
    }
}