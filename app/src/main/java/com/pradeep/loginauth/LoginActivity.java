package com.pradeep.loginauth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.pradeep.loginauth.Utility.CommonMethod;
import com.pradeep.loginauth.Utility.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements OnClickListener {

    JsonObject jsonObject;
    EditText edtEmail, edtPassword;
    Button btnLogin;
    private static final String TAG = "LoginActivity";
    ProgressDialog progressDialog;
    Context mContext;
    String isUserAlreadyLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setOnClick();
    }

    private void setOnClick() {
        btnLogin.setOnClickListener(this);
    }

    private void setDataOnJsonObject(String email, String password) {
        jsonObject = new JsonObject();
        jsonObject.addProperty("user", email);
        jsonObject.addProperty("secret", password);
        Log.e(TAG, "setDataOnJsonObject: " + jsonObject.toString());
    }


    private void init() {
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        progressDialog = new ProgressDialog(LoginActivity.this);
        mContext = LoginActivity.this;

        isUserAlreadyLogin = CommonMethod.getPrefsData(mContext, Constants.isLogin, "");
        if (isUserAlreadyLogin.equalsIgnoreCase("True")) {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            this.finish();
        }

    }

    private void loginUser() {

        ServiceInterface serviceInterface = APIClient.getClient().create(ServiceInterface.class);
        Call<JsonObject> loginResponseCall = serviceInterface.postData("application/json", "application/json", jsonObject);
        loginResponseCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                progressDialog.dismiss();
                Log.e(TAG, "onResponse: " + response.isSuccessful());
                if (response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Login Successful.", Toast.LENGTH_SHORT).show();
                    CommonMethod.setPrefsData(mContext, Constants.isLogin, "true");
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    finish();
                } else {
                    Toast.makeText(mContext, "Login error please check credentials", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                progressDialog.dismiss();
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(mContext, "Something went wrong please try again later.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                if (edtEmail.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(this, "Please enter user email.", Toast.LENGTH_SHORT).show();
                } else if (edtPassword.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog.setMessage("Please wait ... ");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    setDataOnJsonObject(edtEmail.getText().toString(), edtPassword.getText().toString());
                    loginUser();
                }
                break;
        }


    }
}
