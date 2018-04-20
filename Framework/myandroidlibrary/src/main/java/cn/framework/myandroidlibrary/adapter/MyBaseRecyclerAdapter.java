package cn.framework.myandroidlibrary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.framework.myandroidlibrary.R;


public abstract class MyBaseRecyclerAdapter<T extends MyBaseRecyclerAdapter.BaseItem> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    protected List<BaseItem> items;
    protected Context context;
    private String emptyTip;

    public MyBaseRecyclerAdapter(Context context, List<BaseItem> items) {
        this.items = items;
        this.context = context;
    }

    public MyBaseRecyclerAdapter(Context context, List<BaseItem> items, boolean handleEmpty) {
        this.items = items;
        this.context = context;
        if (handleEmpty) {
            addEmptyItem();
        }
    }

    private void addEmptyItem() {
        if (items == null || items.size() == 0) {
            if (items == null) {
                items = new ArrayList<BaseItem>();
            }
            BaseItem item = new BaseItem();
            item.setItemType(BaseItem.ITEM_TYPE_EMPTY);
            items.add(item);
        }
    }

    public void setItems(List<BaseItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void setItemsWithNoNotify(List<BaseItem> items) {
        this.items = items;
    }

    public void addItems(List<BaseItem> items) {
        if (this.items == null) {
            this.items = items;
        } else {
            this.items.addAll(items);
        }
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getItemType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BaseItem.ITEM_TYPE_EMPTY){
            View view=   getEmptyItem(parent);
            return new ListNullViewHolder(view);
        }
     /*   if (viewType == BaseItem.ITEM_TYPE_TOP) {

        } else if (viewType == BaseItem.ITEM_TYPE_BOTTOM){

        } else if (viewType == BaseItem.ITEM_TYPE_EMPTY){
             View view=   getEmptyItem(parent);
            return new ListNullViewHolder(view);
        } else if (viewType == BaseItem.ITEM_TYPE_DEFAULT){

        }*/
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ListNullViewHolder){
            bindNullViewHolder((ListNullViewHolder) holder,null);
        }
    }

    protected View getEmptyItem(ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_listview_empty, parent, false);
        return view;
    }


/*    public RecyclerView.ViewHolder onAddTopItem(ViewGroup parent) {
        return null;
    }

    public RecyclerView.ViewHolder onAddBottomItem(ViewGroup parent) {
        return null;
    }*/

/*    private void addTopItem(ViewGroup parent) {
        topItemViewHolder = onAddTopItem(parent);
        if (topItemViewHolder == null) {
            return;
        }
        BaseItem item = new BaseItem();
        item.setItemType(BaseItem.ITEM_TYPE_TOP);
        items.add(0, item);
    }

    private void addBottomItem(ViewGroup parent) {
         bottomItemViewHolder = onAddTopItem(parent);
        if (bottomItemViewHolder == null) {
            return;
        }
        BaseItem item = new BaseItem();
        item.setItemType(BaseItem.ITEM_TYPE_BOTTOM);
        items.add(item);
    }*/

    public static class BaseItem {
        private static int ITEM_TYPE_EMPTY = 0;//空的item
        private static int ITEM_TYPE_TOP = 1;//自定义顶部item
        private static int ITEM_TYPE_BOTTOM = 2;
        private static int ITEM_TYPE_DEFAULT = 3;

        private int itemType;

        public int getItemType() {
            return itemType;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }
    }

    public static  class ListNullViewHolder extends RecyclerView.ViewHolder {
        public TextView tipView;
        public ListNullViewHolder(View view){
            super(view);
            tipView = (TextView) view.findViewById(R.id.tip);
        }
    }

    private void bindNullViewHolder(ListNullViewHolder viewHolder, BaseItem item) {
        viewHolder.tipView.setVisibility(View.VISIBLE);
        if(emptyTip!=null){
            viewHolder.tipView.setText(emptyTip);
            return;
        }
        viewHolder.tipView.setText("暂无数据");
    }
}
