package com.issog.submissioncompose.presentation.screens.news.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = item.urlToImage,
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = com.issog.submissioncompose.R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(15))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                verticalArrangement = Arrangement.SpaceAround
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
        ))
    }
}