package cn.itcast.f13_final_application.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cn.itcast.f13_final_application.Common;
import cn.itcast.f13_final_application.R;
import cn.itcast.f13_final_application.entity.PhoneDetailInfo;
import cn.itcast.f13_final_application.entity.PhoneQueryResult;

public class TwoActivity extends AppCompatActivity {
//空气质量指数
    //手机号
    private EditText etPhone;
    //查询按钮
    private Button btnQuery;

    private TextView tvCity;
    private TextView tvCompany;

    private String result = "";//result变量表示存储获取到的所有数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPhone = findViewById(R.id.et_phone);
        btnQuery = findViewById(R.id.btn_query);
        tvCity = findViewById(R.id.tv_city);
        tvCompany = findViewById(R.id.tv_company);
        //设置按钮的监听事件
        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //当用户点击查询按钮时，程序会指定该方法
                query();//执行网络请求，查询手机号信息
            }
        });
    }

    //接口：数据接口，是用来描述网络资源的存储位置，是一个url
    //电脑:
    //C:、D:、E:、F:
    //文件路径：E://liuyishou/kejian/数据结构.pptx
    //常量

    /**
     * 该方法通过网络请求，查询用户输入的手机号的相关信息
     */
    public void query() {
        //① 获取到用户界面的输入
        String phoneNumStr = etPhone.getText().toString();
        //② 判断用户的输入内容，如果内容为空，提示用户重新输入，不需要查询
        if ("".equals(phoneNumStr)) {
            Toast.makeText(this, "请输入手机号重试!", Toast.LENGTH_SHORT).show();
            return;
        }
        //③ 如果用户输入的内容不为空，则发起网络请求
        //补充：Android应用中，主线程又称UI线程，是负责和用户交互的线程，在UI线程当中不能执行
        //耗时操作，否则会报错：界面无响应
        //结论：网络请求属于耗时操作，Android中耗时操作需要放在子线程当中执行
        new Thread(new Runnable() {
            @Override
            public void run() {
                //子线程中
                //开始请求
                try {
                    String path = Common.PATH_TWO + "?key="+ Common.KEY_TWO+"area=上海";
                    System.out.println(path);
                    URL url = new URL(path);
                    //将url转换为http协议
                    HttpURLConnection httpconn = (HttpURLConnection) url.openConnection();
                    //请求的设置
                    //① 设置请求类型
                    httpconn.setRequestMethod("GET");
                    //② 设置请求格式json
                    //Content-Type
                    httpconn.setRequestProperty("Content-Type", "application/json");
                    //③ 开始连接
                    httpconn.connect();

                    //④ 接收网络请求的结果
                    int code = httpconn.getResponseCode();
                    if (code != 200) {
                        //网络请求不正常，通过请求状态码可以判断
                        //常见的网络请求状态码有：200，404，500
                        //请求失败:提示用户，请求失败，请重试
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //通过runOnUiThread方法，程序又重新回到了UI线程
                                Toast.makeText(TwoActivity.this, "请求失败，请重试", Toast.LENGTH_SHORT).show();
                            }
                        });
                        return;//程序直接结束
                    }
                    //读取请求结果
                    InputStream input = httpconn.getInputStream();//管道(传数据）
                    byte[] buffer = new byte[1024];
                    int len = 0;

                    while ((len = input.read(buffer)) != -1) {
                        //只要len !=-1，就表示从管道中读取到了数据
                        result += new String(buffer, 0, len);
                    }

                    //⑤ 把请求的结果数据展示到界面上，展示给用户
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.i("TAG", result);
                            //将获取到的数据转换为Java对象
                            /**
                             * 第一个参数表示，要解析的源数据是什么
                             * 第二个参数表示，要将数据转换成什么类型的对象
                             */
                            PhoneQueryResult phoneResult = JSON.parseObject(result, PhoneQueryResult.class);
                            PhoneDetailInfo phoneInfo = phoneResult.getResult();

                            Log.i("TAG", phoneResult.getReason());
                            if (phoneInfo != null) {
                                //北京，联通，010
                                String city = phoneInfo.getCity();
                                String company = phoneInfo.getCompany();
                                tvCity.setText(city);
                                tvCompany.setText(company);
                            }
                        }
                    });
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}