package com.example.alishopping.shopViewModel


import android.annotation.SuppressLint
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
import com.example.alishopping.entities.Authentication
import com.example.alishopping.entities.Product
import com.example.alishopping.entities.Receipt
import com.example.alishopping.entities.ReceiptDetail
import com.example.alishopping.pages.Home
import com.example.alishopping.products.BasketProducts
import com.example.alishopping.repository.AuthenticationRepository
import com.example.alishopping.repository.ProductRepository
import com.example.alishopping.repository.ReceiptDetailRepository
import com.example.alishopping.repository.ReceiptRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class ShopViewModel(
    private val repositoryAuth: AuthenticationRepository,
    private val repositoryReceipt: ReceiptRepository,
    private val repositoryReceiptDetail: ReceiptDetailRepository,
    private val repositoryProduct: ProductRepository
) : ViewModel() {


    var _email = MutableLiveData<String>()
    val email: LiveData<String> = _email


    var _password = MutableLiveData<String>()
    val password: LiveData<String> = _password


    var _tel = MutableLiveData<String>()
    val tel: LiveData<String> = _tel


    var _address = MutableLiveData<String>()
    val address: LiveData<String> = _address

    private val _emailLogin = MutableLiveData<String>()
    val emailLogin: LiveData<String> = _emailLogin

    private val _passwordLogin = MutableLiveData<String>()
    val passwordLogin: LiveData<String> = _passwordLogin

    private val _infoEmail = MutableLiveData<String>()
    val infoEmail: LiveData<String> = _infoEmail

    private val _infoAddress = MutableLiveData<String>()
    val infoAddress: LiveData<String> = _infoAddress

    private val _infoTel = MutableLiveData<String>()
    val infoTel: LiveData<String> = _infoTel


    private val _allProduct = MutableLiveData<List<Authentication>>()
    val allProduct: LiveData<List<Authentication>> = _allProduct

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


    private val _getAllProduct = MutableLiveData<List<Product>>()
    val getAllProduct: LiveData<List<Product>> = _getAllProduct

    private val _getAllReceipt = MutableLiveData<List<Receipt>>()
    val getAllReceipt: LiveData<List<Receipt>> = _getAllReceipt


    fun setEmailLogin(email: String) {
        _emailLogin.value = email
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

    fun setAddressInfo(email: String) {
        _infoAddress.value = email
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
        getAll()
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


        viewModelScope.launch {
            val email = _email.value ?: ""
            val password = _password.value ?: ""
            val tel = _tel.value ?: ""
            val address = _address.value ?: ""

            if (!isFormValid(email, password, tel, address)) {
                showToast(context, "Please fill all fields correctly")

                return@launch
            }

            if (!isValidEmailFormat(email)) {
                showToast(context, "Please enter a valid email address")
                return@launch
            }
            val existingUser = repositoryAuth.search(email)
            if (existingUser?.email != email) {

                val user = Authentication(0, email, password, tel, address)
                insert(user)
                showToast(context, "Successfully registered")

                _email.value = ""
                _password.value = ""
                _tel.value = ""
                _address.value = ""

                return@launch backToLogin(navController)

            } else {
                showToast(context, "Email already exists")
            }


        }


    }


    fun getInfoEmail() {
        viewModelScope.launch {

            _infoEmail.value = repositoryAuth.search(_emailLogin.value.toString())?.email.toString()


        }
        viewModelScope.launch {
            _infoTel.value = repositoryAuth.search(_emailLogin.value.toString())?.tel
        }
        viewModelScope.launch {
            _infoAddress.value =
                repositoryAuth.search(_emailLogin.value.toString())?.address.toString()
        }


    }


    fun showToast(context: Context, message: String) {

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    }


    private fun isFormValid(
        email: String,
        password: String,
        tel: String,
        address: String
    ): Boolean {
        return email.isNotEmpty() &&
                password.isNotEmpty() &&
                tel.isNotEmpty() &&
                address.isNotEmpty() &&
                tel.isDigitsOnly() &&
                email.contains("@") &&
                email.contains(".") &&
                isValidEmailFormat(email)
    }

    private fun isValidEmailFormat(email: String): Boolean {
        return email.trim().split("@").size == 2 && email.trim().split("@")[1].substring(0)
            .endsWith(".com")
    }

    private fun getAll() {

        viewModelScope.launch {
            _allProduct.value = repositoryAuth.getAll()

        }
    }

    fun insert(auth: Authentication) {
        viewModelScope.launch {
            repositoryAuth.insert(auth)
            getAll()

        }
    }

    fun update(auth: Authentication) {
        viewModelScope.launch {
            repositoryAuth.update(auth)
            getAll()
        }
    }

    fun delete(auth: Authentication) {
        viewModelScope.launch {
            repositoryAuth.delete(auth)
            getAll()
        }
    }


    fun accessHomePage(
        email: String,
        password: String,
        navController: NavController,
        context: Context
    ) {
        viewModelScope.launch {


            val result = repositoryAuth.search(email, password)
            if (result?.email == _emailLogin.value && result?.password == _passwordLogin.value) {
                navController.navigate(Navigate.HomePage)
            } else {
                showToast(context, "Wrong email or password")
            }


        }
    }

    fun insertProduct(product: List<Product>) {
        viewModelScope.launch {
            repositoryProduct.insertProduct(product)
        }
    }

    fun getAllProduct() {
        viewModelScope.launch {
            _getAllProduct.value = repositoryProduct.getAllProduct()
        }
    }


    @SuppressLint("SimpleDateFormat")
    fun placeOrder(totalPrice: Int, basketProducts: List<BasketProducts>) {
        Log.d("PLACEHOLDER", "ReceiptDetail inserted for product: ")

        val date = Date()
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val formattedDate = formatter.format(date)

        viewModelScope.launch {
            try {
                val receipt = Receipt(
                    receiptNumber = 0,
                    receiptDate = formattedDate,
                    receiptTotal = totalPrice
                )

                repositoryReceipt.insertReceipt(receipt)
                val receiptId = repositoryReceipt.getAllReceipt().last().receiptNumber
                Log.d("PLACEHOLDER", "BASKET LIST CONTENT OR product: ")

                val basketList = _basketList.value
                Log.d("PLACEHOLDER", "Basket list content: $basketList")
                basketList?.forEachIndexed { index, product ->

                    val receiptDetail = ReceiptDetail(
                        productName = product.basketProductName,
                        quantity = product.basketProductQuantity,
                        price = product.basketProductPrice.toDouble(),
                        receiptNumber = receiptId
                    )
                    try {
                        repositoryReceiptDetail.insertReceiptDetail(receiptDetail)

                        Log.d(
                            "PLACEHOLDER",
                            "ReceiptDetail inserted for product: ${product.basketProductName}"
                        )
                    } catch (e: Exception) {
                        Log.e("PLACEHOLDER", "Error inserting receipt detail: ${e.message}")
                    }
                }
                clearTotalPrice()
                allRemoveBasketList()
            } catch (e: Exception) {
                Log.e("PLACEHOLDER", "Error inserting receipt: ${e.message}")
            }
        }
    }


}













