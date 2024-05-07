package com.ahmrh.newsapp.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.ahmrh.newsapp.R
import com.ahmrh.newsapp.common.util.DateUtils
import com.ahmrh.newsapp.common.util.EntityUtils
import com.ahmrh.newsapp.domain.entity.News
import com.ahmrh.newsapp.ui.theme.NewsAppTheme

@Composable
fun Headline(
    modifier: Modifier = Modifier,
    news: News = EntityUtils.getNewsPlaceholder(),
    onClick: () -> Unit = {}
) {
    Surface {

        Column(
            modifier = modifier.clickable{
                onClick()
            }
                .padding(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SubcomposeAsyncImage(
                model = news.urlToImage,
                contentDescription = "Image",
                modifier = Modifier
                    .size(
                        height = 300.dp,
                        width = 500.dp
                    )
                    .clip(
                        shape = RoundedCornerShape(8.dp)
                    ),

                contentScale = ContentScale.Crop,
            ) {
                val state = painter.state
                if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                    LoadingContent(
                        modifier = Modifier.background(MaterialTheme.colorScheme.surfaceContainer)
                    )
                } else {
                    SubcomposeAsyncImageContent()
                }
            }
            Column (
                verticalArrangement = Arrangement.spacedBy(8.dp)

            ){

                Text(
                    news.source,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    news.title,
                    style = MaterialTheme.typography.headlineMedium,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }



            Text(
                DateUtils.getElapsedTime(news.publishedAt),
                style = MaterialTheme.typography.bodyMedium
            )

            HorizontalDivider()
        }
    }

}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HeadlinePreview() {
    NewsAppTheme {
        Headline()
    }

}