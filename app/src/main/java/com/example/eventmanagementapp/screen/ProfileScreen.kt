package com.example.eventmanagementapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavHostController) {
    val interests = listOf(
        Interest("Games Online", Color(0xFF6B4EE6)),
        Interest("Concert", Color(0xFFFF4B60)),
        Interest("Music", Color(0xFFFF9666)),
        Interest("Art", Color(0xFF8B5CF6)),
        Interest("Movie", Color(0xFF10B981)),
        Interest("Others", Color(0xFF06B6D4))
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Profile Header
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Profile Image
                Image(
                    painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                // Name
                Text(
                    text = "Ashfak Sayem",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                )

                // Stats Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    StatItem("350", "Following")
                    Spacer(modifier = Modifier.width(32.dp))
                    StatItem("346", "Followers")
                }

                // Edit Profile Button
                OutlinedButton(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFF6B4EE6)
                    )
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_edit),
                        contentDescription = "Edit",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Edit Profile")
                }
            }

            // Personal Info Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                InfoSection("Location", "Mumbai, India")
                InfoSection("Age", "28 years")

                // About Me Section
                Text(
                    text = "About Me",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(
                    text = "Enjoy your favorite dish and a lovely your friends and family and have a great time. Food from local food trucks will be available for purchase.",
                    color = Color.Gray
                )
                TextButton(
                    onClick = { },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = Color(0xFF6B4EE6)
                    )
                ) {
                    Text("Read More")
                }
            }

            // Interests Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Interest",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                TextButton(
                    onClick = { },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = Color(0xFF6B4EE6)
                    )
                ) {
                    Text("CHANGE")
                }
            }

            // Interest Tags
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                items(interests) { interest ->
                    InterestChip(interest)
                }
            }
        }
    }
}

@Composable
fun StatItem(count: String, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = count,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Text(
            text = label,
            color = Color.Gray,
            fontSize = 14.sp
        )
    }
}

@Composable
fun InfoSection(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Medium,
            color = Color.Gray
        )
        Text(
            text = value,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun InterestChip(interest: Interest) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = interest.color.copy(alpha = 0.1f),
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Text(
            text = interest.label,
            color = interest.color,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            fontSize = 14.sp
        )
    }
}

data class Interest(
    val label: String,
    val color: Color
)