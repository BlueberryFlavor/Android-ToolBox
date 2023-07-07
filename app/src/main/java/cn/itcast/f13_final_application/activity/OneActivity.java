package cn.itcast.f13_final_application.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.List;

import cn.itcast.f13_final_application.Common;
import cn.itcast.f13_final_application.R;
import cn.itcast.f13_final_application.adapter.OneAdapter;
import cn.itcast.f13_final_application.entity.One;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OneActivity extends AppCompatActivity {
    //展示热门视频的listview控件
    private ListView lvOne;
    private TextView tvEmpty;
    private EditText etOnemsg;
    private Button btnOne;
    private List<One> data;
    private String message;

    public List<One> getData() {
        return data;
    }

    public void setData(List<One> data) {
        this.data = data;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        lvOne = findViewById(R.id.listview_one);//视频布局の列表
        tvEmpty = findViewById(R.id.tv_empty);//视频布局の提示框
        etOnemsg=findViewById(R.id.et_onemsg);
        btnOne=findViewById(R.id.btn_One);

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                queryOne();
                String message=etOnemsg.getText().toString();
                return;

            }
        });
//        //为ListView列表添加点击事件
//        lvHotVideos.setOnItemClickListener(new );
        //网络请求，请求热门视频数据
        queryOne();

    }

//    private void queryOne() {
//        String message=etOnemsg.getText().toString();
//        if ("".equals(etOnemsg)) {
//            Toast.makeText(this, "请输入手机号重试!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//    }
    /**
     * 该方法用于请求聚合数据网站的热门视频的数据
     */
    private void queryOne() {
        //拼接url
        String type="json";
        String path = Common.PATH_ONE + "/?msg="+message +"&TYPE+"+type;

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
                        One one = JSON.parseObject(result,One.class);
                        if(one.getCode() == 200){
                            //请求成功，获取热门视频数据集合

//                            List<One> oneList =one.getResult();
//                            List<NewsDetailInfo> infoList =   newsInfo.getData();
                            String img= one.getImg();
//                            String msg=one.msg;
                            String title=one.getTitle();
                            String content=one.getContent();
                            String time=one.getTime();
                        // TODO: 2022/6/15 进行界面展示
                            List<One> oneList=getData();
                            setAdapter(oneList);
                        }
                    }
                });
            }
        });
    }
//    private  void getData(){
//        message=etOnemsg.getText().toString().trim();
//    }

    private void setAdapter(List<One> oneInfoList) {//hotvideoinfo就是细节
        OneAdapter adapter = new OneAdapter(this,oneInfoList);
        lvOne.setAdapter(adapter);//设置适配器
    }

    /**
     * 该方法请求失败时执行，该方法在主线程中
     */
    private void requestFailure() {
        tvEmpty.setVisibility(View.VISIBLE);
        lvOne.setVisibility(View.GONE);
    }
}