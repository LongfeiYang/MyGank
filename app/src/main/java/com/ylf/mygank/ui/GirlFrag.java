package com.ylf.mygank.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ylf.mygank.R;
import com.ylf.mygank.adapter.GirlAdapter;
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
public class GirlFrag extends Fragment implements SwipeRefreshLayout.OnRefreshListener,RecyclerArrayAdapter.OnLoadMoreListener {
    private static final int LIST_PAGE_COUNT = 10;

    @BindView(R.id.stub_gank)
    ViewStub mViewStub;

    @BindView(R.id.girl_recycler_view)
    EasyRecyclerView mRecycleView;

    private String mTitle;
    private GirlAdapter mGirlAdapter;

    private int page = 1;
    private Handler mHandler = new Handler();

    public static Fragment newInstance(String title) {
        GirlFrag girlFrag = new GirlFrag();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        girlFrag.setArguments(bundle);
        return girlFrag;
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
        View view = inflater.inflate(R.layout.frag_girl, container, false);
        ButterKnife.bind(this, view);
        initializeViews();
        return view;
    }

    private void initializeViews() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(layoutManager);

        mGirlAdapter = new GirlAdapter(getContext());
        initAdapter(mGirlAdapter);

//        mRecycleView.setEmptyView(R.layout.list_empty);
//        mRecycleView.setErrorView(R.layout.list_error);
        mRecycleView.setRefreshListener(this);
        mRecycleView.setRefreshing(true);
        onRefresh();
    }

    private void initAdapter(final RecyclerArrayAdapter<Gank.Result> adapter) {
        mRecycleView.setAdapterWithProgress(adapter);

        adapter.setMore(R.layout.list_load_more, this);
        adapter.setNoMore(R.layout.list_no_more);
        adapter.setError(R.layout.list_error);

        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Gank.Result item = adapter.getItem(position);
                startActivity(GirlDetailAct.newIntent(getActivity(), item.getDesc(), item.getUrl()));
//                Snackbar.make(mRecycleView, item.getDesc(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mGirlAdapter.clear();
                getData("福利", LIST_PAGE_COUNT, 1);
                page = 2;
            }
        }, 1000);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getData("福利", LIST_PAGE_COUNT, page);
                page++;
            }
        }, 1000);
    }

    public void getData(String type, int count, int page) {
        APIClient.getGank(type, count, page, new APICallBack<List<Gank.Result>>() {
            @Override
            public void onSuccess(List<Gank.Result> list) {
                mGirlAdapter.addAll(list);
            }

            @Override
            public void onError(Throwable e) {
                mRecycleView.showError();
            }
        });
    }
}