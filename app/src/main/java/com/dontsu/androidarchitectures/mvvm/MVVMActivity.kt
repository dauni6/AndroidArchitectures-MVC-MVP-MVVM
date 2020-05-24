package com.dontsu.androidarchitectures.mvvm

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dontsu.androidarchitectures.R
import kotlinx.android.synthetic.main.activity_m_v_v_m.*
import kotlinx.android.synthetic.main.row_layout.*

/**
 * MVVM(Model - View - ViewModel)
 * **/
class MVVMActivity : AppCompatActivity() {

    private var listValues = arrayListOf<String>()
    private var adapter: ArrayAdapter<String>? = null
    private var list: ListView? = null
    private var viewModel: CountriesViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_m_v_v_m)
        title = "MVVM Activity"

        viewModel = ViewModelProviders.of(this).get(CountriesViewModel::class.java)

        list = listLayout
        adapter = ArrayAdapter<String>(this, R.layout.row_layout, R.id.countryName, listValues)
        list?.adapter = adapter
        list?.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, "클릭한 나라 :  ${listValues[position]}", Toast.LENGTH_SHORT).show()
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel!!.getCountries().observe(this, Observer { countries ->
            countries?.let {
                listValues.clear()
                listValues.addAll(countries)
                list!!.visibility = View.VISIBLE
                adapter!!.notifyDataSetChanged()
            }
        })

        viewModel!!.getCountryError().observe(this, Observer { error ->
            error?.let {
                loading.visibility = View.GONE
                if (error) {
                    Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_SHORT).show()
                    retryButton.visibility = View.VISIBLE
                } else {
                    retryButton.visibility = View.GONE
                }
            }
        })
    }

    fun onRetry(view: View) {
        viewModel?.onRefresh()
        listLayout.visibility = View.GONE
        retryButton.visibility = View.GONE
        loading.visibility = View.VISIBLE
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, MVVMActivity::class.java)
    }
}
