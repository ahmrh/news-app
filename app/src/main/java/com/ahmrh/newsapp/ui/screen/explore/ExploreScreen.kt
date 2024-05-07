package com.ahmrh.newsapp.ui.screen.explore

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ahmrh.newsapp.ui.navigation.Destination
import com.ahmrh.newsapp.ui.theme.NewsAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(
    navController: NavController = rememberNavController()
) {
    val navigateToExplore = {
        navController.navigate(Destination.Explore.route)
    }

    Scaffold {
        Surface {
            Column(
                modifier = Modifier.padding(it)
            ) {
                var queryString by remember { mutableStateOf("") }
                var isActive by remember {  mutableStateOf(false) }

                SearchBar(
                    query = queryString,
                    onQueryChange = { queryString = it },
                    onSearch = {},
                    active = isActive,
                    onActiveChange = { isActive = !isActive}
                ){
                    // when search bar active goes here
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
        ExploreScreen()
    }
}