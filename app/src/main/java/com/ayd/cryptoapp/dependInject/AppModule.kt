package com.ayd.cryptoapp.dependInject

import com.ayd.cryptoapp.repo.CryptoRepo
import com.ayd.cryptoapp.service.CryptoAPI
import com.ayd.cryptoapp.util.ConstantObjects.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


//bu sınıf(obje) retrofit vs. inject edilmek için provide edilen sınıf.

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCryptoRepo(
        api: CryptoAPI
    )=CryptoRepo(api)


    @Singleton
    @Provides
    fun provideCryptoApi(): CryptoAPI{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(CryptoAPI::class.java)
    }


}