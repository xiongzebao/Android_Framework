package cn.framework.app.act;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import cn.framework.framework.R;

public class CollapsingToolbarActivity extends AppCompatActivity {

    XRecyclerView xRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_collapsing_toolbar);

       // View v=getLayoutInflater().inflate(R.layout.activity_collapsing_toolbar,null);

        /*LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView = (XRecyclerView) findViewById(R.id.xRecyclerView);
        xRecyclerView.setLayoutManager(linearLayoutManager);
        xRecyclerView.addHeaderView(v);

        List<String> list = new ArrayList<String>();
        list.add("d");
        HomeAdapter a = new HomeAdapter(this,list);
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        xRecyclerView.setArrowImageView(R.mipmap.ic_launcher_round);
        xRecyclerView.setLayoutManager(linearLayoutManager);
        xRecyclerView.setAdapter(a);*/





    }
}
