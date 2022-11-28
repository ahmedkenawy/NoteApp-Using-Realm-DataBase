package com.a7medkenawy.noteapp.model

import io.realm.RealmObject

open class Note(
    var id: String? = null,
    var content: String? = null,
) : RealmObject()