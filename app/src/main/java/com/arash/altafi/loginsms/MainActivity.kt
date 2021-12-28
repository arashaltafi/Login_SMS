package com.arash.altafi.loginsms

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arash.altafi.loginsms.java.JavaRegisterActivity
import com.arash.altafi.loginsms.kotlin.KotlinRegisterActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {

        btn_java.setOnClickListener {
            startActivity(Intent(this, JavaRegisterActivity::class.java))
        }

        btn_kotlin.setOnClickListener {
            startActivity(Intent(this, KotlinRegisterActivity::class.java))
        }

    }
}