package com.issog.submissioncompose.presentation.screens.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.issog.submissioncompose.core.domain.model.SourceModel

@Composable
fun Source(
    listSource: List<SourceModel>,
    modifier: Modifier = Modifier,
    navigateToList: (String) -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = "Browse by Source",
            fontSize = MaterialTheme.typography.titleSmall.fontSize,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = modifier.height(16.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
        ) {
            items(listSource) { source ->
                SourceItem(
                    source = source,
                    modifier = modifier.clickable {
                        navigateToList(source.id)
                    }
                )
            }
        }
    }
}