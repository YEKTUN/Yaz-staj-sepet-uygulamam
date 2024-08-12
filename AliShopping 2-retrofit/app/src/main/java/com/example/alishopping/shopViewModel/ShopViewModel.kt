package com.example.alishopping.shopViewModel


import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.alishopping.Navigate
import com.example.alishopping.api_retrofit.AuthT
import com.example.alishopping.api_retrofit.AuthTRepository
import com.example.alishopping.api_retrofit.ProductBack
import com.example.alishopping.api_retrofit.ProductRepositoryBack
import com.example.alishopping.api_retrofit.ReceiptDetailT
import com.example.alishopping.pages.Home
import com.example.alishopping.products.BasketProducts
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ShopViewModel : ViewModel() {


    var _email = MutableLiveData<String>()
    val email: LiveData<String> = _email


    var _password = MutableLiveData<String>()
    val password: LiveData<String> = _password


    var _tel = MutableLiveData<String>()
    val tel: LiveData<String> = _tel


    var _username = MutableLiveData<String>()
    val username: LiveData<String> = _username


    private val _usernameLogin = MutableLiveData<String>()
    val usernameLogin: LiveData<String> = _usernameLogin

    private val _passwordLogin = MutableLiveData<String>()
    val passwordLogin: LiveData<String> = _passwordLogin

    private val _infoEmail = MutableLiveData<String>()
    val infoEmail: LiveData<String> = _infoEmail

    private val _infoUsername = MutableLiveData<String>()
    val infoUsername: LiveData<String> = _infoUsername

    private val _infoTel = MutableLiveData<String>()
    val infoTel: LiveData<String> = _infoTel


    private var _homelist = MutableLiveData<List<Home>>()
    val homeList: LiveData<List<Home>> = _homelist

    private val _basketList = MutableLiveData<List<BasketProducts>>(mutableListOf())
    val basketList: LiveData<List<BasketProducts>> get() = _basketList

    private val _totalPrice = MutableLiveData<Int>(0)
    val totalPrice: LiveData<Int> = _totalPrice

    private val _showPaymentScreen = MutableLiveData<Boolean>(false)
    val showPaymentScreen: LiveData<Boolean> = _showPaymentScreen

    private val _creditCardApply = MutableLiveData<Boolean>(false)
    val creditCardApply: LiveData<Boolean> = _creditCardApply


    private val _getAllProduct = MutableLiveData<List<ProductBack>>()
    val getAllProduct: LiveData<List<ProductBack>> = _getAllProduct


    fun setUsernameLogin(username: String) {
        _usernameLogin.value = username
    }

    fun setPasswordLogin(password: String) {
        _passwordLogin.value = password
    }

    fun setEmailInfo(email: String) {
        _infoEmail.value = email
    }

    fun setTelInfo(email: String) {
        _infoTel.value = email
    }

    fun setUsernameInfo(email: String) {
        _infoUsername.value = email
    }


    fun allRemoveBasketList() {

        _basketList.value = emptyList()
    }

    fun showCreditCardApply(show: Boolean) {
        _creditCardApply.value = show


    }


    fun showPaymentScreeen(show: Boolean) {
        _showPaymentScreen.value = show

    }


    init {

        _basketList.value = listOf()
        _totalPrice.value = 0
    }


    fun totalPrice(basketProducts: Int) {
        var total = 0
        _basketList.value?.forEachIndexed { index, basketProduct ->
            val quantity = basketProduct.basketProductQuantity
            total += basketProduct.basketProductPrice.toInt() * quantity
        }
        _totalPrice.value = total

    }

    fun clearTotalPrice() {
        _totalPrice.value = 0
    }

    fun addToBasket(product: BasketProducts, context: Context) {
        val updatedList = _basketList.value?.toMutableList() ?: mutableListOf()
        if (updatedList.any { it.basketProductName == product.basketProductName }) {
            showToast(context, "Zaten ekli miktarını  sepetten arttırabilirsiniz")
        } else {

            updatedList.add(product)
            _basketList.value = updatedList
            updateTotalPrice()
        }
    }

    fun updateBasketProductQuantity(index: Int, newQuantity: Int) {
        _basketList.value = _basketList.value?.toMutableList()?.apply {
            val product = this[index]
            this[index] = product.copy(basketProductQuantity = newQuantity)
        }
        updateTotalPrice()
    }


    private fun updateTotalPrice() {
        val currentList = _basketList.value ?: listOf()
        val total = currentList.sumOf { it.basketProductPrice.toInt() * it.basketProductQuantity }
        _totalPrice.value = total
    }

    fun deleteToBasket(index: Int) {
        val updatedList = _basketList.value?.toMutableList() ?: mutableListOf()
        if (index in updatedList.indices) {
            updatedList.removeAt(index)
            _basketList.value = updatedList
        }
        updateTotalPrice()
    }


    fun backToLogin(navController: NavController) {

        navController.popBackStack(Navigate.SignUp, true)

    }


    fun register(navController: NavController, context: Context) {

        val authRepo = AuthTRepository()
        viewModelScope.launch {
            val username = _username.value ?: ""
            val password = _password.value ?: ""
            val email = _email.value ?: ""
            val tel = _tel.value ?: ""

            if (!isFormValid(email, password, tel, username)) {
                showToast(context, "Please fill all fields correctly")

                return@launch
            }

            if (!isValidEmailFormat(email)) {
                showToast(context, "Please enter a valid email address")
                return@launch
            }
            val authtRes = authRepo.searchUsername(AuthT(username, "", "", ""))
            val autht = authtRes.auths.firstOrNull()
            Log.d(
                "PLACEHOLDER",
                "USERNAME: ${autht?.username}"
            )

            if (autht == null || autht.username != username) {

                val newUser = AuthT(username, password, email, tel)
                authRepo.createAuth(newUser)
                showToast(context, "Successfully registered")

                _email.value = ""
                _password.value = ""
                _tel.value = ""
                _username.value = ""

                return@launch backToLogin(navController)

            } else {
                showToast(context, "Username already exists")
            }


        }


    }


    fun getInformation() {
        val authRepo = AuthTRepository()
        viewModelScope.launch {
            val result = authRepo.login(
                AuthT(
                    _usernameLogin.value.toString(),
                    _passwordLogin.value.toString(),
                    "",
                    ""
                )
            )



            _infoEmail.value = result.auths.firstOrNull()?.email.toString()


        }
        viewModelScope.launch {
            val result = authRepo.login(
                AuthT(
                    _usernameLogin.value.toString(),
                    _passwordLogin.value.toString(),
                    "",
                    ""
                )
            )
            _infoTel.value = result.auths.firstOrNull()?.tel.toString()
        }
        viewModelScope.launch {
            val result = authRepo.login(
                AuthT(
                    _usernameLogin.value.toString(),
                    _passwordLogin.value.toString(),
                    "",
                    ""
                )
            )
            _infoUsername.value = result.auths.firstOrNull()?.username.toString()
        }


    }


    fun showToast(context: Context, message: String) {

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    }


    private fun isFormValid(
        email: String,
        password: String,
        tel: String,
        username: String
    ): Boolean {
        return email.isNotEmpty() &&
                password.isNotEmpty() &&
                tel.isNotEmpty() &&
                username.isNotEmpty() &&
                tel.isDigitsOnly() &&
                email.contains("@") &&
                email.contains(".") &&
                isValidEmailFormat(email)
    }

    private fun isValidEmailFormat(email: String): Boolean {
        return email.trim().split("@").size == 2 && email.trim().split("@")[1].substring(0)
            .endsWith(".com")
    }


    fun accessHomePage(
        username: String,
        password: String,
        navController: NavController,
        context: Context
    ) {
        viewModelScope.launch {
            try {
                val authRepo = AuthTRepository()
                val result = authRepo.login(AuthT(username, password, "", ""))

                val matchedUser =
                    result.auths.firstOrNull { it.username == username && it.password == password }

                if (matchedUser != null) {

                    navController.navigate(Navigate.HomePage) {
                        popUpTo(Navigate.LoginMenu) { inclusive = true }
                    }
                    showToast(context, "Successfully logged in")

                } else {

                    showToast(context, "Wrong username or password")
                }
            } catch (e: Exception) {

                showToast(context, "An error occurred: ${e.message}")
            }


        }
    }


    fun getAllProduct() {
        val repository = ProductRepositoryBack()
        viewModelScope.launch {
            val products = repository.getProducts()
            _getAllProduct.value = products
        }
    }


    fun placeOrder(totalPrice: Int) {
        val repository = ProductRepositoryBack()
        viewModelScope.launch {
            try {
                val date =
                    SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(
                        Date()
                    )
                Log.e("PLACEHOLDER", "RECEİPT EKLEMEDEN ÖNCE")
                repository.createReceipt(totalPrice, date)
                Log.e("PLACEHOLDER", "Receipt ID: RECEİPT EKLEDİKTEN SONRA")
                val receipts = repository.getReceipt()


                val receiptId = receipts.last().receiptNumber



                Log.e("PLACEHOLDER", "Receipt ID: $receiptId")
                val basketList = _basketList.value

                basketList?.map { product ->
                    val detail = ReceiptDetailT(
                        receiptNumber = receiptId,
                        productName = product.basketProductName,
                        quantity = product.basketProductQuantity,
                        total = product.basketProductQuantity * product.basketProductPrice.toInt(),
                        price = product.basketProductPrice.toDouble(),
                    )
                    repository.createReceiptDetails(detail)

                }


            } catch (e: Exception) {
                Log.d(
                    "PLACEHOLDER",
                    "Error inserting receipt: ${e.message}, StackTrace: ${e.stackTraceToString()}"
                )
            } finally {
                clearTotalPrice()
                allRemoveBasketList()
            }
        }
    }


}
















