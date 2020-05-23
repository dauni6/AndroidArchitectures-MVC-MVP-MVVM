package com.dontsu.androidarchitectures.mvc

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dontsu.androidarchitectures.R
/**
 * MVC(Model - View - Controller)
 * **/
class MVCActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_m_v_c)
        title = "MVC Activity"
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, MVCActivity::class.java)
    }
}
