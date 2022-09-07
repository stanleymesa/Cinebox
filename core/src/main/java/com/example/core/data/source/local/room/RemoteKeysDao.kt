package com.example.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.data.source.local.entity.NowPlayingRemoteKeys
import com.example.core.data.source.local.entity.TopRatedRemoteKeys
import com.example.core.data.source.local.entity.UpcomingRemoteKeys

@Dao
interface RemoteKeysDao {

    // Now Playing

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllNowPlayingRemoteKeys(remoteKey: List<NowPlayingRemoteKeys>)

    @Query("SELECT * FROM now_playing_remote_keys WHERE id = :id")
    suspend fun getNowPlayingRemoteKeysId(id: String): NowPlayingRemoteKeys?

    @Query("DELETE FROM now_playing_remote_keys")
    suspend fun deleteNowPlayingRemoteKeys()

    // Upcoming

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUpcomingRemoteKeys(remoteKey: List<UpcomingRemoteKeys>)

    @Query("SELECT * FROM upcoming_remote_keys WHERE id = :id")
    suspend fun getUpcomingRemoteKeysId(id: String): UpcomingRemoteKeys?

    @Query("DELETE FROM upcoming_remote_keys")
    suspend fun deleteUpcomingRemoteKeys()

    // Top Rated

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTopRatedRemoteKeys(remoteKey: List<TopRatedRemoteKeys>)

    @Query("SELECT * FROM top_rated_remote_keys WHERE id = :id")
    suspend fun getTopRatedRemoteKeysId(id: String): TopRatedRemoteKeys?

    @Query("DELETE FROM top_rated_remote_keys")
    suspend fun deleteTopRatedRemoteKeys()

}