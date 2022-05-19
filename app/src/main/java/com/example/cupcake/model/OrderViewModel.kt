package com.example.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

private const val PRICE_PER_CUPCAKE = 2.00

class OrderViewModel : ViewModel() {

//    init {
//        resetOrder()
//    }

    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    private val _flavor = MutableLiveData<String>()
    val flavor: LiveData<String> = _flavor

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    private val _price = MutableLiveData<Double>()
    val price: LiveData<Double> = _price

    fun setQuantity(numberOfCupcakes: Int) {
        _quantity.value = numberOfCupcakes
        updatePrice()
    }

    fun setFlavor(selectedFlavor: String) {
        _flavor.value = selectedFlavor
    }

    fun setDate(selectedDate: String) {
        _date.value = selectedDate
    }

    fun setPrice(price: Double) {
        _price.value = price
    }

    private fun updatePrice() {
        _price.value = (quantity.value ?: 0) * PRICE_PER_CUPCAKE
    }

    fun noFlavorSet(): Boolean {
        return _flavor.value.isNullOrEmpty()
    }

    fun resetOrder() {
        _quantity.value = 0
        _flavor.value = ""
        _date.value = dateOptions[0]
        _price.value = 0.0
    }

    private fun getPickUpOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.ENGLISH)
        val calendar = Calendar.getInstance()

        repeat(4) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE,1)
        }
        return options
    }

    val dateOptions = getPickUpOptions()
    init {
        resetOrder()
    }
}