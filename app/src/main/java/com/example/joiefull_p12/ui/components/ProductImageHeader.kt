package com.example.joiefull_p12.ui.components

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.joiefull_p12.data.models.ProductModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductImageHeader(
    product: ProductModel,
    navController: NavController
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(430.dp)
            .clip(RoundedCornerShape(20.dp))
            .semantics(mergeDescendants = true) {  }
    ) {
        GlideImage(
            model = product.imageUrl,
            contentDescription = product.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .semantics {
                    this.contentDescription = "Image du produit ${product.title}"
                }
        )

        // Bouton retour
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(8.dp)
                .size(36.dp)
                .semantics {
                    contentDescription = "Retour à la liste de produits"
                }
        ) {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = "Retour",
                tint = Color.Black
            )
        }

        // Bouton partage
        IconButton(
            onClick = {
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "Découvrez ce produit : ${product.title} à ${product.price}€. 🔗 ${product.imageUrl}"
                    )
                }
                context.startActivity(
                    Intent.createChooser(shareIntent, "Partager via")
                )
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .size(36.dp)
                .semantics {
                    contentDescription = "Partager le produit ${product.title}"
                }
        ) {
            Icon(
                Icons.Default.Share,
                contentDescription = "Partager",
                tint = Color.Black
            )
        }

        // Bouton like + compteur
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp)
                .background(Color.White, RoundedCornerShape(50))
                .padding(horizontal = 10.dp, vertical = 6.dp)
                .semantics {
                    contentDescription = "Ce produit a une note de ${product.likes}"
                }
        ) {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Likes",
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = product.likes.toString(),
                fontSize = 14.sp
            )
        }
    }
}