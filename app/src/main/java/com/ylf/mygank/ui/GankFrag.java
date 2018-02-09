package com.ylf.mygank.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ylf.mygank.R;
import com.ylf.mygank.adapter.GankAdapter;
import com.ylf.mygank.api.APICallBack;
import com.ylf.mygank.api.APIClient;
import com.ylf.mygank.entity.Gank;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Longfei Yang on 2016/7/25.
 * Email yanglongfei@yuanchuangyun.com
 */
public class GankFrag extends Fragment implements SwipeRefreshLayout.OnRefreshListener,RecyclerArrayAdapter.OnLoadMoreListener{
    private static final int LIST_PAGE_COUNT = 10;

    @BindView(R.id.stub_gank)
    ViewStub mViewStub;
    @BindView(R.id.gank_recycler_view) EasyRecyclerView mRecycleView;

    private String mTitle;
    private GankAdapter mGankAdapter;

    private int page = 1;
    private Handler mHandler = new Handler();

    public static Fragment newInstance(String title){
        GankFrag gankFrag = new GankFrag();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        gankFrag.setArguments(bundle);
        return gankFrag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mTitle = bundle.getString("title");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_gank, container, false);
        ButterKnife.bind(this, view);
        initializeViews();
        return view;
    }

    private void initializeViews(){
        mRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));

        mGankAdapter = new GankAdapter(getContext());
        initAdapter(mGankAdapter);

//        mRecycleView.setEmptyView(R.layout.list_empty);
//        mRecycleView.setErrorView(R.layout.list_error);
        mRecycleView.setRefreshListener(this);
        mRecycleView.setRefreshing(true);
        onRefresh();
    }

    private void initAdapter(final RecyclerArrayAdapter<Gank.Result> adapter){
        mRecycleView.setAdapterWithProgress(adapter);

        adapter.setMore(R.layout.list_load_more, this);
        adapter.setNoMore(R.layout.list_no_more);
        adapter.setError(R.layout.list_error);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Gank.Result item = adapter.getItem(position);
                startActivity(GankDetailAct.newIntent(getActivity(), item.getDesc(), item.getUrl()));
            }
        });
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mGankAdapter.clear();
                getData(mTitle, LIST_PAGE_COUNT, 1);
                page = 2;
            }
        }, 1000);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getData(mTitle, LIST_PAGE_COUNT, page);
                page++;
            }
        }, 1000);
    }

    public void getData(String type, int count, int page){
        APIClient.getGank(type, count, page, new APICallBack<List<Gank.Result>>() {
            @Override
            public void onSuccess(List<Gank.Result> list) {
                mGankAdapter.addAll(list);
            }

            @Override
            public void onError(Throwable e) {
                mRecycleView.showError();
            }
        });
    }

}
