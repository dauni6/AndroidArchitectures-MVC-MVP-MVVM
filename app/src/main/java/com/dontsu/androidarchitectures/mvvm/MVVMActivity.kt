package com.dontsu.androidarchitectures.mvvm

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dontsu.androidarchitectures.R
/**
 * MVVM(Model - View - ViewModel)
 * **/
class MVVMActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_m_v_v_m)
        title = "MVVM Activity"
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, MVVMActivity::class.java)
    }
}
