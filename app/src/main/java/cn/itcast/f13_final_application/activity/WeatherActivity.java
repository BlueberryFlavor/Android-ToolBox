package cn.itcast.f13_final_application.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.itcast.f13_final_application.Common;
import cn.itcast.f13_final_application.R;
import cn.itcast.f13_final_application.entity.CityInfo;
import cn.itcast.f13_final_application.entity.CityListResult;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {
    private Spinner spProvince;//省份
    private Spinner spCity;//用户选择的城市
    private Button btnWeather;//天气查询
    private Button btnLife;//生活指数查询

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        //初始化控件
        spProvince = findViewById(R.id.spinner_province);
        spCity = findViewById(R.id.spinner_city);
        btnWeather = findViewById(R.id.btn_weatherquery);
        btnLife = findViewById(R.id.btn_lifequery);

        //进入页面，默认先请求可查询的城市列表信息
        queryCityList();

    }

    /**
     * 该方法用于查询城市列表信息数据
     */
    private void queryCityList() {
        //网络请求
        //接口地址
        //请求方式：get/post
        //数据格式：json
        //请求参数：
        //get请求
        String path = Common.PATH_CITYLIST + "?key=" + Common.KEY_WEATHER;
        OkHttpClient client = new OkHttpClient();
        //请求
        Request request = new Request.Builder()
                .url(path)
                .build();
        Call call = client.newCall(request);//计划要做一个网络请求
        //真正的开始执行网络请求
        //同步的执行方式
        //Response response = call.execute();//执行网络请求
        //String result =  response.body().string();
        //Log.i("TAG",result);
        //异步的执行方式：okhttp会创建线程，并在线程中执行耗时任务
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(WeatherActivity.this, "请求失败:" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                //先回到主线程，然后再处理数据，到页面展示
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("TAG", result);
                        //将string类型json数据转成java对象
                        //json解析
                        CityListResult citylist = JSON.parseObject(result, CityListResult.class);
                        if (citylist.getError_code() == 0){
                            //查询成功，查询到了数据
                            //把查询到的数据设置到下拉列表中
                            setData2Spinner(citylist.getResult());
                        }else{
                            Toast.makeText(WeatherActivity.this, "获取城市列表失败:"+citylist.getReason(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    /**
     * 该方法用于将请求到的城市列表数据设置到下拉列表当中
     * @param result
     */
    private void setData2Spinner(List<CityInfo> result) {



        List<String> cityNameList = new ArrayList<>();
        for (CityInfo info : result){
            cityNameList.add(info.getDistrict());
        }
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.item_spinner,cityNameList);
        spCity.setAdapter(adapter);
    }
}