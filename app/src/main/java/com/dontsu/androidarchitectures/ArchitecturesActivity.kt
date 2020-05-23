package com.dontsu.androidarchitectures

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dontsu.androidarchitectures.mvc.MVCActivity
import com.dontsu.androidarchitectures.mvp.MVPActivity
import com.dontsu.androidarchitectures.mvvm.MVVMActivity

class ArchitecturesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_architectrues)
    }

    fun onMVC(view: View) {
        startActivity(MVCActivity.newIntent(this))
    }

    fun onMVP(view: View) {
        startActivity(MVPActivity.newIntent(this))
    }

    fun onMVVM(view: View) {
        startActivity(MVVMActivity.newIntent(this))
    }

}
