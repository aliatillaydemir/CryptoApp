package com.ayd.cryptoapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayd.cryptoapp.model.CryptoListItem
import com.ayd.cryptoapp.repo.CryptoRepo
import com.ayd.cryptoapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//viewmodelda livedata ya da flowlar yazılır. burada compose'larda mutable state olduğu için livedataya gerek yok.
@HiltViewModel
class CryptoListViewModel @Inject constructor(
    private val repo : CryptoRepo
):ViewModel() {


    var cryptoList = mutableStateOf<List<CryptoListItem>>(listOf())
    var errorMes = mutableStateOf("")
    var isload = mutableStateOf(false)

    private var initialCryptoList = listOf<CryptoListItem>()
    private var isSearchStart = true

    init {
        loadCrypto()
    }

    fun searchCrypto(query:String){
        val listToSearch = if(isSearchStart){
            cryptoList.value
        }else{
            initialCryptoList
        }

        viewModelScope.launch(Dispatchers.Default) {
            if(query.isEmpty()){
                cryptoList.value = initialCryptoList
                isSearchStart = true
                return@launch
            }
            val results = listToSearch.filter {
                it.currency.contains(query.trim(),ignoreCase = true)
            }

            if(isSearchStart){
                initialCryptoList = cryptoList.value
                isSearchStart = false
            }

            cryptoList.value = results

        }

    }

     fun loadCrypto(){
        viewModelScope.launch {
            isload.value = true
            val result = repo.getCryptoList()

            when(result){

                is Resource.Success ->{
                val cryptoItem = result.data!!.mapIndexed{index, cryptoListItem ->
                    CryptoListItem(cryptoListItem.currency,cryptoListItem.price)
                } as List<CryptoListItem>  //eklenebilir de ...

                 isload.value = false
                 errorMes.value = ""
                 cryptoList.value += cryptoItem

                }

                is Resource.Error ->{
                    errorMes.value = result.message!!
                    isload.value = false
                }

            }

        }


    }

}