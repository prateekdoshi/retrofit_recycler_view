package com.prateek.android.retrofit_recyclerview.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(val id: Int?,
                val userId: Int? = null,
                val title: String? = null,
                val content: String? = null) : Parcelable {
}