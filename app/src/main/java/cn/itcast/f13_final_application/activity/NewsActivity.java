package cn.itcast.f13_final_application.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.List;

import cn.itcast.f13_final_application.Common;
import cn.itcast.f13_final_application.R;
import cn.itcast.f13_final_application.adapter.NewsAdapter;
import cn.itcast.f13_final_application.entity.NewsDetailInfo;
import cn.itcast.f13_final_application.entity.NewsInfo;
import cn.itcast.f13_final_application.entity.NewsResult;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


//1.newsResult
//2.newsInfo
//3.newsDetailInfo
public class NewsActivity extends AppCompatActivity {
    //展示热门视频的listview控件
    private ListView lvNews;
    private TextView tvEmpty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        lvNews = findViewById(R.id.listview_news);//视频布局の列表
        tvEmpty = findViewById(R.id.tv_empty);//视频布局の提示框

//        //为ListView列表添加点击事件
//        lvHotVideos.setOnItemClickListener(new );

        //网络请求，请求热门视频数据
        queryNews();

    }

    /**
     * 该方法用于请求聚合数据网站的热门视频的数据
     */
    private void queryNews() {
        //拼接url
        String path = Common.PATH_NEWS + "?type=top&key=" + Common.KEY_NEWS;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(path)
                .build();

        Call call = client.newCall(request);
        //执行网络请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        requestFailure();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();//json格式数据
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        NewsResult NewsResult = JSON.parseObject(result, NewsResult.class);
                        if(NewsResult.getError_code() == 0){
                            //请求成功，获取热门视频数据集合
                            NewsInfo newsInfo = NewsResult.getResult();
                            List<NewsDetailInfo> infoList =   newsInfo.getData();
                            setAdapter(infoList);
                        }
                    }
                });
            }
        });
    }

    private void setAdapter(List<NewsDetailInfo> NewsDetailInfo) {
        NewsAdapter adapter = new NewsAdapter(this, NewsDetailInfo);
        lvNews.setAdapter(adapter);//设置适配器
    }

    /**
     * 该方法请求失败时执行，该方法在主线程中
     */
    private void requestFailure() {
        tvEmpty.setVisibility(View.VISIBLE);
        lvNews.setVisibility(View.GONE);
    }
}











