package com.issog.submissioncompose.presentation.screens.profile

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.issog.submissioncompose.core.ui.theme.SubmissionComposeTheme
import com.issog.submissioncompose.core.R as RC

data class Menu(
    val title: String,
    val icon: ImageVector
)

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val menuItems = listOf(
        Menu(
            title = "rosyidrosadi15@gmail.com",
            icon = Icons.Default.Email
        ),
        Menu(
            title = "Jakarta, Indonesia",
            icon = Icons.Default.LocationOn
        ),
        Menu(
            title = "Android, Kotlin, Jetpack Compose",
            icon = Icons.Default.Star
        ),
        Menu(
            title = "Flutter, MQL4 & MQL5",
            icon = Icons.Filled.Favorite
        )
    )

    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // Profile Header
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(290.dp)
                        .padding(top = 66.dp)
                ) {
                    Image(
                        painter = ColorPainter(MaterialTheme.colorScheme.primary),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(7)),
                        contentScale = ContentScale.Crop
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = RC.drawable.img_profile),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(120.dp)
                            .border(10.dp, Color.White, CircleShape)
                            .padding(1.dp)
                            .clip(CircleShape)
                            .background(Color.DarkGray),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Rosyid Rosadi",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Experienced Android Developer with 4 years of expertise in creating robust and\n" +
                                "user-centric mobile applications. Proficient in Kotlin, Jetpack Compose, and Kotlin\n" +
                                "Multiplatform, with hands-on experience in Flutter and React Native. Successfully led a team\n" +
                                "of four to develop a backend and Android application for a coffee shop, showcasing strong\n" +
                                "project management and leadership skills. Additionally, served as a mentor in an Android\n" +
                                "development short course, guiding participants through core concepts and final projects.\n" +
                                "Passionate about building fast, secure, and maintainable apps to deliver seamless user\n" +
                                "experiences.",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.White,
                        maxLines = 5,
                        fontWeight = FontWeight.Normal,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            IconButton(
                onClick = {
                    Intent(Intent.ACTION_VIEW, Uri.parse("http://rosyadi.tech")).also { intent ->
                        context.startActivity(intent)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(30))
                    .background(Color.White)

            ) {
                Text(
                    text = "See My Portfolio",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Menu Items
            menuItems.forEach { item ->
                MenuItem(
                    title = item.title,
                    icon = item.icon
                )
            }
        }
    }
}

@Composable
private fun MenuItem(
    title: String,
    icon: ImageVector
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    SubmissionComposeTheme {
        ProfileScreen()
    }
}
