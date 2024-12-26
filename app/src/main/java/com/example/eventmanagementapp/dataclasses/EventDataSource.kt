package com.example.eventmanagementapp.dataclasses

import com.example.eventmanagementapp.R

object EventDataSource {
    private val eventsList = mutableListOf(
        EventDesc(
            id = 1,
            title = "International Band Music Concert",
            date = "14 December, 2021",
            time = "Tuesday, 4:00PM - 5:00PM",
            description = "Enjoy your favorite dishes and a lovely time with friends and have a great time. Food from local food trucks will be available.",
            imageRes = R.drawable.baseline_music_note_24,
            location = "Gala Convention Center",
            organizer = "Ashfak Sayem",
            organizerImageRes = R.drawable.baseline_music_note_24,
            price = "$120",
            goingCount = 20,
            address = "36 Guild Street London, UK"
        ),
        EventDesc(
            id = 2,
            title = "Jo Malone London's Mother's Day",
            date = "1ST MAY",
            time = "SAT-2:00 PM",
            description = "Celebrate mother's day with exclusive Jo Malone London events.",
            imageRes = R.drawable.baseline_music_note_24,
            location = "London, UK",
            organizer = "Jo Malone",
            organizerImageRes = R.drawable.baseline_music_note_24,
            price = "£50",
            goingCount = 15,
            address = "24 Oxford Street, London, UK"
        ),
        EventDesc(
            id = 3,
            title = "Women's Leadership Conference",
            date = "15 MAY",
            time = "THU-9:00 AM",
            description = "Join us for an inspiring day of leadership talks and networking opportunities.",
            imageRes = R.drawable.baseline_music_note_24,
            location = "Business Center",
            organizer = "Women in Leadership",
            organizerImageRes = R.drawable.baseline_music_note_24,
            price = "£75",
            goingCount = 45,
            address = "100 Business Avenue, London, UK"
        )
    )

    fun getEvents(): List<EventDesc> = eventsList.toList()

    fun getEventById(id: Int): EventDesc? = eventsList.find { it.id == id }

    fun addEvent(event: EventDesc) {
        eventsList.add(event)
    }
}