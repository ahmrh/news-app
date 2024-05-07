package com.ahmrh.newsapp.ui.screen.explore

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ahmrh.newsapp.common.state.UiState
import com.ahmrh.newsapp.ui.component.ErrorDialog
import com.ahmrh.newsapp.ui.component.LoadingContent
import com.ahmrh.newsapp.ui.component.News
import com.ahmrh.newsapp.ui.theme.NewsAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(
    navController: NavController = rememberNavController(),
    viewModel: ExploreViewModel = hiltViewModel()
) {
    val navigateBack = {
        navController.navigateUp()
    }

    ExploreScreenContent(
        navigateBack = navigateBack,
        viewModel = viewModel
    )


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreenContent(
    navigateBack: () -> Boolean = { false },
    viewModel: ExploreViewModel? = null
){
    val newsListUiState = viewModel?.newsListUiState?.collectAsState()?.value

    var queryString by remember { mutableStateOf("") }
    var isActive by remember {  mutableStateOf(false) }

    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Scaffold {
        Surface {
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = 16.dp, vertical= 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                SearchBar(
                    modifier = Modifier.focusRequester(focusRequester),
                    query = queryString,
                    onQueryChange = {
                        queryString = it
                    },
                    leadingIcon = {
                        IconButton(onClick = {
                            navigateBack()
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                    },
                    onSearch = {
                       viewModel?.getNews(queryString)
                        focusRequester.freeFocus()
                        keyboardController?.hide()
                    },
                    placeholder = {
                        Text("Search for news")
                    },
                    active = false,
                    onActiveChange = { isActive = !isActive}
                ){
                    // when search bar active goes here
                    BackHandler{
                        navigateBack()
                    }
                }
                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                when(newsListUiState){
                    is UiState.Idle -> {

                    }
                    is UiState.Success -> {
                        val newsList = newsListUiState.data

                        val uriHandler = LocalUriHandler.current
                        LazyColumn {
                            items(newsList){
                                News(news = it, onClick = {
                                    uriHandler.openUri(it.url)
                                })
                            }
                        }

                    }
                    is UiState.Loading -> {
                        LoadingContent()
                    }
                    is UiState.Error -> {

                        var openDialogError by remember{ mutableStateOf(true) }

                        when{
                            openDialogError -> {
                                ErrorDialog(
                                    errorMessage = newsListUiState.errorMessage,
                                    onDismiss = {
                                        viewModel.getNews(queryString)
                                        openDialogError = false
                                    },
                                    title = "Error",
                                    dismissText = "Retry"
                                )

                            }
                        }
                    }
                    else -> {

                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ExploreScreenPreview(){
    NewsAppTheme {
        ExploreScreenContent()
    }
}