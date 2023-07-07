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
import cn.itcast.f13_final_application.entity.HotVideoInfo;

/**
 * Author: hongweiyu
 * Date: 2022/6/15
 * Email: yuxinburendavie@gmail.com
 * Description :适配器负责管理listview列表控件的数据，用来控制数据和视图如何展示
 */
public class HotVideoAdapter extends BaseAdapter {
    private Activity context;//上下文
    //要操作和展示的热门视频的数据
    private List<HotVideoInfo> videoInfoList;

    /**
     * 构造方法
     */
    public HotVideoAdapter(Activity context, List<HotVideoInfo> list) {
        this.context = context;
        this.videoInfoList = list;
    }

    /**
     * 该方法用于返回一共有多少个要展示的条目的个数
     *
     * @return
     */
    @Override
    public int getCount() {
        return videoInfoList.size();
    }

    /**
     * 该方法返回的是每一个条目
     *
     * @param position
     * @return
     */
    @Override
    public HotVideoInfo getItem(int position) {
        return videoInfoList.get(position);
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
        //① 先把要展示的布局拿到
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_hotvideos, viewGroup, false);
        //② 利用布局找到布局中的控件
        TextView tvTitle = itemView.findViewById(R.id.tv_title);
        TextView tvHotValue = itemView.findViewById(R.id.tv_hotvalue);
        TextView tvDigg = itemView.findViewById(R.id.tv_digg);
        TextView tvComment = itemView.findViewById(R.id.tv_comment);
        ImageView imgConver = itemView.findViewById(R.id.img_cover);

        //③ 确定要展示的数据
        HotVideoInfo videoInfo = videoInfoList.get(position);
        //④ 将数据设置到对应的控件上
        tvTitle.setText(videoInfo.getTitle());
        tvHotValue.setText(videoInfo.getHot_value()+"");
        tvDigg.setText(videoInfo.getDigg_count()+"");
        tvComment.setText(videoInfo.getComment_count()+"");


        //新闻封面的图片的链接
        String cover_url = videoInfo.getItem_cover();
        //把资源通过网络获取到本地，然后设置到控件上
        Picasso.with(context).load(cover_url).into(imgConver);
        return itemView;
    }
}
