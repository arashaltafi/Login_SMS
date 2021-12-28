package com.arash.altafi.loginsms.java;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.arash.altafi.loginsms.R;
import com.google.android.material.button.MaterialButton;

public class JavaRegisterActivity extends AppCompatActivity {

    private EditText edtPhone;
    private EditText edtName;
    private MaterialButton btnLogin;
    String phone;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_register);

        bindViews();
        init();
    }

    private void init() {

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtName.getText().toString().trim().isEmpty() || edtPhone.getText().toString().trim().isEmpty()) {
                    Toast.makeText(JavaRegisterActivity.this, "لطفا نام و شماره خود را وارد کنید", Toast.LENGTH_LONG).show();
                }
                else {

                    phone = edtPhone.getText().toString().trim();
                    name = edtName.getText().toString().trim();

                    if (phone.length() != 11) {
                        Toast.makeText(JavaRegisterActivity.this, "لطفا شماره خود را صحیح وارد کنید", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Intent intent = new Intent(JavaRegisterActivity.this , JavaVerifyActivity.class);
                        intent.putExtra("phone" , phone);
                        intent.putExtra("name" , name);
                        startActivity(intent);
                    }
                }
            }
        });

    }

    private void bindViews() {
        btnLogin = findViewById(R.id.btn_login_java);
        edtPhone = findViewById(R.id.edt_login_java);
        edtName = findViewById(R.id.edt_name_java);
    }

}