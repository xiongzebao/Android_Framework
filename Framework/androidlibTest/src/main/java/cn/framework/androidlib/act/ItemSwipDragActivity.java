package cn.framework.androidlib.act;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.framework.androidlib.R;
import cn.framework.androidlib.data.Status;
import cn.framework.myandroidlibrary.act.BaseActivity;


interface ItemTouchHelperAdapter {
    //数据交换
    void onItemMove(int fromPosition, int toPosition);

    //数据删除
    void onItemDissmiss(int position);
}

class TestAdapter extends BaseQuickAdapter<Status, BaseViewHolder> implements ItemTouchHelperAdapter {
    public TestAdapter(List<Status> data) {
        super(R.layout.layout_animation, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Status item) {
        helper.setText(R.id.tweetText, item.getText());
        helper.setText(R.id.tweetName, item.getUserName());
        //helper.addOnClickListener(R.id.delete);
        helper.getView(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( mContext,"delete",Toast.LENGTH_SHORT).show();
            }
        });

        helper.getView(R.id.content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( mContext,"content",Toast.LENGTH_SHORT).show();
            }
        });

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( mContext,"item click",Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mData, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDissmiss(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }
}

class SimpleTouchHelperCallback extends ItemTouchHelper.Callback {

    private ItemTouchHelperAdapter mAdapter;

    public SimpleTouchHelperCallback(ItemTouchHelperAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.LEFT;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
      //  mAdapter.onItemDissmiss(viewHolder.getAdapterPosition());
    }

    //限制ImageView长度所能增加的最大值
    private double ICON_MAX_SIZE = 50;
    //ImageView的初始长宽
    private int fixedWidth = 150;

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        //重置改变，防止由于复用而导致的显示问题
        viewHolder.itemView.setScrollX(0);
        BaseViewHolder holder = (BaseViewHolder)viewHolder;
      //  View delBtn= holder.getView(R.id.delBtn);
   /*     FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) delBtn.getLayoutParams();
        params.width = 150;
        params.height = 150;
        delBtn.setLayoutParams(params);*/

       // holder.getView(R.id.delBtn).setVisibility(View.INVISIBLE);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        //仅对侧滑状态下的效果做出改变
      /*  if (actionState ==ItemTouchHelper.ACTION_STATE_SWIPE){
            //如果dX小于等于删除方块的宽度，那么我们把该方块滑出来
            if (Math.abs(dX) <= getSlideLimitation(viewHolder)){
                viewHolder.itemView.scrollTo(-(int) dX,0);
            }
            //如果dX还未达到能删除的距离，此时慢慢增加“眼睛”的大小，增加的最大值为ICON_MAX_SIZE
            else if (Math.abs(dX) <= recyclerView.getWidth() / 2){
                double distance = (recyclerView.getWidth() / 2 -getSlideLimitation(viewHolder));
                double factor = ICON_MAX_SIZE / distance;
                double diff =  (Math.abs(dX) - getSlideLimitation(viewHolder)) * factor;
                if (diff >= ICON_MAX_SIZE)
                    diff = ICON_MAX_SIZE;
                BaseViewHolder holder = (BaseViewHolder)viewHolder;
              //  View delBtn= holder.getView(R.id.delBtn);
             *//*   FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)  delBtn.getLayoutParams();
                params.width = (int) (fixedWidth + diff);
                params.height = (int) (fixedWidth + diff);
                delBtn.setLayoutParams(params);*//*

            }
        }else {
            //拖拽状态下不做改变，需要调用父类的方法
          //  super.onChildDraw(c,recyclerView,viewHolder,dX,dY,actionState,isCurrentlyActive);
        }*/
    }

    /**
     * 获取删除方块的宽度
     */
    public int getSlideLimitation(RecyclerView.ViewHolder viewHolder){
        ViewGroup viewGroup = (ViewGroup) viewHolder.itemView;
        int width = viewGroup.getChildAt(1).getLayoutParams().width;
        return  width;
    }
}

public class ItemSwipDragActivity extends BaseActivity {


    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_itemswipdrag_layout);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ArrayList<Status> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Status(""+i, "iii"+i));
        }
        TestAdapter adapter = new TestAdapter(list);

      adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
          @Override
          public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
              Toast.makeText(ItemSwipDragActivity.this,"click  sfdsfdsf",Toast.LENGTH_SHORT).show();
          }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        //先实例化Callback
        ItemTouchHelper.Callback callback = new SimpleTouchHelperCallback(adapter);
        //用Callback构造ItemtouchHelper
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        //调用ItemTouchHelper的attachToRecyclerView方法建立联系
        touchHelper.attachToRecyclerView(recyclerView);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
               int vid= view.getId();
                Toast.makeText(ItemSwipDragActivity.this,"deletefddfdf",Toast.LENGTH_SHORT).show();
            }
        });

    }


}
