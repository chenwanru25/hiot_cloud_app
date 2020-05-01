package com.huatec.hiot_cloud.test.networktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.huatec.hiot_cloud.R;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestRetrofitActivity extends AppCompatActivity {

    private static final String TAG = "TestRetrofitActivity";
    private Retrofit build;
    private Retrofit retrofit;
    private TestRetrofitService retrofitService;
    private TestRetrofitService service;
    private Retrofit retrofit1;
    private String loginCode;
    private Call<ResponseBody> call;
    private Call<ResponseBody> responseBodyCall;
    private Call<ResponseBody> responseBodyCall1;
    private Call<ResponseBody> call1;
    private UserBean userBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_retrofit);

       //创建retrofit和service对象
        createRetrofit();

        //百度
        Button btnTest = findViewById(R.id.btn_retrofit_enqueue);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();

            }
        });

        //登录
        Button btnLogin = findViewById(R.id.btn_retrofit_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login2("apptest", "abc123", "app");

            }
        });

        //用户信息
        Button btnUserInfo = findViewById(R.id.btn_retrofit_userinfo);
        btnUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserInfo("58d2e00d4def465d901394b67094dee4_21a61926f0a044c1b2b70017f174fd35_use");

            }
        });

        //修改邮箱
        Button btnUpdateEmail = findViewById(R.id.btn_retrofit_update_email);
        btnUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmail("58d2e00d4def465d901394b67094dee4_21a61926f0a044c1b2b70017f174fd35_use",
                        "testemail2@qq.com");

            }
        });

        //注册
        Button btnRegister = findViewById(R.id.btn_retrofit_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();

            }
        });
    }

    /**
     * 注册用户
     */
    private void register() {
        UserBean userBean = new UserBean();
        userBean.setUsername("apptest3");
        userBean.setEmail("apptest3@qq.com");
        userBean.setPassword("abc123");
        userBean.setUserType("1");
        Call<ResponseBody> call = service.register(userBean);
        callEnqueue(call);

    }

    /**
     * 修改邮箱
     * @param authorization
     * @param email
     */
    private void updateEmail(String authorization, String email) {
        Call<ResponseBody> call = service.updateEmail(authorization, email);
        callEnqueue(call);
    }

    /**
     * 获取用户信息
     * @param authorization
     */
    private void getUserInfo(String authorization) {
        Call<ResponseBody> call = service.getUserInfo(authorization);
        callEnqueue(call);

    }

    /**
     * 登录
     * @param userName
     * @param password
     * @param loginCode
     */
    private void login(String userName, String password, String loginCode) {
        Call<ResponseBody> call = service.login(userName, password, loginCode);
        callEnqueue(call);
    }


    /**
     * 另一种登录接口方式
     * @param userName
     * @param password
     * @param loginCode
     */
    private void login2(String userName, String password, String loginCode) {
        Call<ResponseBody> call = service.login(userName, password, loginCode);
        callEnqueue(call);
    }

    private void callEnqueue(Call<ResponseBody> call) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d(TAG, "onResponse: " + response.body().string());
                } catch (IOException e) {
                    Log.e(TAG, "onResponse: " + e.getMessage(), e);
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage(), t);

            }
        });
    }

    private void test() {
        Call<ResponseBody> call = service.test();
        callEnqueue(call);
    }

    private void createRetrofit(){

        retrofit = new Retrofit.Builder().baseUrl(TestRetrofitService.basUrl)
                .addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofit.create(TestRetrofitService.class);
    }
}
