package com.issog.submissioncompose.utils

import com.issog.submissioncompose.core.domain.usecase.BeritainCategory
import com.issog.submissioncompose.presentation.screens.home.model.ItemCategory
import com.issog.submissioncompose.core.R as RCore

object DataGenerator {
    fun generateCategoryData(): List<ItemCategory> =
        listOf(
            ItemCategory(BeritainCategory.BUSINESS.value.uppercase(), RCore.drawable.ic_business),
            ItemCategory(BeritainCategory.ENTERTAINMENT.value.uppercase(), RCore.drawable.ic_entertainment),
            ItemCategory(BeritainCategory.GENERAL.value.uppercase(), RCore.drawable.ic_general),
            ItemCategory(BeritainCategory.HEALTH.value.uppercase(), RCore.drawable.ic_health),
            ItemCategory(BeritainCategory.SCIENCE.value.uppercase(), RCore.drawable.ic_science),
            ItemCategory(BeritainCategory.SPORTS.value.uppercase(), RCore.drawable.ic_sport),
            ItemCategory(BeritainCategory.TECHNOLOGY.value.uppercase(), RCore.drawable.ic_technology)
        )
}