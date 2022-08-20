package com.example.cinebox.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cinebox.core.data.source.local.entity.NowPlayingRemoteKeys

@Dao
interface NowPlayingRemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<NowPlayingRemoteKeys>)

    @Query("SELECT * FROM now_playing_remote_keys WHERE id = :id")
    suspend fun getRemoteKeysId(id: Int): NowPlayingRemoteKeys?

    @Query("DELETE FROM remote_keys")
    suspend fun deleteRemoteKeys()

}