package com.motlagh.core.database

import androidx.room.RenameColumn
import androidx.room.migration.AutoMigrationSpec
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration1To2Spec : AutoMigrationSpec {
    override fun onPostMigrate(db: SupportSQLiteDatabase) {
        super.onPostMigrate(db)
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_SearchVideoCrossRef_videoId` ON `SearchVideoCrossRef` (`videoId`)")
    }
}