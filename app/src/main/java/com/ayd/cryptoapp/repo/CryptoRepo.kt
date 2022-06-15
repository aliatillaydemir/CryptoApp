package com.ayd.cryptoapp.repo

import com.ayd.cryptoapp.model.CryptoList
import com.ayd.cryptoapp.model.CryptoSpecial
import com.ayd.cryptoapp.service.CryptoAPI
import com.ayd.cryptoapp.util.ConstantObjects.API_KEY
import com.ayd.cryptoapp.util.ConstantObjects.CALL_ATTRIBUTES
import com.ayd.cryptoapp.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class CryptoRepo @Inject constructor(
    private val api: CryptoAPI
){

    suspend fun getCryptoList(): Resource<CryptoList>{
        val response = try {
            api.getCryptoList(API_KEY)
        }catch (e : Exception){
            return Resource.Error("Problem")
        }
        return Resource.Success(response)
    }

    suspend fun getCrypto(id:String) : Resource<CryptoSpecial>{
        val response = try {
            api.getCrypto(API_KEY,id, CALL_ATTRIBUTES)
        }catch (e:Exception){
            return Resource.Error("Problem")
        }
        return Resource.Success(response)
    }


}