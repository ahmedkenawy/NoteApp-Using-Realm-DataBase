package com.a7medkenawy.noteapp.model

import android.os.Parcel
import android.os.Parcelable
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
open class Note(
    @PrimaryKey
    var id: String? = null,
    var content: String? = "null",
) : RealmObject(),Parcelable