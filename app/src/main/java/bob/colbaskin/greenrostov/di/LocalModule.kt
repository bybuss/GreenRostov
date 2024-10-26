package bob.colbaskin.greenrostov.di

import android.content.Context
import bob.colbaskin.greenrostov.data.local.dataStore.DataStoreRepositoryImpl
import bob.colbaskin.greenrostov.domain.local.DataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author bybuss
 */
@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDataStoreRepository(@ApplicationContext context: Context): DataStoreRepository {
        return DataStoreRepositoryImpl(context = context)
    }
}