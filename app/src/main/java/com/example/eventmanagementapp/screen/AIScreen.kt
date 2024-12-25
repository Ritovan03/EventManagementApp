package com.example.eventmanagementapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.eventmanagementapp.dataclasses.ChatMessage

@Composable
fun AIScreen(navController: NavHostController) {
    var messageText by remember { mutableStateOf("") }
    var messages by remember { mutableStateOf(listOf<ChatMessage>()) }

    val primaryColor = Color(0xFF6B4EE6) // Purple color from the image theme

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
    ) {
        // Chat messages
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(messages) { message ->
                ChatBubble(message)
            }
        }

        // Message input
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = messageText,
                    onValueChange = { messageText = it },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    placeholder = { Text("Type a message...") }
                )

                Button(
                    onClick = {
                        if (messageText.isNotBlank()) {
                            messages = messages + ChatMessage(messageText, true)
                            messages = messages + ChatMessage("Hello Timmy", false)
                            messageText = ""
                        }
                    },
                    enabled = messageText.isNotBlank(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = primaryColor,
                        disabledContainerColor = primaryColor.copy(alpha = 0.6f)
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text("Send")
                }
            }
        }
    }
}

@Composable
fun ChatBubble(message: ChatMessage) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (message.isUser) Alignment.End else Alignment.Start
    ) {
        Card(
            shape = RoundedCornerShape(
                topStart = 20.dp,
                topEnd = 20.dp,
                bottomStart = if (message.isUser) 20.dp else 4.dp,
                bottomEnd = if (message.isUser) 4.dp else 20.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = if (message.isUser)
                    Color(0xFF6B4EE6) else Color.White
            )
        ) {
            Text(
                text = message.text,
                modifier = Modifier.padding(12.dp),
                color = if (message.isUser) Color.White else Color.Black
            )
        }
    }
}

