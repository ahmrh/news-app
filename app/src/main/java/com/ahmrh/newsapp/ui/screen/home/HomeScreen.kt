package com.ahmrh.newsapp.ui.screen.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ahmrh.newsapp.common.state.UiState
import com.ahmrh.newsapp.common.util.EntityUtils
import com.ahmrh.newsapp.domain.entity.News
import com.ahmrh.newsapp.ui.component.ErrorDialog
import com.ahmrh.newsapp.ui.component.Headline
import com.ahmrh.newsapp.ui.component.LoadingContent
import com.ahmrh.newsapp.ui.navigation.Destination
import com.ahmrh.newsapp.ui.theme.NewsAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController = rememberNavController(),
    viewModel: HomeViewModel = hiltViewModel()
) {
    val navigateToExplore = {
        navController.navigate(Destination.Explore.route)
    }

    val headlineListUiState =
        viewModel.headlineListUiState.collectAsState().value

    when (headlineListUiState) {
        is UiState.Success -> {
            HomeScreenContent(headlineList = headlineListUiState.data)
        }

        is UiState.Loading -> {
            LoadingContent()
        }

        is UiState.Error -> {
            ErrorDialog(
                errorMessage = headlineListUiState.errorMessage,
                onDismiss = { },
                onConfirm = { }
            )

        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    headlineList: List<News>
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "News",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.headlineSmall
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = "Localized description"
                        )
                    }
                },
            )
        }
    ) {
        Surface {

            val uriHandler = LocalUriHandler.current
            LazyColumn(

                modifier = Modifier
                    .padding(it)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(headlineList) { headline ->
                    Headline(news = headline, onClick = {
                        uriHandler.openUri(headline.url)
                    })
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
    NewsAppTheme {
        HomeScreenContent(
            listOf(
                EntityUtils.getNewsPlaceholder(),
                EntityUtils.getNewsPlaceholder(),
                EntityUtils.getNewsPlaceholder(),
                EntityUtils.getNewsPlaceholder(),
            )
        )
    }
}