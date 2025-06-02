package com.issog.submissioncompose.presentation.screens.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.issog.submissioncompose.core.R
import com.issog.submissioncompose.core.domain.usecase.BeritainCategory
import com.issog.submissioncompose.core.ui.theme.SubmissionComposeTheme
import com.issog.submissioncompose.presentation.screens.home.model.ItemCategory

@Composable
fun CategoryItem(
    category: ItemCategory,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(50))
                .background(color = MaterialTheme.colorScheme.primary)
        ) {
            Image(
                painter = painterResource(category.image),
                contentDescription = category.category,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            )
        }
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = category.category,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = MaterialTheme.typography.labelMedium.fontSize,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryItemPreview() {
    SubmissionComposeTheme {
        CategoryItem(
            ItemCategory(
                BeritainCategory.ENTERTAINMENT.value.uppercase(),
                R.drawable.ic_entertainment
            )
        )
    }
}