package home

import KuripTheme
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import ui.PromptBar

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun App(
    vm: MainVm = remember { MainVm() }
) {
    val state = vm.kuripFlow.collectAsState()
    KuripTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                PromptBar { vm.addKurip(it) }
            },
            content = { paddingValues ->
                LazyColumn(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxHeight(),
                    reverseLayout = true,
                ) {
                    items(state.value.size) { index ->
                        ListItem(
                            text = { Text(state.value[index].body) },
                        )
                    }
                }
            }
        )
    }
}
