package com.example.numaboaterapia.versionTwo.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.numaboaterapia.R
import com.example.numaboaterapia.ds.CustomWhiteButton
import com.example.numaboaterapia.ui.theme.NumaBoaTheme
import com.example.numaboaterapia.versionTwo.main.viewModel.MainViewModel
import com.example.numaboaterapia.views.CustomView.BotaoArredondadoBranco

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val composeView = ComposeView(this)
        composeView.setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        composeView.setContent {
            NumaBoaTheme {
                // Observe o estado do ViewModel
                val state by mainViewModel.viewState.observeAsState(MainStates.Disabled(false))

                // Defina um evento de ação quando o botão é clicado
                val onAction: (MainActions.Action) -> Unit = { action ->
                    mainViewModel.sendAction(action)
                }

                MainScreen(state = state, onAction = onAction)
            }
        }
    }

    @Composable
    fun MainScreen(
        state: MainStates,
        onAction: (MainActions.Action) -> Unit
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.mipmap.ic_logo_background), // Substitua pelo ID da sua imagem
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize()
                    .padding(16.dp) // Ajuste o espaçamento conforme necessário
                    .background(Color.Transparent),
                verticalArrangement = Arrangement.Bottom, // Alinhar os elementos na parte inferior
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomWhiteButton(
                    text = "Cadastro",
                    onClick = { onAction(MainActions.Action.ClickButton) }

                )
                Spacer(modifier = Modifier.height(16.dp))
                CustomWhiteButton(
                    text = "Entrar",
                    onClick = { onAction(MainActions.Action.ClickButton) }
                )
            }

        }
    }

    @Composable
    @Preview
    fun MainScreenPreview() {
        MainScreen(
            state = MainStates.Disabled(false),
            onAction = { action -> /* Define ação aqui */ }
        )
    }
}
