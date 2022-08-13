package com.zakin.readerzmultiplatform.models

import com.zakin.readerzmultiplatform.models.enums.Tags

data class Manga(
    var id: String? = "",
    var name: String? = "",
    var link: String? = "",
    var resume: String? = "",
    var coverLink: String? = "",
    var author: String? = "",
    var releaseYear: String? = "",
    var tag: Tags? = Tags.NONE,
    var chapters: ArrayList<Chapter>? = ArrayList()
)
