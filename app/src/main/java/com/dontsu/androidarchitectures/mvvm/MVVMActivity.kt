package com.dontsu.androidarchitectures.mvvm

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dontsu.androidarchitectures.R

class MVVMActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_m_v_v_m)
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, MVVMActivity::class.java)
    }
}
