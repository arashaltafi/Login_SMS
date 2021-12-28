package com.arash.altafi.loginsms.kotlin

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.arash.altafi.loginsms.R
import com.arash.altafi.loginsms.kotlin.utils.SMSReceiver
import com.google.android.gms.auth.api.phone.SmsRetriever
import kotlinx.android.synthetic.main.activity_kotlin.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class KotlinVerifyActivity : AppCompatActivity() , SMSReceiver.OTPReceiveListener{

    private val authViewModel : KotlinViewModel by viewModel()
    var phone : String ?= null
    var name : String ?= null
    private var smsReceiver : SMSReceiver ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        phone = intent.getStringExtra("phone")
        name  = intent.getStringExtra("name")

        authViewModel.registerUser(phone!! , name!!)

        Log.e("ArashAltafiSample", "phone: $phone")
        Log.e("ArashAltafiSample", "name: $name")

        init()
    }

    private fun init() {
        try
        {
            smsReceiver = SMSReceiver()
            smsReceiver!!.setOTPListener(this)
            val intentFilter = IntentFilter()
            intentFilter.addAction(SmsRetriever.SMS_RETRIEVED_ACTION)
            this.registerReceiver(smsReceiver , intentFilter)
            val client = SmsRetriever.getClient(this)
            val task = client.startSmsRetriever()
            task.addOnSuccessListener {

            }
            task.addOnFailureListener {

            }
        }
        catch (e : Exception)
        {
            e.printStackTrace()
        }
    }

    override fun onOTPReceived(otp: String?) {

        Log.e("ArashAltafiSample", "otp: $otp")


        val codes = otp!!.replace("کد ورود شما :" , "").trim()
        val codes1 = codes.replace("<#>" , "").trim()
        val show = codes1.replace("تست واحد فنی آرش الطافی" , "").trim()
        val ss = show.replace("h52G5YaIjaK","").trim()


        Log.e("ArashAltafiSample", "codes is: $codes")
        Log.e("ArashAltafiSample", "codes1 is: $codes1")
        Log.e("ArashAltafiSample", "show is: $show")
        Log.e("ArashAltafiSample", "ss is: $ss")

        edt_code_kotlin.setText(ss.toString().trim())

        val handler = Handler(Looper.myLooper()!!)
        handler.postDelayed(Runnable {
            Toast.makeText(this , "ثبت نام با موفقیت انجام شد" , Toast.LENGTH_LONG).show()
//            finish()
        } , 1000)

        if (smsReceiver != null)
        {
            unregisterReceiver(smsReceiver)
            smsReceiver = null
        }
    }

    override fun onOTPTimeOut() {
        Toast.makeText(this , "OTP Time Out" , Toast.LENGTH_SHORT).show()

    }

    override fun onOTPReceivedError(error: String?) {
        Toast.makeText(this , error , Toast.LENGTH_SHORT).show()

    }

    override fun onDestroy() {
        super.onDestroy()
        if (smsReceiver != null) {
            unregisterReceiver(smsReceiver)
        }
    }
}