package cn.itcast.f13_final_application.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cn.itcast.f13_final_application.R;
import cn.itcast.f13_final_application.entity.NewsDetailInfo;

/**
 * Author: hongweiyu
 * Date: 2022/6/15
 * Email: yuxinburendavie@gmail.com
 * Description :适配器负责管理listview列表控件的数据，用来控制数据和视图如何展示
 */
public class NewsAdapter extends BaseAdapter {
    private Activity context;//上下文
    //要操作和展示的热门视频的数据
    private List<NewsDetailInfo> NewsInfoList;

    /**
     * 构造方法
     */
    public NewsAdapter(Activity context, List<NewsDetailInfo> list) {
        this.context = context;
        this.NewsInfoList = list;
    }

    /**
     * 该方法用于返回一共有多少个要展示的条目的个数
     *
     * @return
     */
    @Override
    public int getCount() {
        return NewsInfoList.size();
    }

    /**
     * 该方法返回的是每一个条目
     *
     * @param position
     * @return
     */
    @Override
    public NewsDetailInfo getItem(int position) {
        return NewsInfoList.get(position);
    }

    /**
     * 该方法用于返回每一个条目的编号id
     * (每一个条目都应该有唯一的id，用以和其他条目进行区分)
     *
     * @param positon
     * @return
     */
    @Override
    public long getItemId(int positon) {
        return positon;
    }

    /**
     * 该方法用于在每个条目展示时都会调用
     *
     * @param position
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        //view单个控件、viewgroup控件容器大视图、webview加载详情页面
        //① 先把要展示的布局拿到
        LayoutInflater inflater = LayoutInflater.from(context);//引擎：提供动力，加载文件【布局泵】
        View itemView = inflater.inflate(R.layout.item_news, viewGroup, false);
        //② 利用布局找到布局中的控件
        TextView tvNewsTitle = itemView.findViewById(R.id.tv_newstitle);//id只与item_news有关
        TextView tvNewsDate = itemView.findViewById(R.id.tv_newsdate);
        TextView tvNewsUrl = itemView.findViewById(R.id.tv_newsurl);
        TextView tvNewsAuthor=itemView.findViewById(R.id.tv_newsauthorname);
        ImageView imgConver = itemView.findViewById(R.id.img_newscover);
        TextView tvNewsImage=itemView.findViewById(R.id.tv_newsImage);


//        TextView tvComment = itemView.findViewById(R.id.tv_newscomment);

        //③ 确定要展示的数据
        NewsDetailInfo NewsInfo = NewsInfoList.get(position);
        //④ 将数据设置到对应的控件上
        tvNewsTitle.setText(NewsInfo.getTitle());

        tvNewsDate.setText(NewsInfo.getDate()+"");

        tvNewsUrl.setText(NewsInfo.getUrl()+"");

        tvNewsAuthor.setText(NewsInfo.getAuthor_name()+"");

        tvNewsImage.setText(NewsInfo.getThumbnail_pic_s02()+"");



//        tvComment.setText(NewsInfo.getCategory()+"");

        //新闻封面的图片的链接
        String cover_url = NewsInfo.getThumbnail_pic_s();

        //把资源通过网络获取到本地，然后设置到控件上
        Picasso.with(context).load(cover_url).into(imgConver);

        return itemView;
    }
}
