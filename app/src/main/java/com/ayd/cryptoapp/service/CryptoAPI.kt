package com.ayd.cryptoapp.service

import com.ayd.cryptoapp.model.CryptoList
import com.ayd.cryptoapp.model.CryptoSpecial
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoAPI {

    @GET("currencies/ticker")
    suspend fun getCryptoList(
        @Query("key") key: String
    ) : CryptoList

//spesifik özellikler endpoint için buradan alınıyor.
    @GET("currencies/ticker")
    suspend fun getCrypto(
        @Query("key") key: String,
        @Query("ids") id: String,
        @Query("attributes") attributes: String
    ): CryptoSpecial


}