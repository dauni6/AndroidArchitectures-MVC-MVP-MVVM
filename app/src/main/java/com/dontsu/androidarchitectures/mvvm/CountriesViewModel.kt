package com.dontsu.androidarchitectures.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dontsu.androidarchitectures.model.CountriesApiService
import com.dontsu.androidarchitectures.model.Country
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CountriesViewModel: ViewModel() {

    private val countries = MutableLiveData<List<String>>()
    private val countryError = MutableLiveData<Boolean>()
    private val service: CountriesApiService = CountriesApiService()

    init {
        fetchCountries()
    }

    fun getCountries(): LiveData<List<String>> {
        return countries
    }

    fun getCountryError(): LiveData<Boolean> {
        return countryError
    }

    private fun fetchCountries() {
        service.getCountries()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Country>>() {

                override fun onSuccess(value: List<Country>) {
                    val countryNames = arrayListOf<String>()
                    for (country in value!!.iterator()) {
                        countryNames.add(country.countryName)
                    }
                    countries.value = countryNames
                    countryError.value = false
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    countryError.value = true
                }
            })
    }

    fun onRefresh() {
        fetchCountries()
    }
}