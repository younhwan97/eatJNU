package kr.co.younhwan.eatjnu.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kr.co.younhwan.eatjnu.common.Constants
import kr.co.younhwan.eatjnu.data.preferences.Preferences
import kr.co.younhwan.eatjnu.data.preferences.PreferencesImpl
import kr.co.younhwan.eatjnu.data.remote.EatJnuApi
import kr.co.younhwan.eatjnu.data.repository.EatJnuRepositoryImpl
import kr.co.younhwan.eatjnu.data.repository.PrefRepositoryImpl
import kr.co.younhwan.eatjnu.domain.repository.EatJnuRepository
import kr.co.younhwan.eatjnu.domain.repository.PrefRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideEatJnuApi(): EatJnuApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EatJnuApi::class.java)
    }

    @Provides
    @Singleton
    fun provideEatJnuRepository(api: EatJnuApi): EatJnuRepository {
        return EatJnuRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providePreferences(@ApplicationContext app: Context): Preferences {
        return PreferencesImpl(app)
    }
}