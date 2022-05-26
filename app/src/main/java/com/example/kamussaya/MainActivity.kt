package com.example.kamussaya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kamussaya.feature_kamus.presentation.WordItem
import com.example.kamussaya.feature_kamus.presentation.WordViewModel
import com.example.kamussaya.ui.theme.KamusTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KamusTheme {
                val viewModel: WordViewModel = hiltViewModel()
                val state = viewModel.state
                val scaffoldState = rememberScaffoldState()

                //? for listen event flow in view model
                LaunchedEffect(key1 = true) {
                    viewModel.evenFlow.collectLatest { event ->
                        when (event) {
                            is WordViewModel.UiEvent.showSnackbar -> {
                                scaffoldState.snackbarHostState.showSnackbar(message = event.message)
                            }
                        }

                    }
                }

                Scaffold(
                    scaffoldState = scaffoldState
                ) {
                    Box(modifier = Modifier.background(MaterialTheme.colors.background)) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .absolutePadding(16.dp, 16.dp, 16.dp)
                        ) {
                            TextField(
                                value = viewModel.searchQuery.value,
                                onValueChange = viewModel::onSearch,
                                modifier = Modifier.fillMaxWidth(),
                                placeholder = { Text("Search...") }
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            LazyColumn(modifier = Modifier.fillMaxSize()) {
                                items(state.value.wordInfoItems.size) { idx ->
                                    val wordInfo = state.value.wordInfoItems[idx]
                                    if (idx > 0) {
                                        Spacer(modifier = Modifier.height(8.dp))
                                    }
                                    WordItem(wordInfo = wordInfo)
                                    Spacer(modifier = Modifier.height(8.dp))

                                    if (idx < state.value.wordInfoItems.size - 1) {
                                        Divider()
                                    }
                                }
                            }
                        }
                        if (state.value.wordInfoItems.isEmpty() && !state.value.isLoading) {
                            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                                Text(text = "Start search the word...")
                            }
                        }
                        if (state.value.isLoading) {
                            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            }
        }
    }
}
