package com.example.asm_nguyenconganhduy_ps25332;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class login extends AppCompatActivity {
    EditText txtuser,txtpass;
    Button btnlogin;
    TextView validate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtuser = findViewById(R.id.edtuser);
        txtpass = findViewById(R.id.edtpass);
        validate = findViewById(R.id.validate);
        btnlogin = findViewById(R.id.btn1);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = txtuser.getText().toString();
                String pass = txtpass.getText().toString();
                if(user.equals("admin")&&pass.equals("123")){
                    startActivity(new Intent(login.this,MainActivity.class));
                }else{
                    validate.setText("Mời bạn nhập lại");
                }
            }
        });
    }

}