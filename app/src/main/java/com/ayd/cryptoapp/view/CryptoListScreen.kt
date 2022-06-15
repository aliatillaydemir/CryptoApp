package com.ayd.cryptoapp.view


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ayd.cryptoapp.model.CryptoListItem
import com.ayd.cryptoapp.viewmodel.CryptoListViewModel

@Composable
fun CryptoListScreen(
    navController: NavController,
    viewModel: CryptoListViewModel = hiltViewModel()) {

  Surface(
      color = MaterialTheme.colors.secondary,
      modifier = Modifier.fillMaxSize()
  ) {
      Column {

          Text("Crypto App", modifier = Modifier
              .fillMaxWidth()
              .padding(20.dp),
              textAlign = TextAlign.Center,
              fontSize = 45.sp,
              fontWeight = FontWeight.Bold,
              color = MaterialTheme.colors.primary
          )
          Spacer(modifier = Modifier.height(10.dp))
          //search için
          SearchBar(hint = "Search", modifier = Modifier
              .fillMaxWidth()
              .padding(16.dp)
          ){
              viewModel.searchCrypto(it)
          }

          Spacer(modifier = Modifier.height(10.dp))
          //List
          CryptoList(navController = navController)


      }

  }

}

@Composable
fun SearchBar(
    modifier: Modifier= Modifier,
    hint : String = "",
    onSearch : (String) -> Unit = {}
){
    var text by remember { mutableStateOf("")} //by dersek direkt olarak değer alınabilir. burada string mesela aşağıda .value dememize gerek kalmadı

    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier){

        BasicTextField(value = text, onValueChange = {
            text = it
            onSearch(it)
        }, maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 13.dp)
                .onFocusChanged {
                    //kullanıcı tıklamayı bıraktıktan sonra ne olsunu gösterir?
                    isHintDisplayed =
                        it.isFocused != true && text.isEmpty() // kullanıcı searchbarta tılmayırosa ve bir şey yazmıyorsa gösterilecek, aksi takdirde gösterilmeyecek
                }

        )


        if(isHintDisplayed){

            Text(text = hint,
                color = Color.LightGray,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 13.dp)
            )

        }


    }
    


}


@Composable
fun CryptoList(navController: NavController,
viewModel : CryptoListViewModel = hiltViewModel()
){

    val cryptoList by remember { viewModel.cryptoList }
    val errorMessage by remember {viewModel.errorMes}
    val isLoad by remember { viewModel.isload}

    CryptoListView(cryptos = cryptoList, navController = navController)
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()){
        if(isLoad){
            CircularProgressIndicator(color = MaterialTheme.colors.primary)    
        }
        if(errorMessage.isNotEmpty()){
            
            Retry(error = errorMessage) {
                viewModel.loadCrypto() //internet bir anlığına çalışmazsa tüm kriptoları indirir.
                
            }
            
        }
        
    }


}






@Composable
fun CryptoListView(cryptos: List<CryptoListItem>,navController: NavController){

    //lazycolumn recyclerview vazifesi görüyoru.
    LazyColumn(contentPadding = PaddingValues(5.dp)){
        items(cryptos){ crypto ->

            CryptoRow(navController = navController, crypto = crypto)

        }
    }

}




@Composable
fun CryptoRow(navController: NavController, crypto: CryptoListItem){

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.secondary)
            .clickable {
                navController.navigate("cryptoDetailScreen/${crypto.currency}/${crypto.price}")
            }
            ){

        Text(text = crypto.currency,
        style = MaterialTheme.typography.h5,
        modifier = Modifier.padding(2.dp),
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colors.primary)

        Text(text = crypto.price,
        style = MaterialTheme.typography.h5,
        modifier = Modifier.padding(2.dp),
        color = MaterialTheme.colors.primaryVariant)

        Divider(color = Color.White, thickness = 2.dp)

    }


}

@Composable
fun Retry(
    error: String,
    onRetry : () -> Unit
){
    Column() {
        Text(error, color = Color.Red, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick =  {
            onRetry
        }, modifier = Modifier.align(Alignment.CenterHorizontally)){
            Text(text = "Retry")
        }
    }
    
}









