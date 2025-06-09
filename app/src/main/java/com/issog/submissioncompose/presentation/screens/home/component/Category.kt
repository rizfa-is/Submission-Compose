package com.issog.submissioncompose.presentation.screens.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.issog.submissioncompose.core.ui.theme.SubmissionComposeTheme
import com.issog.submissioncompose.utils.DataGenerator

@Composable
fun Category(
    modifier: Modifier = Modifier,
    navigateToCategory: (category: String) -> Unit
) {
    val dummyCategory = DataGenerator.generateCategoryData()

    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Browse by Category",
            fontSize = MaterialTheme.typography.titleSmall.fontSize,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = modifier.height(16.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(dummyCategory) { category ->
                CategoryItem(
                    category,
                    modifier = modifier
                        .clickable {
                            navigateToCategory(category.category)
                        }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryPreview() {
    SubmissionComposeTheme {
        Category(navigateToCategory = {})
    }
}