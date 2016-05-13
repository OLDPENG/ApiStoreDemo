package com.yuxuan.apistoredemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private EditText etCity;
    private Button btGetContent;
    private TextView tvContent;

    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_main);

        gson = new Gson();

        etCity = (EditText) findViewById(R.id.etCity);
        btGetContent = (Button) findViewById(R.id.btGetContent);
        tvContent = (TextView) findViewById(R.id.tvContent);

        btGetContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = etCity.getText().toString();
                if (TextUtils.isEmpty(city)) {
                    Toast.makeText(MainActivity.this, "请先输入需要查询的城市", Toast.LENGTH_SHORT).show();
                } else {
                    tvContent.setText("正在查询......");
                    getData(city);
                }
            }
        });
    }

    public void getData(String city) {
        Parameters para = new Parameters();
        para.put("city", city);
        ApiStoreSDK.execute(
                "http://apis.baidu.com/heweather/weather/free",
                ApiStoreSDK.GET,
                para,
                new ApiCallBack() {
                    @Override
                    //请求成功时调用
                    public void onSuccess(int status, String responseString) {
                        Toast.makeText(MainActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
                        tvContent.setText(responseString);

                    }

                    @Override
                    //请求失败时调用
                    public void onError(int status, String responseString, Exception e) {
                        tvContent.setText("查询失败");
                    }

                    @Override
                    //总是会调用
                    public void onComplete() {

                    }
                }
        );
    }
}
