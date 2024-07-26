package com.example.alishopping.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Person2
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.alishopping.Navigate
import com.example.alishopping.R
import com.example.alishopping.homepagepages.Home
import com.example.alishopping.homepagepages.Info
import com.example.alishopping.homepagepages.ShoppingCart
import com.example.alishopping.shopViewModel.ShopViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomePage(navController: NavController, viewModel: ShopViewModel) {

    LaunchedEffect(key1 = true) {
        viewModel.getInfoEmail()
    }
    val infoEmail = viewModel.infoEmail.observeAsState("Empty")
    val infoTel = viewModel.infoTel.observeAsState("Empty")
    val homeList by viewModel.homeList.observeAsState(
        listOf(
            Home(stringResource(id = R.string.bottom_bar_homepage), Icons.Default.Home),
            Home(stringResource(id = R.string.bottom_bar_info), Icons.Default.Search),
        )
    )
    var indexedValue by remember {
        mutableIntStateOf(0)
    }
    var searchProduct by remember {
        mutableStateOf("")
    }

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val basketList by viewModel.basketList.observeAsState(emptyList())




    ModalNavigationDrawer(


        drawerContent = {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .padding(top = 100.dp)
            ) {
                Column(

                    modifier = Modifier
                        .background(colorResource(id = R.color.drawer_backround))
                        .height(650.dp)
                        .width(300.dp),

                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Icon(
                            imageVector = Icons.Rounded.Person,
                            contentDescription = "",
                            tint = White,
                            modifier = Modifier.size(100.dp)
                        )
                        Column(modifier = Modifier.fillMaxWidth()) {

                            Column {

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(text = "E-mail :", color = Black, fontSize = 10.sp)
                                    Text(text = infoEmail.value, color = White, fontSize = 13.sp)

                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(text = "Tel :", color = Black, fontSize = 10.sp)
                                    Text(text = infoTel.value, color = White, fontSize = 13.sp)

                                }
                            }


                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(colorResource(id = R.color.drawer_backround_alt))
                    ) {
                        Text(
                            text = stringResource(id = R.string.drawer_categories),
                            color = DarkGray,
                            fontSize = 20.sp,

                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(start = 20.dp, top = 10.dp)
                                .offset(y = (-5).dp)
                                .align(alignment = Alignment.CenterHorizontally),

                            )
                        CategoryItem(item = stringResource(id = R.string.drawer_categories_electronic_devices))
                        CategoryItem(item = stringResource(id = R.string.drawer_categories_health))
                        CategoryItem(item = stringResource(id = R.string.drawer_categories_baby_products))
                        CategoryItem(item = stringResource(id = R.string.drawer_categories_home))
                        CategoryItem(item = stringResource(id = R.string.drawer_categories_fashion))
                        CategoryItem(item = stringResource(id = R.string.drawer_categories_books))


                    }

                }

            }


        },


        drawerState = drawerState,


        content = {


            Scaffold(


                topBar = {

                    TopAppBar(

                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = colorResource(id = R.color.main_menu_background_card)
                        ),
                        title = {
                            Text(text = "AliShopping")
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                coroutineScope.launch {
                                    drawerState.open()
                                }

                            }) {
                                Icon(
                                    modifier = Modifier
                                        .size(30.dp)
                                        .offset((-5).dp, 0.dp),
                                    imageVector = Icons.Rounded.Person2, contentDescription = "",
                                    tint = Black

                                )
                            }
                        },


                        actions = {

//
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp),
                                horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.CenterVertically,

                                ) {


                                OutlinedTextField(
                                    value = searchProduct, onValueChange = { searchProduct = it },
                                    label = {
                                        Text(
                                            text = stringResource(id = R.string.topbar_search_label),
                                            color = DarkGray
                                        )
                                    },
                                    textStyle = TextStyle(color = Black),
                                    singleLine = true,

                                    modifier = Modifier.width(300.dp),

                                    shape = RoundedCornerShape(20.dp),

                                    )

                                IconButton(onClick = { }) {
                                    Icon(
                                        imageVector = Icons.Filled.Search, contentDescription = "",
                                        tint = Black

                                    )
                                }

                            }


                        }

                    )


                },

                bottomBar = {


                    BottomAppBar(
                        modifier = Modifier.fillMaxWidth(),
                        containerColor = White,

                        content = {

                            NavigationBar(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp, 0.dp, 0.dp, 10.dp),
                                containerColor = colorResource(id = R.color.main_menu_background_card),
                            ) {
                                homeList.forEachIndexed { index, navItem ->
                                    NavigationBarItem(


                                        selected = false,
                                        onClick = {
                                            indexedValue = index


                                        },
                                        icon = {
                                            Icon(
                                                imageVector = navItem.icon,
                                                contentDescription = "",
                                                tint = Black
                                            )
                                        },
                                        modifier = Modifier
                                            .background(colorResource(id = R.color.main_menu_background_card))
                                            .padding(20.dp),
                                        label = {
                                            Text(text = navItem.label, color = Black)
                                        },


                                        )
                                }
                                NavigationBarItem(
                                    selected = false,
                                    onClick = {
                                        indexedValue = 2


                                    },
                                    label = {
                                        Text(
                                            text = stringResource(id = R.string.bottom_bar_shopping_cart),
                                            color = Black
                                        )
                                    },
                                    modifier = Modifier
                                        .background(colorResource(id = R.color.main_menu_background_card))
                                        .padding(20.dp),
                                    icon = {
                                        BadgedBox(badge = {
                                            Badge(
                                                modifier = Modifier.size(
                                                    20.dp,
                                                )
                                            ) {
                                                Text(
                                                    "${basketList.size}",
                                                    color = White,

                                                    )

                                            }
                                        }) {
                                            Icon(
                                                imageVector = Icons.Default.ShoppingCart,
                                                contentDescription = "",
                                                tint = Black
                                            )
                                        }
                                    }

                                )


                            }

                        },


                        )


                },


                ) { innerPadding ->

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Navigate.Home) {
                    composable(Navigate.Home) {
                        Home(
                            innerPadding = innerPadding,
                            viewModel = viewModel
                        )
                    }
                    composable(Navigate.Info) { Info(viewModel = viewModel) }
                    composable(Navigate.ShoppingCart) {
                        ShoppingCart(
                            innerPadding = innerPadding,
                            viewModel = viewModel,
                            navController = navController
                        )
                    }
                }

                LaunchedEffect(indexedValue) {

                    when (indexedValue) {

                        0 -> navController.navigate(Navigate.Home)
                        1 -> navController.navigate(Navigate.Info)
                        2 -> navController.navigate(Navigate.ShoppingCart)

                    }
                }


            }
        }

    )

}


@Composable
fun CategoryItem(item: String) {
    TextButton(
        onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth(),

        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = item,
                color = DarkGray,
                fontSize = 14.sp,

                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 10.dp)
            )
        }


    }
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = (-10).dp)
    ) {
        drawLine(
            color = DarkGray,
            start = Offset(50f, 0f),
            end = Offset(400f, 0f),
            strokeWidth = 1f
        )

    }
    Spacer(modifier = Modifier.height(8.dp))
}









