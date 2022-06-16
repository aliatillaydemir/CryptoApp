package com.ayd.cryptoapp.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ayd.cryptoapp.ui.theme.CryptoAppTheme
import com.ayd.cryptoapp.view.CryptoDetailScreen
import com.ayd.cryptoapp.view.CryptoListScreen
import com.ayd.cryptoapp.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint //hilt için. buradan başlayacağını bilmesi lazım
class MainActivity : ComponentActivity() {

    private val splashviewModel : SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreen = installSplashScreen()

        splashScreen.apply {
            splashScreen.setKeepOnScreenCondition{
                splashviewModel.isloading.value
            }
        }

        setContent {
            CryptoAppTheme {
                //burası splash screen için.
                Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,) {

                }
                
                
                
                val navController =
                    rememberNavController() //değişkenlerde değişiklik olursa baştan oluşturur

                NavHost(navController = navController, startDestination = "cryptoListScreen") {

                    composable("cryptoListScreen") {
                        //crytplistscreen ana sayfamız olacak
                        CryptoListScreen(navController = navController)

                    }

                    //cryptoDetail sayfasına buradan iki veri gönderecegiz. criptonun id ve ucreti.
                    composable("cryptoDetailScreen/{cryptoId}/{cryptoPrices}", arguments = listOf(
                        navArgument("cryptoId") {
                            type = NavType.StringType
                        },
                        navArgument("cryptoPrices") {
                            type = NavType.StringType
                        }


                    )) {
                        val cryptoId = remember {  //cryptoDetailScreen composable'ına gelindiginde cryptoListScreen'deki argumanlar degisken olarak verilir.
                            it.arguments?.getString("cryptoId")
                        }

                        val cryptoPrices = remember {
                            it.arguments?.getString("cryptoPrices")
                        }

                        //CryptoDetailScreen
                        CryptoDetailScreen(id = cryptoId ?: "", price = cryptoPrices?: "", navController = navController)

                    }


                }
            }
        }
    }
}

