package com.arash.altafi.loginsms.java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import com.arash.altafi.loginsms.R;
import com.arash.altafi.loginsms.java.api.ApiClient;
import com.arash.altafi.loginsms.java.api.ApiService;
import com.arash.altafi.loginsms.java.models.ResponseVerifyJava;
import com.arash.altafi.loginsms.java.utils.SMSReceiver;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.mukesh.OtpView;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class JavaVerifyActivity extends AppCompatActivity implements SMSReceiver.OTPReceiveListener {

    private OtpView edtCode;
    private SMSReceiver smsReceiver;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);

        apiService = ApiClient.getRetrofit().create(ApiService.class);

        String phone = getIntent().getStringExtra("phone");
        String name = getIntent().getStringExtra("name");

        Log.e("ArashAltafiSample", "phone: " + phone);
        Log.e("ArashAltafiSample", "name: " + name);

        sendData(phone, name);
        init();
    }

    private void init() {
        bindViews();

        try {
            smsReceiver = new SMSReceiver();
            smsReceiver.setOTPListener(this);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(SmsRetriever.SMS_RETRIEVED_ACTION);
            this.registerReceiver(smsReceiver, intentFilter);
            SmsRetrieverClient client = SmsRetriever.getClient(this);
            Task<Void> task = client.startSmsRetriever();
            task.addOnSuccessListener(new OnSuccessListener() {
                @Override
                public void onSuccess(@NonNull Object o) {

                }
            });
            task.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void sendData(String phone, String name) {
        apiService.sendUser(phone, name)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<ResponseVerifyJava>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull ResponseVerifyJava responseRegisterJava) {
                        Toast.makeText(JavaVerifyActivity.this , responseRegisterJava.getMessage() , Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(JavaVerifyActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void bindViews() {
        edtCode = findViewById(R.id.edt_code_java);
    }

    @Override
    public void onOTPReceived(String opt) {

        Log.e("ArashAltafiSample", "otp: $otp");

        String codes = opt.replace("کد ورود شما :", "").trim();
        String codes1 = codes.replace("<#>", "").trim();
        String show = codes1.replace("تست واحد فنی آرش الطافی", "").trim();
        String ss = show.replace("h52G5YaIjaK", "").trim();

        Log.e("ArashAltafiSample", "codes is:" + codes);
        Log.e("ArashAltafiSample", "codes1 is:" + codes1);
        Log.e("ArashAltafiSample", "show is:" + show);
        Log.e("ArashAltafiSample", "ss is:" + ss);


        edtCode.setText(ss.trim());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(JavaVerifyActivity.this ,"ثبت نام با موفقیت انجام شد" , Toast.LENGTH_LONG).show();
//                finish();
            }
        }, 3000);

        if (smsReceiver != null) {
            unregisterReceiver(smsReceiver);
            smsReceiver = null;
        }
    }

    @Override
    public void onOTPTimeOut() {
        Toast.makeText(getApplicationContext(), "OTP Time Out", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onOTPReceivedError(String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (smsReceiver != null) {
            unregisterReceiver(smsReceiver);
        }
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
            compositeDisposable.clear();
        }
    }
}