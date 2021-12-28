package com.arash.altafi.loginsms.kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.arash.altafi.loginsms.R
import kotlinx.android.synthetic.main.activity_kotlin_register.*

class KotlinRegisterActivity : AppCompatActivity() {

    lateinit var phone: String
    lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_register)

        init()
    }

    private fun init() {

        btn_login_kotlin.setOnClickListener {
            if (edt_name_kotlin.text.trim().toString().isEmpty() || edt_login_kotlin.text.trim().toString().isEmpty()) {
                Toast.makeText(this, "لطفا نام و شماره خود را وارد کنید", Toast.LENGTH_LONG).show()
            }
            else {

                phone = edt_login_kotlin.text.trim().toString()
                name = edt_name_kotlin.text.trim().toString()

                if (phone.length != 11) {
                    Toast.makeText(this, "لطفا شماره خود را صحیح وارد کنید", Toast.LENGTH_LONG).show()
                }
                else {
                    startActivity(Intent(this, KotlinVerifyActivity::class.java).apply {
                        putExtra("phone", phone)
                        putExtra("name", name)
                    })
                }
            }
        }

    }

}