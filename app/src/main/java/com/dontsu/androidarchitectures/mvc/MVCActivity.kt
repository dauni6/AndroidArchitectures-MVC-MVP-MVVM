package com.dontsu.androidarchitectures.mvc

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.dontsu.androidarchitectures.R
import kotlinx.android.synthetic.main.activity_m_v_c.*
import kotlinx.android.synthetic.main.row_layout.*

/**
 * MVC(Model - View - Controller)
 * **/
class MVCActivity : AppCompatActivity() {

    private var listValues = arrayListOf<String>()
    private var adapter: ArrayAdapter<String>? = null
    private var list: ListView? = null
    private var controller: CountriesController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_m_v_c)
        title = "MVC Activity"

        controller = CountriesController(this)

        list = listLayout
        adapter = ArrayAdapter<String>(this, R.layout.row_layout, R.id.countryName, listValues)
        list?.adapter = adapter
        list?.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, "클릭한 나라 :  ${listValues[position]}", Toast.LENGTH_SHORT).show()
        }

        /*
          아래에 수동으로 작성한 이 부분을 Controller로 만들서 사용하기
        val values = arrayListOf<String>()
        values.add("UK")
        values.add("USA")
        values.add("Korea")
        values.add("Japan")
        values.add("France")
        values.add("Vietnam")
        values.add("UK")
        values.add("USA")
        values.add("Korea")
        values.add("Japan")
        values.add("France")
        values.add("Vietnam")
        values.add("UK")
        values.add("USA")
        values.add("Korea")
        values.add("Japan")
        values.add("France")
        values.add("Vietnam")
        values.add("UK")
        values.add("USA")
        values.add("Korea")
        values.add("Japan")
        values.add("France")
        values.add("Vietnam")
        setValues(values)*/
    }

    fun setValues(values: List<String>) {
        listValues.clear()
        listValues.addAll(values)
        retryButton.visibility = View.GONE
        loading.visibility = View.GONE
        listLayout.visibility = View.VISIBLE
        adapter!!.notifyDataSetChanged()
    }

    fun onRetry(view: View) {
        controller?.onRefresh()
        listLayout.visibility = View.GONE
        retryButton.visibility = View.GONE
        loading.visibility = View.VISIBLE
    }

    fun onError() {
        Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_SHORT).show()
        loading.visibility = View.GONE
        listLayout.visibility = View.GONE
        retryButton.visibility = View.VISIBLE
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, MVCActivity::class.java)
    }
}
