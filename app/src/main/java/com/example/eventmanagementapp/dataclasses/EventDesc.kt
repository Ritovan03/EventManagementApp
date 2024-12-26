package com.example.eventmanagementapp.dataclasses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventDesc(
    val id: Int,
    val title: String,
    val date: String,
    val time: String,
    val description: String,
    val imageRes: Int,
    val location: String,
    val organizer: String,
    val organizerImageRes: Int,
    val price: String,
    val goingCount: Int = 0,
    val address: String
) : Parcelable
