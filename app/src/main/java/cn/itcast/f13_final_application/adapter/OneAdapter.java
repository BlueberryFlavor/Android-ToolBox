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
import cn.itcast.f13_final_application.entity.One;

/**
 * Author: hongweiyu
 * Date: 2022/6/15
 * Email: yuxinburendavie@gmail.com
 * Description :适配器负责管理listview列表控件的数据，用来控制数据和视图如何展示
 */
public class OneAdapter extends BaseAdapter {
    private Activity context;//上下文
    //要操作和展示的热门视频的数据
    private List<One> OneInfoList;

    /**
     * 构造方法
     */
    public OneAdapter(Activity context, List<One> list) {
        this.context = context;
        this.OneInfoList = list;
    }

    /**
     * 该方法用于返回一共有多少个要展示的条目的个数
     *
     * @return
     */
    @Override
    public int getCount() {
        return OneInfoList.size();
    }

    /**
     * 该方法返回的是每一个条目
     *
     * @param position
     * @return
     */
    @Override
    public One getItem(int position) {
        return OneInfoList.get(position);
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
        View itemView = inflater.inflate(R.layout.item_one, viewGroup, false);
        //② 利用布局找到布局中的控件
        TextView tvOneTitle = itemView.findViewById(R.id.tv_onetitle);
        TextView tvOneContent = itemView.findViewById(R.id.tv_onecontent);
        TextView tvOneSource = itemView.findViewById(R.id.tv_onesource);
        TextView tvOneTime = itemView.findViewById(R.id.tv_onetime);

        ImageView imgOne = itemView.findViewById(R.id.img_one);

        //③ 确定要展示的数据
        One one = OneInfoList.get(position);
        //④ 将数据设置到对应的控件上
        tvOneTitle.setText(one.getTitle()+"");
        tvOneContent.setText(one.getContent()+"");
        tvOneSource.setText(one.getSource()+"");
        tvOneTime.setText(one.getTime()+"");

        //新闻封面的图片的链接
        String cover_url = one.getImg();
        //把资源通过网络获取到本地，然后设置到控件上
        Picasso.with(context).load(cover_url).into(imgOne);
        return itemView;
    }
}
