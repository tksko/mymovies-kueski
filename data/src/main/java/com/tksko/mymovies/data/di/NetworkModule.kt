package com.tksko.mymovies.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tksko.mymovies.data.BuildConfig
import com.tksko.mymovies.data.api.MoviesApiServices
import com.tksko.mymovies.domain.config.TMDB_PARAM_API_KEY
import com.tksko.mymovies.domain.config.TMDB_PARAM_LANGUAGE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val DATE_FORMAT = "yyyy-MM-dd"

    @Provides
    fun provideGson(): Gson = GsonBuilder().setDateFormat(DATE_FORMAT).create()

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val requestInterceptor = Interceptor { chain ->
            val url = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter(TMDB_PARAM_API_KEY, BuildConfig.API_KEY)
                .addQueryParameter(
                    TMDB_PARAM_LANGUAGE,
                    Locale.getDefault().toString().replace("_", "-")
                )
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()
            return@Interceptor chain.proceed(request)
        }

        return OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideMoviesApiServices(retrofit: Retrofit): MoviesApiServices =
        retrofit.create(MoviesApiServices::class.java)
}