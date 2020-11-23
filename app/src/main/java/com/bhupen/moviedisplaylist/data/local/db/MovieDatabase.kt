package com.bhupen.moviedisplaylist.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bhupen.moviedisplaylist.data.local.dao.MovieDao
import com.bhupen.moviedisplaylist.data.local.dao.RemoteKeysDao
import com.bhupen.moviedisplaylist.data.local.entity.MovieEntity
import com.bhupen.moviedisplaylist.data.local.entity.RemoteKeysEntity


@Database(
        entities = [MovieEntity::class, RemoteKeysEntity::class],
        version = 1,
        exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun remoteKeysDao(): RemoteKeysDao

}
