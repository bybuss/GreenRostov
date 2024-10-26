package bob.colbaskin.greenrostov.di

import android.util.Log
import bob.colbaskin.greenrostov.data.network.AuthRepositoryImpl
import bob.colbaskin.greenrostov.data.network.RestApiRepositoryImpl
import bob.colbaskin.greenrostov.domain.local.DataStoreRepository
import bob.colbaskin.greenrostov.domain.network.AuthRepository
import bob.colbaskin.greenrostov.domain.network.restApi.RestApiRepository
import bob.colbaskin.greenrostov.domain.network.restApi.RestApiService
import com.google.firebase.auth.FirebaseAuth
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

/**
 * @author bybuss
 */
@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth)
    }

    @Provides
    @Singleton
    @Named("restApiUrl")
    fun provideRestApiUrl(): String {
        return "http://90.156.170.155/api/"
    }

    @Provides
    @Singleton
    @Named("token")
    fun provideBaseUrl(dataStoreRepository: DataStoreRepository): String {
        return runBlocking { dataStoreRepository.getToken().first() }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @Named("token") token: String
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                Log.d("AuthViewModel", "token used in provideOkHttpClient: $token")
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", token)
                    .build()
                chain.proceed(request)
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        @Named("restApiUrl") restApiUrl: String,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(restApiUrl)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideRestApiService(retrofit: Retrofit): RestApiService {
        return retrofit.create(RestApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRestApiRepository(restApiService: RestApiService): RestApiRepository {
        return RestApiRepositoryImpl(restApiService)
    }
}