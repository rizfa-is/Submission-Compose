package com.issog.submissioncompose.presentation.screens.news.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.issog.submissioncompose.core.domain.model.ArticleModel
import com.issog.submissioncompose.core.ui.theme.SubmissionComposeTheme

@Composable
fun NewsItem(
    item: ArticleModel,
    modifier: Modifier = Modifier,
    onFavoriteClick: (ArticleModel) -> Unit,
    navigateToDetail: (ArticleModel) -> Unit
) {
    var isFavorite by remember { mutableStateOf(item.favorite) }

    Box(
        modifier = modifier
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image with favorite button overlay
            Box(
                modifier = Modifier
                    .size(120.dp)
            ) {
                // Clickable image
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    AsyncImage(
                        model = item.urlToImage,
                        contentDescription = item.title,
                        contentScale = ContentScale.Crop,
                        placeholder = painterResource(id = com.issog.submissioncompose.R.drawable.ic_launcher_background),
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(20))
                    )
                }

                // Favorite button
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(28.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(20) // 20% of the size
                        )
                ) {

                    // Favorite button
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
                        tint = Color.Red,
                        modifier = Modifier
                            .padding(2.dp)
                            .fillMaxSize()
                            .clickable {
                                isFavorite = !isFavorite
                                onFavoriteClick(item.copy(favorite = isFavorite))
                            }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                        .clickable { navigateToDetail(item) }
            ) {
                Text(
                    text = item.title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = item.content,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.labelMedium.fontSize,
                        fontWeight = FontWeight.Normal
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = item.author,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Gray,
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.labelMedium.fontSize,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Italic
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewsItemPreview() {
    SubmissionComposeTheme {
        NewsItem(item = ArticleModel(
            urlToImage = "https://live-production.wcms.abc-cdn.net.au/8eaaf4b712c3a129e6a8abb06169bfba?impolicy=wcms_watermark_news&cropH=906&cropW=1611&xPos=0&yPos=41&width=862&height=485&imformat=generic",
            title = "Entertainment and Arts",
            content = "Entertainment and Arts",
            author = "BBC News",
            url = "https://www.bbc.com/news/entertainment_and_arts",
            favorite = false
        ), onFavoriteClick = { _ -> }, navigateToDetail = { _ -> })
    }
}