package com.dontsu.androidarchitectures.mvc

import com.dontsu.androidarchitectures.model.CountriesApiService
import com.dontsu.androidarchitectures.model.Country
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CountriesController(val activity: MVCActivity) {

    private val service: CountriesApiService = CountriesApiService()

    init {
        fetchCountries()
    }

    private fun fetchCountries() {
        service.getCountries()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableSingleObserver<List<Country>>(){

                override fun onSuccess(value: List<Country>) {
                    val countryNames = arrayListOf<String>()
                    for (country in value!!.iterator()) {
                        countryNames.add(country.countryName)
                    }
                    activity.setValues(countryNames)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    activity.onError()
                }
            })
    }

    fun onRefresh() {
        fetchCountries()
    }

}