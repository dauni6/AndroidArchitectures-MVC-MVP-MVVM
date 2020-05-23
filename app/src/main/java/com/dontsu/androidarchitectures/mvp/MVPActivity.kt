package com.dontsu.androidarchitectures.mvp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dontsu.androidarchitectures.R

class MVPActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_m_v_p)
    }

    companion object {
        fun newIntent(context: Context) =Intent(context, MVPActivity::class.java)
    }
}
