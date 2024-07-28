package com.example.eventmanagementapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.eventmanagementapp.R

@Composable
fun HomeContent(
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF4A4AFF))
    ) {
        TopBar()
        SearchBar()
        CategoryButtons()
        // Removed EventsSection as it's not visible in the image
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Menu",
            tint = Color.White
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                "New Yourk, USA",  // Kept the typo as shown in the image
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Location Dropdown",
                tint = Color.White
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.bell),
            contentDescription = "Notifications",
            tint = Color.White
        )
    }
}

@Composable
fun SearchBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(Color.White, RoundedCornerShape(24.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            tint = Color.Gray
        )
        Text(
            "Search...",
            color = Color.Gray,
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.filter),
            contentDescription = "Filters",
            tint = Color.Gray
        )
    }
}

@Composable
fun CategoryButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CategoryButton("Sports", Color(0xFFFF6B6B), R.drawable.sports)
        CategoryButton("Music", Color(0xFFFFD93D), R.drawable.music)
        CategoryButton("Food", Color(0xFF6BCB77), R.drawable.cutlery)
    }
}

@Composable
fun CategoryButton(text: String, color: Color, iconResId: Int) {
    Button(
        onClick = { },
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(24.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text, color = Color.White, fontSize = 14.sp)
    }
}

//@Composable
//fun EventsSection() {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Color.White, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
//            .padding(16.dp)
//    ) {
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text("Upcoming Events", fontWeight = FontWeight.Bold)
//            Text("See All", color = Color.Blue)
//        }
//        Spacer(modifier = Modifier.height(16.dp))
//        LazyRow(
//            horizontalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            item { EventCard("International Band Mu...", "10\nJUNE", Color(0xFFFFC0CB)) }
//            item { EventCard("Jo Malone", "10\nJUNE", Color(0xFFADD8E6)) }
//        }
//        Spacer(modifier = Modifier.height(16.dp))
//        InviteFriendsCard()
//    }
//}
//
//@Composable
//fun EventCard(title: String, date: String, color: Color) {
//    Card(
//        modifier = Modifier
//            .width(160.dp)
//            .height(200.dp),
//        shape = RoundedCornerShape(16.dp)
//    ) {
//        Column {
//            Box(
//                modifier = Modifier
//                    .weight(1f)
//                    .fillMaxWidth()
//                    .background(color)
//            ) {
//                Text(
//                    date,
//                    color = Color.White,
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier
//                        .align(Alignment.TopEnd)
//                        .padding(8.dp)
//                )
//            }
//            Column(modifier = Modifier.padding(8.dp)) {
//                Text(title, fontWeight = FontWeight.Bold)
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    // Add user avatars here
//                    Text("+20 Going", color = Color.Gray)
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun InviteFriendsCard() {
//    Card(
//        modifier = Modifier.fillMaxWidth(),
//        shape = RoundedCornerShape(16.dp),
//        colors = CardDefaults.cardColors(containerColor = Color(0xFFE6E6FA))
//    ) {
//        Row(
//            modifier = Modifier.padding(16.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Column(modifier = Modifier.weight(1f)) {
//                Text("Invite your friends")
//                Text("Get $20 for ticket", color = Color.Gray)
//            }
//            Button(
//                onClick = { },
//                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
//                shape = RoundedCornerShape(16.dp)
//            ) {
//                Text("Invite", color = Color.Black)
//            }
//        }
//    }
//}
