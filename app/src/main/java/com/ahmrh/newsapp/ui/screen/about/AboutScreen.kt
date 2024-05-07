package com.ahmrh.newsapp.ui.screen.about

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ahmrh.newsapp.R
import com.ahmrh.newsapp.ui.theme.NewsAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    navController: NavController = rememberNavController(),
) {

    val navigateBack = {
        navController.navigateUp()
    }


    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                "About", maxLines = 1, overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.headlineSmall
            )
        }, navigationIcon = {
            IconButton(onClick = {
                navigateBack()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = "Back button"
                )
            }
        })
    }) {
        val uriHandler = LocalUriHandler.current

        Column(
            modifier = Modifier
                .padding(it)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.rakamin_logo),
                contentDescription = "Rakamin Logo",
                modifier = Modifier.clip(RoundedCornerShape(8.dp)).size(200.dp),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(

                textAlign = TextAlign.Justify, text = buildAnnotatedString {
                    append(
                        "This app is developed as part of "
                    )
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(
                            "Rakamin Academy's Project-Based Internship Program "
                        )
                    }
                    append(
                        "in collaboration with "
                    )
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(
                            "Bank Mandiri"
                        )
                    }
                    append(
                        ". Any kind of feedback is appreciated"
                    )
                })

            HorizontalDivider()
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text("© 2024 ahmrh. Personal use only.")

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    IconButton(
                        onClick = {
                            uriHandler.openUri("https://github.com/ahmrh")
                        },

                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.icon_github
                            ), contentDescription = "GitHub"
                        )
                    }

                    IconButton(
                        onClick = {
                            uriHandler.openUri(
                                "https://www.linkedin.com/in/ahmrh/"
                            )
                        }, colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.icon_linkedin
                            ), contentDescription = "LinkedIn"
                        )
                    }
                }
            }


        }

    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AboutScreenPreview() {
    NewsAppTheme {
        Surface {
            AboutScreen()
        }
    }

}
