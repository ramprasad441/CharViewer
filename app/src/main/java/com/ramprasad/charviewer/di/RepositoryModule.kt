package com.ramprasad.charviewer.di

import com.ramprasad.charviewer.repository.CharactersRepository
import com.ramprasad.charviewer.repository.CharactersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Ramprasad on 7/5/23.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepo(characterRepositoryImpl: CharactersRepositoryImpl): CharactersRepository

}