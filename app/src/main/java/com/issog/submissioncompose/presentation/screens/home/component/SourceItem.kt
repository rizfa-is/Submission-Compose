package com.issog.submissioncompose.presentation.screens.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.issog.submissioncompose.core.domain.model.SourceModel
import com.issog.submissioncompose.core.domain.usecase.BeritainCategory
import com.issog.submissioncompose.core.ui.theme.SubmissionComposeTheme

@Composable
fun SourceItem(
    source: SourceModel,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .requiredSize(100.dp)
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(15))
            .background(color = MaterialTheme.colorScheme.primary)
    ) {
        Text(
            text = source.name,
            maxLines = 3,
            lineHeight = TextUnit(18f, TextUnitType.Sp),
            overflow = TextOverflow.Ellipsis,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SourceItemPreview() {
    SubmissionComposeTheme {
        SourceItem(
            SourceModel(
                "BBC News BBC News",
                BeritainCategory.ENTERTAINMENT.value.uppercase(),
                "R.drawable.ic_entertainment",
                "https://www.bbc.com/news/entertainment_and_arts"
            )
        )
    }
}