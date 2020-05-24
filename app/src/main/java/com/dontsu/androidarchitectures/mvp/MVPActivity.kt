package com.dontsu.androidarchitectures.mvp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.dontsu.androidarchitectures.R
import com.dontsu.androidarchitectures.mvc.CountriesController
import kotlinx.android.synthetic.main.activity_m_v_p.*

/**
 * MVP(Model - View - Presenter)
 * Presenter은 어떤 activity 또는 어떤 View를 알게될지 모른다. 오로지 interface를 이용하여 interface와 view만이 서로 알게 됨
 * **/
class MVPActivity : AppCompatActivity(), CountriesPresenter.View {

    private var listValues = arrayListOf<String>()
    private var adapter: ArrayAdapter<String>? = null
    private var list: ListView? = null
    private var presenter: CountriesPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_m_v_p)
        title = "MVP Activity"

        presenter = CountriesPresenter(this)

        list = listLayout
        adapter = ArrayAdapter<String>(this, R.layout.row_layout, R.id.countryName, listValues)
        list?.adapter = adapter
        list?.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, "클릭한 나라 :  ${listValues[position]}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun setValues(countries: List<String>) {
        listValues.clear()
        listValues.addAll(countries)
        retryButton.visibility = View.GONE
        loading.visibility = View.GONE
        listLayout.visibility = View.VISIBLE
        adapter!!.notifyDataSetChanged()
    }
    override fun onError() {
        Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_SHORT).show()
        loading.visibility = View.GONE
        listLayout.visibility = View.GONE
        retryButton.visibility = View.VISIBLE
    }

    fun onRetry(view: View) {
        presenter?.onRefresh()
        listLayout.visibility = View.GONE
        retryButton.visibility = View.GONE
        loading.visibility = View.VISIBLE
    }

    companion object {
        fun newIntent(context: Context) =Intent(context, MVPActivity::class.java)
    }

}
