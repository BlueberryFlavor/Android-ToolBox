package cn.itcast.f13_final_application.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import cn.itcast.f13_final_application.R;

public class MenuActivity extends AppCompatActivity {
//    private FrameLayout flContainer;
//    private BottomNavigationBar bottomNavigationBar;

    private Button btnPhone;
    private Button btnWeatherLife;
    private Button btnHotVideos;
    private Button btnNews;
    private Button btnOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnPhone = findViewById(R.id.btn_phone);
        btnWeatherLife = findViewById(R.id.btn_weatherlife);
        btnHotVideos = findViewById(R.id.btn_hotvideo);
        btnNews = findViewById(R.id.btn_news);
        btnOne = findViewById(R.id.btn_One);

        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, PhoneQueryActivity.class);
                startActivity(intent);
            }
        });

        btnWeatherLife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, WeatherActivity.class);
                startActivity(intent);
            }
        });

        btnHotVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, VideosActivity.class);
                startActivity(intent);
            }
        });

        btnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, NewsActivity.class);
                startActivity(intent);
            }
        });

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, OneActivity.class);
                startActivity(intent);
            }
        });

    }
//        flContainer=findViewById(R.id.fl_container);
//        bottomNavigationBar=findViewById(R.id.bottom_navigation_bar);
//        //给底部导航栏设置 数据项（自己的项目中有几个导航栏）
//        initBottombar();
//    }
//
//    private void initBottombar() {
//        BottomNavigationItem item=new BottomNavigationItem(R.mipmap.home,"首页");
//        bottomNavigationBar.addItem(item_home);
//
//        BottomNavigationItem item=new BottomNavigationItem(R.mipmap.weather,"天气");
//        bottomNavigationBar.addItem(item_weather);
//
//        BottomNavigationItem item=new BottomNavigationItem(R.mipmap.video,"视频");
//        bottomNavigationBar.addItem(item_video);
//
//        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(int position) {
//                Log.i("TAG","onTabSelected",)
//            }
//
//            @Override
//            public void onTabUnselected(int position) {
//            }
//
//            @Override
//            public void onTabReselected(int position) {
//            }
//        });
//    }
//
//}//🔚—END—
}