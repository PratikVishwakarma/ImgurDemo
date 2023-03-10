package com.pratik.imgurdemo.model.networkAPI.useCase

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)

abstract class UseCaseModule {
    @Binds
    abstract fun apiUseCase(mainAPIUseCase: MainAPIUseCase): ApiUseCase
}