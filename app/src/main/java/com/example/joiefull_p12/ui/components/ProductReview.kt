package com.example.joiefull_p12.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductReview() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        GlideImage(
            model = "https://randomuser.me/api/portraits/women/44.jpg", // Avatar fictif
            contentDescription = "Photo de profil utilisateur",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        var selectedStars by remember { mutableStateOf(0) }

        Row {
            repeat(5) { index ->
                val starNumber = index + 1
                Icon(
                    Icons.Default.Star,
                    contentDescription = "$starNumber étoile",
                    tint = if (index < selectedStars) Color(0xFFFF9800) else Color.Gray,
                    modifier = Modifier
                        .size(48.dp)
                        .clickable { selectedStars = index + 1 }
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Champ de texte pour avis
    OutlinedTextField(
        value = "",
        onValueChange = { /* gestion saisie */ },
        placeholder = {
            Text(
                "Partagez ici vos impressions sur cette pièce",
                color = Color(0xFF555555)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    )
}
