package com.ylf.mygank.viewholder;

import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.ylf.mygank.R;
import com.ylf.mygank.entity.Gank;
import com.ylf.mygank.util.TimeUtil;


/**
 * Created by Longfei Yang on 2016/7/25.
 * Email yanglongfei@yuanchuangyun.com
 */
public class GankViewHolder extends BaseViewHolder<Gank.Result> {
    private TextView mTitle;
    private TextView mType;
    private TextView mWho;
    private TextView mTime;

    public GankViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_gank);
        mTitle = $(R.id.title);
        mType = $(R.id.type);
        mWho = $(R.id.who);
        mTime = $(R.id.time);
    }

    @Override
    public void setData(Gank.Result data) {
        super.setData(data);
        //标题
        mTitle.setText(data.getDesc());
        //干货类型
        mType.setText(data.getType());
        //根据干货类型动态替换干货图标
        if (data.getType().equals("Android")){
            setDrawableLeft(R.drawable.ic_android_black_24dp);
        }else if (data.getType().equals("iOS")){
            setDrawableLeft(R.drawable.ic_whatshot_black_24dp);
        }else {
            setDrawableLeft(R.drawable.ic_play_circle_filled_black_24dp);
        }
        //干货贡献者
        mWho.setText(data.getWho());
        //干货提交时间
        mTime.setText(TimeUtil.getFormatTime(data.getPublishedAt()));
    }

    public void setDrawableLeft(int imageId){
        Drawable drawable = getContext().getResources().getDrawable(imageId);
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        mType.setCompoundDrawables(drawable,null,null,null);
    }

}
