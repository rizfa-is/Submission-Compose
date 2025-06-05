package com.issog.submissioncompose.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.issog.submissioncompose.core.ui.theme.SubmissionComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeritainTopBar(
    modifier: Modifier = Modifier,
    navigateToFavorite: () -> Unit
) {
   TopAppBar(
       title = {
           Row(
               horizontalArrangement = Arrangement.Absolute.Left,
               modifier = Modifier.fillMaxWidth()
           ) {
               Text(
                   text = "TOP NEWS",
                   color = MaterialTheme.colorScheme.primary,
                   fontSize = MaterialTheme.typography.titleLarge.fontSize,
                   fontWeight = FontWeight.Bold
               )
               Spacer(modifier = Modifier.width(6.dp))
               Text(
                   text = "WORLDWIDE",
                   color = Color.Black,
                   fontSize = MaterialTheme.typography.titleLarge.fontSize
               )
           }
       },
       actions = {
           IconButton(
               onClick = { navigateToFavorite() },
           ) {
               Surface(
                   shape = RoundedCornerShape(50),
                   color = MaterialTheme.colorScheme.primary,
                   modifier = Modifier
                       .width(24.dp)
                       .height(24.dp)
               ) {
                   Icon(
                       imageVector = Icons.Default.Favorite,
                       contentDescription = "Favorite",
                       modifier = Modifier
                           .padding(4.dp)
                   )
               }
           }
       },
       modifier = modifier
   )
}

@Preview(showBackground = true)
@Composable
fun BeritainTopBarPreview() {
    SubmissionComposeTheme {
        BeritainTopBar(navigateToFavorite = {})
    }
}