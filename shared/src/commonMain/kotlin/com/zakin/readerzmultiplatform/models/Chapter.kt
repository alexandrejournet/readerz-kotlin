package com.zakin.readerzmultiplatform.models

import com.zakin.readerzmultiplatform.models.enums.Badges

data class Chapter(
    var id: String = "",
    var number: String = "",
    var name: String = "",
    var subName: String = "",
    var badge: Badges = Badges.NONE,
    var addedDate: String = "",
    var link: String = "",
    var pages: ArrayList<Page> = ArrayList(),
)
