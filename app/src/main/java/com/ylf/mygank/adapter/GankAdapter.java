package com.ylf.mygank.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ylf.mygank.entity.Gank;
import com.ylf.mygank.viewholder.GankViewHolder;

/**
 * Created by Longfei Yang on 2016/7/25.
 * Email yanglongfei@yuanchuangyun.com
 */
public class GankAdapter extends RecyclerArrayAdapter<Gank.Result> {

    public GankAdapter(Context context){
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new GankViewHolder(parent);
    }
}
