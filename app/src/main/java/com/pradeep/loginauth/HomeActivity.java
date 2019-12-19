package com.pradeep.loginauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pradeep.loginauth.Utility.CommonMethod;
import com.pradeep.loginauth.Utility.Constants;

public class HomeActivity extends AppCompatActivity {

    Button btnLogout;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
    }

    private void init() {
        btnLogout = findViewById(R.id.btnLogout);
        mContext = HomeActivity.this;
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonMethod.setPrefsData(mContext, Constants.isLogin, "false");
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
}
