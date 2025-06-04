package com.issog.submissioncompose.presentation.screens.news.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.issog.submissioncompose.core.ui.theme.SubmissionComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = {},
        active = false,
        onActiveChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        placeholder = {
            Text(text = "Search hot news")
        },
        shape = MaterialTheme.shapes.large,
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .heightIn(min = 48.dp)
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun SearchPreview() {
    SubmissionComposeTheme {
        Search(query = "", onQueryChange = {})
    }
}