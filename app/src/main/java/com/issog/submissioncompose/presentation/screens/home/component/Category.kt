package com.issog.submissioncompose.presentation.screens.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
    modifier: Modifier = Modifier
) {
    val dummyCategory = DataGenerator.generateCategoryData()

    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Text(
            text = "Browse by Category",
            fontSize = MaterialTheme.typography.titleSmall.fontSize,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = modifier.height(16.dp))
        LazyRow {
            items(dummyCategory.size) { category ->
                CategoryItem(
                    dummyCategory[category],
                    modifier = modifier
                        .width(100.dp)
                        .fillMaxHeight()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryPreview() {
    SubmissionComposeTheme {
        Category()
    }
}