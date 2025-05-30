package com.issog.submissioncompose.core.data.source.local.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.issog.submissioncompose.core.data.source.local.room.dao.ArticleDao
import com.issog.submissioncompose.core.data.source.local.room.entites.ArticleEntity

@Database(
    entities = [ArticleEntity::class],
    version = 1,
    exportSchema = false
)
abstract class BeritainDatabase: RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}