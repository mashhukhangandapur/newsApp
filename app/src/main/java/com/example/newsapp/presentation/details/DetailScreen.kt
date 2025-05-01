package com.example.newsapp.presentation.details

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presentation.details.components.DetailsTopBar
import androidx.core.net.toUri
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapp.R
import com.example.newsapp.presentation.Dimens.MediumPadding1
import com.example.newsapp.util.UIComponent

@Composable
fun DetailScreen(
    article: Article,
    event : (DetailsEvent) -> Unit,
    sideEffect: UIComponent?,
    navigateUp : () -> Unit
){
    val context = LocalContext.current

    LaunchedEffect(key1 = sideEffect) {
        sideEffect?.let {
            when(sideEffect){
                is UIComponent.Toast ->{
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                    event(DetailsEvent.RemoveSideEffect)
                }
                else -> Unit
            }
        }
    }
    Column(
        modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding())
    {
        DetailsTopBar(
            onBrowsingClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = article.url.toUri()
                if (it.resolveActivity(context.packageManager)!= null){
                    context.startActivity(it)
                }
                }
            },
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT,article.url)
                    it.type = "text/plain"
                    if (it.resolveActivity(context.packageManager)!= null){
                        context.startActivity(it)
                    }
                }
            },
            onBookMarkClick = {event(DetailsEvent.UpsertDeleteArticle(article))},
            onBackClick = navigateUp
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = MediumPadding1,
                end = MediumPadding1,
                top = MediumPadding1
            )

        ) {
          item{
              AsyncImage(
                  model = ImageRequest.Builder(context = context).data(article.urlToImage).build(),
                  contentDescription = null,
                  modifier = Modifier
                      .fillMaxWidth()
                      .height(248.dp)
                      .clip(MaterialTheme.shapes.medium),
                  contentScale = ContentScale.Crop
              )

              Spacer(modifier = Modifier.height(MediumPadding1))

              Text(
                  text = article.title,
                  style = MaterialTheme.typography.displaySmall,
                  color = colorResource(id = R.color.text_title)
              )

              Text(
                  text = article.content,
                  style = MaterialTheme.typography.bodyMedium,
                  color = colorResource(id = R.color.body)
              )
          }
        }
    }
}
