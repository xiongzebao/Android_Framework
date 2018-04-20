package cn.framework.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.framework.app.act.TestActivity;
import cn.framework.framework.R;
import cn.framework.myandroidlibrary.act.BaseActivity;
import cn.framework.myandroidlibrary.model.EventBusMessage;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
    }

    public boolean onClick(@NonNull View v) {
        int vid = v.getId();
        if(vid==R.id.testActivity){
            Intent intent = new Intent(this, TestActivity.class);
            startActivity(intent);
            return true;
        }
         return false;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(@NonNull EventBusMessage event) {
        Toast.makeText(this,"MainActivity:"+event.getMsg(),Toast.LENGTH_LONG).show();

    };




    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
