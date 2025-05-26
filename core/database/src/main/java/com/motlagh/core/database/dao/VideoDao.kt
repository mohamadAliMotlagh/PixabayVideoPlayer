package com.motlagh.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.motlagh.core.database.model.SearchQueryEntity
import com.motlagh.core.database.model.SearchVideoCrossRef
import com.motlagh.core.database.model.SearchWithVideos
import com.motlagh.core.database.model.VideoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VideoSearchDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertVideos(videos: List<VideoEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSearchQuery(query: SearchQueryEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSearchVideoCrossRefs(crossRefs: List<SearchVideoCrossRef>)

    @Transaction
    @Query("SELECT * FROM SearchQueryEntity WHERE `query` = :searchQuery")
    fun getSearchWithVideosFlow(searchQuery: String): Flow<SearchWithVideos?>

    @Query("SELECT * FROM SearchQueryEntity WHERE `query` = :searchQuery")
    suspend fun getSearchQueryByText(searchQuery: String): SearchQueryEntity?

    @Query("UPDATE VideoEntity SET isBookmarked = 1 WHERE id = :videoId")
    suspend fun bookmarkVideo(videoId: String)

    @Query("UPDATE VideoEntity SET isBookmarked = 0 WHERE id = :videoId")
    suspend fun removeBookmarkVideo(videoId: String)

    @Query("SELECT * FROM VideoEntity WHERE isBookmarked = 1")
    fun getAllBookmarkedVideos(): Flow<List<VideoEntity>>

    @Query("SELECT * FROM VideoEntity WHERE id = :videoId")
    suspend fun getVideoById(videoId: String): VideoEntity?



}
