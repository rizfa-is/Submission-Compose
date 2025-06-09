package com.issog.submissioncompose.presentation.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.issog.submissioncompose.core.domain.model.ArticleModel
import com.issog.submissioncompose.core.ui.theme.SubmissionComposeTheme

@Composable
fun DetailNewsScreen(
    modifier: Modifier = Modifier,
    article: ArticleModel,
    navigateBack: () -> Unit,
    navigateToWebView: () -> Unit,
) {
    Scaffold(
        bottomBar = {
            Button(
                onClick = {
                    navigateToWebView()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Read Full Article")
            }
        },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding())
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            // Image with favorite button overlay
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            ) {
                // Clickable image
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    AsyncImage(
                        model = article.urlToImage,
                        contentDescription = article.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }

                // Back button
                Box(
                    modifier = Modifier
                        .padding(top = 16.dp, start = 16.dp)
                        .align(Alignment.TopStart)
                        .size(34.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(20) // 20% of the size
                        )
                ) {
                    // Back button
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Red,
                        modifier = Modifier
                            .padding(2.dp)
                            .fillMaxSize()
                            .clickable {
                                navigateBack()
                            }
                    )
                }

                // Back button
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(14.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(topStartPercent = 50, topEndPercent = 50) // 20% of the size
                        )
                )
            }

            Text(
                text = article.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = article.author,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = article.content,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
        }
    }
}

@Preview
@Composable
fun DetailNewsScreenPreview() {
    SubmissionComposeTheme {
        DetailNewsScreen(
            article = ArticleModel(
                author = "author",
                content = "content",
                urlToImage = "description",
                title = "title",
                url = "url",
                favorite = false
            ),
            navigateBack = {},
            navigateToWebView = {}
        )
    }
}