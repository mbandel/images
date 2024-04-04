package com.mbandel.images.di

import android.content.Context
import androidx.room.Room
import com.mbandel.images.apiservice.ApiConstants
import com.mbandel.images.apiservice.ApiService
import com.mbandel.images.database.ImagesDatabase
import com.mbandel.images.database.DatabaseConstants.DATABASE_NAME
import com.mbandel.images.database.dao.ImagesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvidesModule {
    @Provides
    @Singleton
    fun provideServiceApi(): ApiService {
        val retrofit = Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiConstants.BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .build()
            )
            .build()
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ImagesDatabase {
        return Room.databaseBuilder(
            context,
            ImagesDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideImagesDao(database: ImagesDatabase): ImagesDao {
        return database.getImagesDao()
    }
}