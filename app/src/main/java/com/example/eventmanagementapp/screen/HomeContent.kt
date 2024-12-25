package com.example.eventmanagementapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.eventmanagementapp.R
import com.example.eventmanagementapp.dataclasses.Filter


@Composable
fun HomeContent(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    colorResource(id = R.color.light_blue),
                    shape = RoundedCornerShape(bottomEnd = 24.dp, bottomStart = 24.dp)
                )
                .height(170.dp) // Increase the height of the Box
        ) {
            Column {
                TopBar()
                SearchBarr()
                Spacer(modifier = Modifier.height(20.dp)) // Add space to accommodate the FilterRow
            }
        }
        FilterRow(modifier = Modifier.offset(y = (-40).dp)) // Decrease the vertical offset
        UpcomingEvents(modifier = Modifier.offset(y = (-12).dp))
        Spacer(modifier = Modifier.height(8.dp))
        InviteFriends()
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { /* TODO: Handle settings click */ }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_settings_24), // Replace with the actual settings icon resource
                contentDescription = "Settings",
                tint = Color.White,
                modifier = Modifier.size(36.dp)
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(){
                Text(text = "Current Location", color = Color.White, fontSize = 18.sp)
                Icon(imageVector = Icons.Default.ArrowDropDown,
                    tint = Color.White,
                    contentDescription = null)
            }
            Text(text = "Mumbai, India", color = Color.White, fontSize = 14.sp)
        }
        IconButton(
            onClick = { /* TODO: Handle notification click */ },
            modifier = Modifier
                .size(36.dp)
                .background(Color.White, shape = CircleShape)
                .padding(8.dp)
                .alpha(0.7f)
        ) {
            Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = colorResource(id = R.color.light_blue))
        }
    }
}

@Composable
fun SearchBarr() {
    var textState by remember { mutableStateOf(TextFieldValue("")) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(
                Color.Transparent,
                shape = MaterialTheme.shapes.medium
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        BasicTextField(
            value = textState,
            onValueChange = { textState = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp),
            textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
            singleLine = true,
            decorationBox = { innerTextField ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.White,
                        )
                    Spacer(modifier = Modifier.width(2.dp))

                    Text(text = "|",
                        color = Color.Gray,
                        )

                    Spacer(modifier = Modifier.width(8.dp))
                    if (textState.text.isEmpty()) {
                        Text("Search", color = Color.Gray)
                    }
                    innerTextField()
                }
            },
            cursorBrush = SolidColor(Color.White)
        )
    }
}

@Composable
fun FilterRow(modifier: Modifier = Modifier) {
    // Define a list of filters with unique values
    val filters = listOf(
        Filter("Sports", R.color.sports_red, R.drawable.baseline_sports_basketball_24),
        Filter("Music", R.color.music_orange, R.drawable.baseline_music_note_24),
        Filter("Food", R.color.food_green, R.drawable.baseline_restaurant_menu_24),
        Filter("Tech", R.color.tech_blue, R.drawable.baseline_computer_24)
    )

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(filters) { filter ->
            // Access color and drawable resources properly
            val backgroundColor = colorResource(id = filter.color)
            val icon = painterResource(id = filter.icon)

            FilterButton(filter = filter, backgroundColor = backgroundColor, icon = icon)
        }
    }
}

@Composable
fun FilterButton(filter: Filter, backgroundColor: Color, icon: Painter) {
    Button(
        onClick = { /* TODO: Handle filter click */ },
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        modifier = Modifier.height(40.dp)
    ) {
        Icon(
            painter = icon,
            contentDescription = filter.name,
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = filter.name, color = Color.White)
    }
}


@Composable
fun UpcomingEvents(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header row remains the same
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Upcoming Events",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "See All",
                    modifier = Modifier.alpha(0.7f),
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Icon(
                    Icons.Default.PlayArrow,
                    contentDescription = "See All",
                    tint = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                EventCard(
                    date = "10",
                    month = "JUNE",
                    title = "International Band Mu...",
                    location = "36 Guild Street London, UK",
                    attendees = 20,
                    imageResId = R.drawable.cat_dummy // Replace with your actual drawable resource
                )
            }
            item {
                EventCard(
                    date = "3",
                    month = "FEB",
                    title = "Drake's Birthday Party",
                    location = "Toronto,Canada",
                    attendees = 20,
                    imageResId = R.drawable.cat_dummy // Replace with your actual drawable resource
                )
            }
            item {
                EventCard(
                    date = "10",
                    month = "JUL",
                    title = "Jo Malone London's...",
                    location = "Radius Gallery • Santa Cruz, CA",
                    attendees = 12,
                    imageResId = R.drawable.cat_dummy // Replace with your actual drawable resource
                )
            }
        }
    }
}

@Composable
fun EventCard(
    date: String,
    month: String,
    title: String,
    location: String,
    attendees: Int,
    imageResId: Int
) {
    Card(
        modifier = Modifier
            .width(250.dp)
            .height(280.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Background Image
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = "Event background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Overlay to ensure text is readable
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f))
            )

            // Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .background(Color.White, RoundedCornerShape(8.dp))
                            .padding(8.dp)
                    ) {
                        Text(
                            text = date,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            color = Color.Black
                        )
                        Text(
                            text = month,
                            fontSize = 12.sp,
                            color = Color.Black
                        )
                    }
                    Icon(
                        Icons.Default.FavoriteBorder,
                        contentDescription = "Bookmark",
                        tint = Color.White
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.LocationOn,
                        contentDescription = "Location",
                        modifier = Modifier.size(16.dp),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = location,
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = "Attendees",
                        modifier = Modifier.size(16.dp),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "+$attendees Going",
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun InviteFriends() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.light_cyan)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    )
    {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),

        )
        {
            Column {
                Text(text = "Invite your friends",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Get $20 for ticket",
                    fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                //share logic
                },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.cyan),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(0.dp)
                ) {
                    Text(text = "INVITE",
                        fontSize = 16.sp)

                }


            }
            Image(painter = painterResource(id = R.drawable.gift_boxes),
                contentDescription = null,
                modifier = Modifier.size(200.dp))

        }

    }
}
