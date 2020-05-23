package com.dontsu.androidarchitectures.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class CountriesApiService {
    private val BASE_URL = "https://restcountries.eu/rest/v2/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CountriesApi::class.java)

    fun getCountries(): Single<List<Country>> {
        return api.getCountries()
    }
}