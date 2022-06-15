package com.ayd.cryptoapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.ayd.cryptoapp.model.CryptoSpecial
import com.ayd.cryptoapp.util.Resource
import com.ayd.cryptoapp.viewmodel.CryptoDetailViewModel

@Composable
fun CryptoDetailScreen(id: String, price: String, navController: NavController,
viewModel: CryptoDetailViewModel = hiltViewModel()) {


/*
    var cryptoItem by remember {
        mutableStateOf<Resource<CryptoSpecial>>(Resource.Loading())
    }

    
    LaunchedEffect(key1 = Unit){
        cryptoItem = viewModel.getCrypto(id)
        println(cryptoItem.data)
    }
    */

  //üstteki de çalışır(aynısı) ama alttaki 2 satırda daha basit ifade edebiliyoruz.

    val cryptoItem = produceState<Resource<CryptoSpecial>>(initialValue = Resource.Loading()){
        value = viewModel.getCrypto(id)
    }.value //string türünde kalsın diye değer(value) veriyoruz




    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.secondary),
    contentAlignment = Alignment.Center){

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            when(cryptoItem){

                is Resource.Success ->{

                    val selectCrypto = cryptoItem.data!![0]

                    Text(text = selectCrypto.name,
                   style = MaterialTheme.typography.h3,
                   modifier = Modifier.padding(3.dp),
                   fontWeight = FontWeight.Bold,
                       color = MaterialTheme.colors.primary,
                       textAlign = TextAlign.Center
                   )


                    Image(painter = rememberImagePainter(data = selectCrypto.logoUrl),
                    contentDescription = selectCrypto.name,
                    modifier = Modifier
                        .padding(14.dp)
                        .size(200.dp, 200.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Gray, CircleShape)
                        )


                    Text(text = price + "$",
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.padding(3.dp),
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.primaryVariant,
                        textAlign = TextAlign.Center
                    )

                }

                is Resource.Loading ->{
                    CircularProgressIndicator(color = MaterialTheme.colors.primary)
                }

                is Resource.Error ->{
                    Text(text = cryptoItem.message!!)
                }


            }

        }

    }


}