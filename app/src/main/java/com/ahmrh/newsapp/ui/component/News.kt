package com.ahmrh.newsapp.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.ahmrh.newsapp.common.util.DateUtils
import com.ahmrh.newsapp.common.util.EntityUtils
import com.ahmrh.newsapp.domain.entity.News
import com.ahmrh.newsapp.ui.theme.NewsAppTheme

@Composable
fun News(
     modifier: Modifier = Modifier,
     news: News = EntityUtils.getNewsPlaceholder(),
     onClick: () -> Unit = {}
) {
    Column (
        modifier
            .padding(vertical = 8.dp)
            .clickable { onClick() }
    ){

        Row (
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column(

                Modifier.weight(5f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    news.source,
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    news.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.width(16.dp))

            Box (
                Modifier.weight(2f),
                contentAlignment = Alignment.TopEnd
            ){

                SubcomposeAsyncImage(
                    model = news.urlToImage,
                    contentDescription = "Image",
                    modifier = Modifier
                        .size(
                            height = 100.dp,
                            width = 100.dp
                        )
                        .clip(
                            shape = RoundedCornerShape(8.dp)
                        )
                        .wrapContentSize(),

                    contentScale = ContentScale.Crop,
                ) {
                    val state = painter.state
                    if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                        LoadingContent(
                            modifier = Modifier.background(
                                MaterialTheme.colorScheme.surfaceContainer
                            )
                        )
                    } else {
                        SubcomposeAsyncImageContent()
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            DateUtils.getElapsedTime(news.publishedAt),
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider()
    }
}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun NewsPreview() {
    NewsAppTheme {
        Surface {

            LazyColumn (
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ){
                items(5){

                    News()
                }
            }
        }
    }
}