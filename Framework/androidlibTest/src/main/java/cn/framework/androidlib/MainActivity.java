package cn.framework.androidlib;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.framework.androidlib.adapter.MultipleItemQuickAdapter;
import cn.framework.androidlib.data.DataServer;
import cn.framework.androidlib.data.MultipleItem;

public class MainActivity extends AppCompatActivity {
     @BindView(R.id.recyclerView)
     RecyclerView mRecyclerView;
    private BaseQuickAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);
        initRecycleView();
        initAdapter();
    }
    
    private void initRecycleView(){
        final GridLayoutManager manager = new GridLayoutManager(this, 4);
        mRecyclerView.setLayoutManager(manager);
    }

    private void initAdapter() {
        final List<MultipleItem> data = DataServer.getMultipleItemData();
        final MultipleItemQuickAdapter multipleItemAdapter = new MultipleItemQuickAdapter(this, data);
        multipleItemAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {

                  return data.get(position).getSpanSize();
            }
        });

        multipleItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
               MultipleItem item= (MultipleItem) adapter.getItem(position);
                if(item.getGoClass()!=null){
                    Intent i = new Intent(MainActivity.this,item.getGoClass());
                    startActivity(i);
                }

            }
        });


        mRecyclerView.setAdapter(multipleItemAdapter);

    }

    private  void onHandleItemClick(int position){


    }
}
