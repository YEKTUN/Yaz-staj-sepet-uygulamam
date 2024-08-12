package com.example.alishopping.homepagepages


import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.alishopping.R
import com.example.alishopping.shopViewModel.ShopViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ShoppingCart(
    innerPadding: PaddingValues,
    viewModel: ShopViewModel,
    navController: NavController
) {
    val showPaymentScreen by viewModel.showPaymentScreen.observeAsState(false)



    Column(
        horizontalAlignment = Alignment.CenterHorizontally,

        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(
                colorResource(id = R.color.main_menu_background_card),

                )

    ) {
        Text(text = stringResource(id = R.string.basket_shopcart_name), color = Black)

        Box {
            if (!showPaymentScreen) {

                SentBasketProducts(viewModel = viewModel)
            } else {

                PaymentScreen(viewModel = viewModel)
            }
        }
        Payment(
            viewModel = viewModel

        )


    }
}


@SuppressLint("RememberReturnType")
@Composable
fun SentBasketProducts(viewModel: ShopViewModel) {
    val basketList by viewModel.basketList.observeAsState(initial = emptyList())
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(450.dp)
    ) {
        if (basketList.isEmpty()) {


            item {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .fillParentMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.basket_your_basket_empty),
                        color = Black,
                        fontSize = 30.sp
                    )
                    Text(
                        text = stringResource(id = R.string.basket_pls_add_product),
                        color = DarkGray,
                        fontSize = 15.sp
                    )
                }
            }


        } else {
            basketList.forEachIndexed { index, basketProducts ->

                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),

                        elevation = CardDefaults.cardElevation(5.dp),


                        content = {

                            Row(
                                modifier = Modifier

                                    .width(500.dp)
                                    .height(100.dp)
                                    .background(White),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {


                                Row(
                                    modifier = Modifier
                                        .size(80.dp)
                                        .offset(10.dp)
                                ) {

                                    GlideImage(
                                        imageModel = basketProducts.basketProductImage,
                                        modifier = Modifier
                                            .size(50.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(Gray),
                                        contentScale = ContentScale.Crop


                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))

                                Column(
                                    modifier = Modifier
                                        .height(120.dp)
                                        .weight(1f)
                                        .offset(x = 20.dp),
                                    verticalArrangement = Arrangement.SpaceEvenly
                                ) {

                                    Text(
                                        text = basketProducts.basketProductName,
                                        color = DarkGray, fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "${basketProducts.basketProductPrice}₺",
                                        color = DarkGray
                                    )
                                }


                                Column(
                                    modifier = Modifier.width(150.dp),
                                    horizontalAlignment = Alignment.End,
                                    verticalArrangement = Arrangement.SpaceEvenly
                                ) {


                                    IconButton(onClick = {
                                        viewModel.deleteToBasket(index)


                                    }) {

                                        Icon(
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = "Delete",
                                            tint = Red

                                        )
                                    }
                                    Row(
                                        Modifier
                                            .width(700.dp),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {


                                        IconButton(onClick = {

                                            if (basketProducts.basketProductQuantity > 1) {


                                                val newQuantity =
                                                    basketProducts.basketProductQuantity - 1
                                                viewModel.updateBasketProductQuantity(
                                                    index,
                                                    newQuantity
                                                )


                                            }

                                            if (basketProducts.basketProductQuantity <= 1) {
                                                viewModel.deleteToBasket(index)
                                                viewModel.totalPrice(basketProducts.basketProductQuantity)
                                            }
                                        }) {

                                            Icon(
                                                imageVector = Icons.Filled.Remove,
                                                contentDescription = "",
                                                tint = Black
                                            )
                                        }

                                        Text(
                                            text = basketProducts.basketProductQuantity.toString(),
                                            color = Black
                                        )


                                        IconButton(onClick = {

                                            val newQuantity =
                                                basketProducts.basketProductQuantity + 1
                                            viewModel.updateBasketProductQuantity(
                                                index,
                                                newQuantity
                                            )

//

                                        }) {

                                            Icon(
                                                imageVector = Icons.Rounded.Add,
                                                contentDescription = "",
                                                tint = Black
                                            )
                                        }
                                        Text(
                                            text = "${basketProducts.basketProductPrice.toInt() * basketProducts.basketProductQuantity}₺",
                                            color = Black
                                        )


                                    }
                                }


                            }


                        }
                    )
                }
            }


        }

    }


}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Payment(
    viewModel: ShopViewModel,

    ) {
    var radioButtonCredit by remember {

        mutableStateOf(false)
    }
    var radioButtonCash by remember {

        mutableStateOf(false)
    }
    val totalPrice by viewModel.totalPrice.observeAsState(0)
    val context = LocalContext.current
    val basketList by viewModel.basketList.observeAsState(emptyList())
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }






    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.main_menu_background_card))
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.main_menu_background_card)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {

                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,

                        ) {

                        RadioButton(
                            selected = radioButtonCredit,
                            onClick = {
                                radioButtonCredit = !radioButtonCredit
                                radioButtonCash = false

                            },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Black,
                                unselectedColor = DarkGray
                            )
                        )
                        Text(text = stringResource(id = R.string.payment_credit), color = Black)
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.offset(y = (-10).dp)
                    ) {

                        RadioButton(
                            selected = radioButtonCash,
                            onClick = {
                                radioButtonCash = !radioButtonCash
                                radioButtonCredit = false

                            },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Black,
                                unselectedColor = DarkGray
                            )
                        )
                        Text(text = stringResource(id = R.string.payment_cash), color = Black)
                    }


                }
                Button(onClick = {
                    if (radioButtonCredit && basketList.isNotEmpty()) {
                        viewModel.showToast(context = context, message = "Kredi Seçildi")
                        viewModel.showPaymentScreeen(true)
                    }
                    if (basketList.isEmpty()) {

                        viewModel.showToast(context = context, message = "Sepetinizde Ürün Yok")
                    }
                    if (radioButtonCash && basketList.isNotEmpty()) {
                        viewModel.showToast(
                            context = context,
                            message = "Ödemeye Nakit Olarak Devam Ediyorsunuz"
                        )


                    }
                }) {

                    Text(text = stringResource(id = R.string.payment_info_go))
                }

            }
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-10).dp)
            ) {
                drawLine(
                    color = DarkGray,
                    start = Offset(22f, 0f),
                    end = Offset(1062f, 0f),
                    strokeWidth = 2f
                )

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Toplam Tutar:${totalPrice} ₺",
                    color = Black,
                    modifier = Modifier.offset(x = 10.dp)
                )
                Button(
                    onClick = {
                        if (viewModel.creditCardApply.value == true && radioButtonCredit && basketList.size > 0) {

                            scope.launch {
                                snackbarHostState
                                    .showSnackbar(
                                        message = "Kredi Kartıyla Siparişiniz Alındı",
                                        actionLabel = "Tamam",


                                        )


                            }
                            viewModel.showCreditCardApply(false)
                            viewModel.placeOrder(totalPrice)

                        } else if (radioButtonCash && basketList.size > 0) {

                            scope.launch {
                                snackbarHostState
                                    .showSnackbar(
                                        message = "Nakit Olarak Siparişiniz Alındı",
                                        actionLabel = "Tamam",

                                        )
                            }
                            viewModel.placeOrder(totalPrice)


                        } else if (radioButtonCredit && viewModel.creditCardApply.value == false) {
                            viewModel.showToast(
                                context = context,
                                message = "Kredi Kartı Bilgileri Girilmedi"
                            )
                        } else if (radioButtonCash && basketList.size == 0) {
                            viewModel.showToast(context = context, message = "Sepette Ürün Yok")
                        }


                    }, modifier = Modifier.offset(x = (-10).dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Black,
                        contentColor = White
                    )
                ) {
                    Text(text = stringResource(id = R.string.payment_order))

                }

            }
        }

    }


}


@Composable
fun PaymentScreen(
    viewModel: ShopViewModel

) {
    var cardNumber by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }
    val context = LocalContext.current

    Box(
        modifier = Modifier.size(500.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.payment_credit_title),
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 16.dp), fontWeight = FontWeight.Bold,
                color = Black
            )

            OutlinedTextField(
                value = cardNumber,
                onValueChange = {
                    if (it.length <= 16) {
                        cardNumber = it
                    }

                },
                label = {
                    Text(
                        stringResource(id = R.string.payment_credit_cardnumber),
                        color = Gray
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(0.8f),
                textStyle = TextStyle(color = DarkGray),
                maxLines = 1
            )

            OutlinedTextField(
                value = expiryDate,
                onValueChange = {
                    if (it.length <= 5) {
                        expiryDate = it

                    }
                },
                label = { Text(stringResource(id = R.string.payment_credit_date), color = Gray) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth(0.8f),
                textStyle = TextStyle(color = DarkGray),
                maxLines = 1
            )

            OutlinedTextField(
                value = cvv,
                onValueChange = {
                    if (it.length <= 3) {
                        cvv = it
                    }
                },
                label = { Text(stringResource(id = R.string.payment_credit_cvv), color = Gray) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                modifier = Modifier.fillMaxWidth(0.8f),
                textStyle = TextStyle(color = DarkGray),
                maxLines = 1

            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        if (cardNumber.isNotEmpty() && expiryDate.isNotEmpty() && cvv.isNotEmpty()) {

                            viewModel.showToast(
                                context = context,
                                message = "Kredi Bilgileri Girildi Siparişi Onaylayabilirsiniz"

                            )
                            viewModel.showCreditCardApply(true)
                            viewModel.showPaymentScreeen(false)
                        } else {

                            viewModel.showToast(
                                context = context,
                                message = "Kredi Bilgilerini Kontrol Edin"
                            )
                            viewModel.showCreditCardApply(false)
                        }
                    },
                    modifier = Modifier.width(200.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Gray
                    )


                ) {
                    Text(text = stringResource(id = R.string.payment_credit_submit), color = White)
                }
                Button(
                    onClick = {
                        viewModel.showPaymentScreeen(false)
                    },
                    modifier = Modifier.width(100.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Red,
                        contentColor = White
                    )

                ) {
                    Text(text = stringResource(id = R.string.payment_credit_back))
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
                }
            }


        }
    }
}





