package br.com.fiap.nagandoentretelas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.fiap.nagandoentretelas.screens.LoginScreen
import br.com.fiap.nagandoentretelas.screens.MenuScreen
import br.com.fiap.nagandoentretelas.screens.PedidosScreen
import br.com.fiap.nagandoentretelas.screens.PerfilScreen
import br.com.fiap.nagandoentretelas.ui.theme.NagandoEntreTelasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NagandoEntreTelasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                   val navController = rememberNavController()
                   NavHost(navController = navController, startDestination = "login" ){
                        composable(route = "login") {
                            LoginScreen(navController)
                        }
                        composable(route = "menu") {
                            MenuScreen(navController)
                        }
                        composable(route = "perfil/{nome}") {
                            val nome = it.arguments?.getString("nome")
                            PerfilScreen(navController, nome!!) // double bang - trata o nulo
                        }
                        composable(
                            route = "pedidos?numero={numero}",
                            arguments = listOf(navArgument(name = "numero"){
                                defaultValue = "sem valor"})
                        ) {
                            PedidosScreen(it.arguments?.getString("numero")!!)
                        }
                    }
                }
            }
        }
    }
}
