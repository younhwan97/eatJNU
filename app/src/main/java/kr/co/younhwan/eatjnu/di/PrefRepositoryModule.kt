package kr.co.younhwan.eatjnu.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kr.co.younhwan.eatjnu.data.repository.PrefRepositoryImpl
import kr.co.younhwan.eatjnu.domain.repository.PrefRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class PrefRepositoryModule {

    @Binds
    abstract fun providePrefRepository(impl: PrefRepositoryImpl): PrefRepository
}