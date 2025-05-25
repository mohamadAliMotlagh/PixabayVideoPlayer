package com.motlagh.core.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.AutoMigrationSpec
import androidx.sqlite.db.SupportSQLiteDatabase
import com.motlagh.core.database.dao.VideoSearchDao
import com.motlagh.core.database.model.ListConverters
import com.motlagh.core.database.model.SearchQueryEntity
import com.motlagh.core.database.model.SearchVideoCrossRef
import com.motlagh.core.database.model.VideoEntity

@Database(
    entities = [
        SearchQueryEntity::class,
        SearchVideoCrossRef::class,
        VideoEntity::class
    ],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2, spec = Migration1To2Spec::class),
    ],
    exportSchema = true,
)
@TypeConverters(
    ListConverters::class,
)
internal abstract class AppDataBase : RoomDatabase() {
    abstract fun videos(): VideoSearchDao
}

