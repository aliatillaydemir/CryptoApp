package com.ayd.cryptoapp.viewmodel

import androidx.lifecycle.ViewModel
import com.ayd.cryptoapp.model.CryptoSpecial
import com.ayd.cryptoapp.repo.CryptoRepo
import com.ayd.cryptoapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CryptoDetailViewModel @Inject constructor(
    private val repo: CryptoRepo
): ViewModel(){

    suspend fun getCrypto(id : String): Resource<CryptoSpecial>{
        return repo.getCrypto(id)
    }



}