package com.example.alishopping.homepagepages

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleLeft
import androidx.compose.material.icons.filled.ArrowCircleRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.example.alishopping.R
import com.example.alishopping.products.BasketProducts
import com.example.alishopping.shopViewModel.ShopViewModel
import kotlinx.coroutines.launch

@Composable
fun Home(innerPadding: PaddingValues, viewModel: ShopViewModel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)

            .background(colorResource(id = R.color.main_menu_background_card))
    ) {
//        item { SimplePager(items = listOf("Page 1", "Page 2", "Page 3", "Page 4", "Page 5")) }
//        item { Cards() }

        item {

            ProductList(viewModel = viewModel)

        }
    }


}


@Composable
fun SimplePager(items: List<String>) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        LazyRow(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            items(items) { item ->
                Box(
                    modifier = Modifier
                        .width(395.dp)
                        .height(200.dp)

                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = item, style = MaterialTheme.typography.headlineLarge)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            IconButton(onClick = {
                coroutineScope.launch {
                    listState.animateScrollBy(-1080f)
                }
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowCircleLeft,
                    contentDescription = "",
                    tint = White,
                    modifier = Modifier.size(80.dp)
                )

            }

            IconButton(onClick = {
                coroutineScope.launch {
                    listState.animateScrollBy(1080f)
                }
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowCircleRight,
                    contentDescription = "",
                    tint = White,
                    modifier = Modifier.size(80.dp)
                )

            }
        }
    }
}

@Composable
fun Cards() {

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items(10) { index ->
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .padding(8.dp)
                    .background(color = colorResource(id = R.color.teal_200))
            ) {
                Text(
                    text = "Card ${index + 1}",
                    color = White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }


}

@Composable
fun ProductList(viewModel: ShopViewModel) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {

        viewModel.getAllProduct()
    }
    val getAllProduct = viewModel.getAllProduct.observeAsState(emptyList())

    Text(
        text = stringResource(id = R.string.homepage_products),
        color = Black,
        fontSize = 20.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        fontWeight = FontWeight.Bold
    )


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .height(600.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalArrangement = Arrangement.Center,


        ) {


        items(getAllProduct.value) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Transparent),


                content = {
                    Column(
                        modifier = Modifier

                            .fillMaxWidth()
                            .height(350.dp)
                            .background(White)
                            .padding(20.dp),
                        verticalArrangement = Arrangement.SpaceBetween,


                        ) {

                        Text(text = item.productName, color = Black)
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "${item.productPrice}₺",
                            color = Black,
                            fontWeight = FontWeight.Bold
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                        ) {
                            GlideImage(
                                imageModel = item.productImage,
                                modifier = Modifier.size(500.dp),
                                contentScale = ContentScale.Crop
                            )
                        }


                        Button(
                            onClick = {
                                viewModel.addToBasket(
                                    BasketProducts(
                                        item.productName,
                                        item.productImage,
                                        item.productPrice,

                                        1
                                    ), context = context
                                )


                            }, modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(id = R.color.satın_al_btn)
                            )
                        ) {
                            Text(text = stringResource(id = R.string.products_buy), color = White)
                        }
                    }
                }
            )
        }


    }

}

@Composable
fun GlideImage(imageModel: String, modifier: Modifier, contentScale: ContentScale) {
    AsyncImage(
        model = imageModel,
        contentDescription = "",
        imageLoader = ImageLoader.Builder(LocalContext.current).build(),
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(16.dp)) // Resimleri köşeli yapmak için
            .background(Color.Gray),
        contentScale = ContentScale.Crop
    )

}





