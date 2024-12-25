package com.example.eventmanagementapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eventmanagementapp.R

data class EventDesc(
    val id: Int,
    val title: String,
    val date: String,
    val time: String,
    val description: String,
    val imageRes: Int,
    val location: String,
    val organizer: String,
    val price: String
)

@Composable
fun EventCard(event: EventDesc, onEventClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onEventClick),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .height(80.dp)
        ) {
            Image(
                painter = painterResource(id = event.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .weight(1f)
            ) {
                Text(
                    text = "${event.date} - ${event.time}",
                    color = Color(0xFF6B4EE6),
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = event.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    var showFilterSheet by remember { mutableStateOf(false) }

    val events = remember {
        listOf(
            EventDesc(1, "A virtual evening of smooth jazz", "1ST MAY", "SAT-2:00 PM",
                "Join us for an evening of smooth jazz with renowned artists.",
                android.R.drawable.ic_menu_gallery, "Virtual Event", "Jazz Society", "Free"),
            EventDesc(2, "Jo malone london's mother's day", "1ST MAY", "SAT-2:00 PM",
                "Celebrate mother's day with exclusive Jo Malone London events.",
                android.R.drawable.ic_menu_gallery, "London, UK", "Jo Malone", "£50"),
            EventDesc(3, "Women's leadership conference", "1ST MAY", "SAT-2:00 PM",
                "Empowering women leaders through networking and workshops.",
                android.R.drawable.ic_menu_gallery, "Conference Center", "WLC", "£100"),
            EventDesc(4, "International kids safe parents night out", "1ST MAY", "SAT-2:00 PM",
                "A safe and fun evening for kids while parents enjoy their time.",
                android.R.drawable.ic_menu_gallery, "Community Center", "SafeKids Org", "£25"),
            EventDesc(5, "International gala music festival", "1ST MAY", "SAT-2:00 PM",
                "A celebration of international music and culture.",
                android.R.drawable.ic_menu_gallery, "City Hall", "World Music", "£75")
        )
    }

    val filteredEvents = events.filter {
        it.title.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Search") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            // Search Bar
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Search...") },
                    leadingIcon = { Icon(Icons.Default.Search, null) },
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color(0xFFF5F5F5),
                        unfocusedBorderColor = Color.Transparent
                    )
                )

                Button(
                    onClick = { showFilterSheet = true },
                    modifier = Modifier.padding(start = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6B4EE6)
                    )
                ) {
                    Text("Filters")
                }
            }

            // Events List
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(filteredEvents) { event ->
                    EventCard(event = event, onEventClick = {
                        navController.navigate("eventDetail/${event.id}")
                    })
                }
            }
        }

        // Filter Bottom Sheet
        if (showFilterSheet) {
            FilterBottomSheet(
                onDismiss = { showFilterSheet = false }
            )
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(onDismiss: () -> Unit) {
    ModalBottomSheet(
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                "Filters",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Filter options will be added here")
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun EventDetailScreen(event: EventDesc) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = event.imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = event.title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "${event.date} - ${event.time}",
            color = Color(0xFF6B4EE6)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Location: ${event.location}")
        Text(text = "Organizer: ${event.organizer}")
        Text(text = "Price: ${event.price}")

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = event.description,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { /* Handle booking */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6B4EE6)
            )
        ) {
            Text("Book Now")
        }
    }
}
