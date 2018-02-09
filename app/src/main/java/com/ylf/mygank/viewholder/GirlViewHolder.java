package com.ylf.mygank.viewholder;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.ylf.mygank.R;
import com.ylf.mygank.entity.Gank;

/**
 * Created by Longfei Yang on 2016/7/25.
 * Email yanglongfei@yuanchuangyun.com
 */
public class GirlViewHolder extends BaseViewHolder<Gank.Result> {
    private ImageView mImage;

    public GirlViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_girl);
        mImage = $(R.id.item_girl_image);
    }

    @Override
    public void setData(final Gank.Result data) {
        super.setData(data);
        Glide.with(getContext())
                .load(data.getUrl())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mImage);
    }

}
