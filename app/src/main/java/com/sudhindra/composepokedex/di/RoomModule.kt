package com.sudhindra.composepokedex.di

import android.app.Application
import androidx.room.Room
import com.sudhindra.composepokedex.room.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun providePokemonDatabase(application: Application) =
        Room.databaseBuilder(
            application,
            PokemonDatabase::class.java,
            "pokemon_database"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun providePokemonDao(db: PokemonDatabase) = db.pokemonDao()
}
