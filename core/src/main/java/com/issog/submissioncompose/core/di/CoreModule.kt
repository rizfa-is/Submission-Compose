package com.issog.submissioncompose.core.di

import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.issog.submissioncompose.core.data.BeritainRepository
import com.issog.submissioncompose.core.data.source.local.ILocalDataSource
import com.issog.submissioncompose.core.data.source.local.LocalDataSource
import com.issog.submissioncompose.core.data.source.local.room.db.BeritainDatabase
import com.issog.submissioncompose.core.data.source.remote.IRemoteDataSource
import com.issog.submissioncompose.core.data.source.remote.RemoteDataSource
import com.issog.submissioncompose.core.data.source.remote.network.ApiService
import com.issog.submissioncompose.core.domain.repository.IBeritainRepository
import com.issog.submissioncompose.core.utils.db.DatabaseEncryptionHelper
import com.issog.submissioncompose.core.utils.security.ComposeNativeLibs
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<BeritainDatabase>().articleDao() }
    single {
        val pass = ComposeNativeLibs.beritainPassphrase()
        val databaseName = ComposeNativeLibs.beritainDb()
        val passphrase = SQLiteDatabase.getBytes(pass.toCharArray())
        val factory = SupportFactory(passphrase)

        // Initialize SQLCipher
        SQLiteDatabase.loadLibs(get())

        // Migrate to encrypted database if needed
        DatabaseEncryptionHelper.migrateToEncrypted(get(), databaseName, pass)

        Room.databaseBuilder(
            androidContext(),
            BeritainDatabase::class.java,
            databaseName
        )
            .openHelperFactory(factory)
            .addMigrations()
            .fallbackToDestructiveMigration()
            .build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                requestBuilder.addHeader("Authorization", ComposeNativeLibs.beritainApiKey())
                chain.proceed(requestBuilder.build())
            }
            .addInterceptor(ChuckerInterceptor(get()))
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl(ComposeNativeLibs.beritainBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single<IRemoteDataSource>  { RemoteDataSource(get()) }
    single<ILocalDataSource>  { LocalDataSource(get()) }
    single<IBeritainRepository> { BeritainRepository(get(), get()) }
}