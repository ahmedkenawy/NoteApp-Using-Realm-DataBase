package com.a7medkenawy.noteapp.model

import android.os.Parcelable
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import kotlinx.android.parcel.Parcelize

//Note Table consists of 2 parameters 1-id (primary key) 2- content
//annotated with (Parcelize) and implement (Parcelable) to send it easily between activities
@Parcelize
open class Note(
    @PrimaryKey
    var id: String? = null,
    var content: String? = "null",
) : RealmObject(),Parcelable