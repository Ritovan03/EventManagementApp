package com.example.eventmanagementapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.ContentAlpha
import com.example.eventmanagementapp.R
import com.example.eventmanagementapp.screen.AddEventScreen
import com.example.eventmanagementapp.navigation.BottomNavItem
import com.example.eventmanagementapp.screen.EventScreen
import com.example.eventmanagementapp.screen.ProfileScreen



@Composable
fun HomeScreen(navController: NavHostController) {
    var selectedItem by remember { mutableStateOf(0) }

    val items = listOf(
        BottomNavItem(
            label = "Home",
            icon = painterResource(R.drawable.home_outlined),
            selectedIcon = painterResource(R.drawable.home_filled),
            route = "homecontent"
        ),
        BottomNavItem(
            label = "Ask AI",
            icon = painterResource(R.drawable.help_outlined),
            selectedIcon = painterResource(R.drawable.help_filled),
            route = "ai"
        ),
        BottomNavItem(
            label = "",
            icon = painterResource(R.drawable.add_filled),
            selectedIcon = painterResource(R.drawable.add_filled),
            route = "add_event"
        ),
        BottomNavItem(
            label = "Events",
            icon = painterResource(R.drawable.calendar_outlined),
            selectedIcon = painterResource(R.drawable.calendar_filled),
            route = "event"
        ),
        BottomNavItem(
            label = "Profile",
            icon = painterResource(R.drawable.person_outlined),
            selectedIcon = painterResource(R.drawable.person_filled),
            route = "profile"
        ),
    )

    Scaffold(
        bottomBar = {
            Box {
                BottomAppBar(
                    modifier = Modifier
                        .height(88.dp)
                        .background(Color.White),
                    containerColor = Color.White,
                    content = {
                        items.forEachIndexed { index, item ->
                            if (index != 2) {
                                IconButton(
                                    onClick = { selectedItem = index },
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Icon(
                                            painter = if (selectedItem == index) item.selectedIcon else item.icon,
                                            contentDescription = item.label,
                                            tint = if (selectedItem == index) MaterialTheme.colorScheme.primary else LocalContentColor.current.copy(
                                                alpha = ContentAlpha.medium
                                            )
                                        )

                                        Text(
                                            text = item.label,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = if (selectedItem == index) MaterialTheme.colorScheme.primary else LocalContentColor.current.copy(
                                                alpha = ContentAlpha.medium
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                )
                FloatingActionButton(
                    onClick = { selectedItem = 2 },
                    containerColor = colorResource(id = R.color.light_blue),
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(y = (-28).dp) // Adjust this offset to control the button's position
                        .size(48.dp) // Adjust the size if needed,
                    , shape = CircleShape
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedItem) {
                0 -> HomeContent(navController)
                1 -> AIScreen(navController)
                2 -> AddEventScreen(navController)
                3 -> EventScreen(navController)
                4 -> ProfileScreen(navController)
            }
        }
    }
}


