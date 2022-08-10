package com.zakin.readerzmultiplatform.models

import com.zakin.readerzmultiplatform.models.enums.Badges
import java.time.LocalDate

data class Chapter(
    val id: String? = "",
    val number: String? = "",
    val name: String? = "",
    val subName: String? = "",
    val Badge: Badges? = Badges.NONE,
    val addedDate: LocalDate? = null,
    val link: String? = "",
    val pages: ArrayList<Page>? = ArrayList()
)
