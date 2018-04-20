package cn.framework.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import cn.framework.myandroidlibrary.adapter.MyBaseRecyclerAdapter;

/**
 * Created by xiaoxiong on 2018/1/4.
 * 描述:
 * 路径:
 */

public class MyTestAdapter extends MyBaseRecyclerAdapter {

    public MyTestAdapter(Context context, List<BaseItem> items) {
         super(context,items);
    }
    public MyTestAdapter(Context context, List<BaseItem> items ,boolean handEmpty) {
        super(context,items,handEmpty);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
    }


}
