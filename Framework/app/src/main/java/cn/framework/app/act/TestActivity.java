package cn.framework.app.act;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.framework.app.adapter.MyTestAdapter;
import cn.framework.framework.R;
import cn.framework.myandroidlibrary.act.BaseActivity;
import cn.framework.myandroidlibrary.adapter.MyBaseRecyclerAdapter;
import cn.framework.myandroidlibrary.http.RespDataBase;
import cn.framework.myandroidlibrary.model.EventBusMessage;
import cn.framework.myandroidlibrary.utils.AppUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class TestActivity extends BaseActivity {


    @BindView(R.id.recyclerView)
    public XRecyclerView recyclerView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(AppUtils.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<MyBaseRecyclerAdapter.BaseItem> list = new ArrayList<>();
        for (int i=0;i <0;i++){
            list.add(new MyBaseRecyclerAdapter.BaseItem());

        }

        MyTestAdapter adapter = new MyTestAdapter(this,list,false);
        recyclerView.setAdapter(adapter);

    }




    @Override
    public void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                EventBusMessage busMessage = new EventBusMessage();
                busMessage.setMsg("TestActvity");
                EventBus.getDefault().post(busMessage);
            }
        },3000);
    }

    void rxJavaTest(){
        //创建一个上游 Observable： Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() { @Override public void subscribe(ObservableEmitter<Integer> emitter) throws Exception { emitter.onNext(1); emitter.onNext(2); emitter.onNext(3); emitter.onComplete(); } }); //创建一个下游 Observer Observer<Integer> observer = new Observer<Integer>() { @Override public void onSubscribe(Disposable d) { Log.d(TAG, "subscribe"); } @Override public void onNext(Integer value) { Log.d(TAG, "" + value); } @Override public void onError(Throwable e) { Log.d(TAG, "error"); } @Override public void onComplete() { Log.d(TAG, "complete"); } }; //建立连接 observable.subscribe(observer);
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        });

        //创建一个下游Observer
        Observer<Integer> observer0 = new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.e("xiong","onSubscribe");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.e("xiong","onNext"+integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("xiong","onError");
            }

            @Override
            public void onComplete() {
                Log.e("xiong","onComplete");
            }
        };
        observable.subscribe(observer0);
        observable.subscribe(observer1);
    }

    //创建一个下游Observer
    Observer<Integer> observer1 = new Observer<Integer>() {
        @Override
        public void onSubscribe(@NonNull Disposable d) {
            Log.e("xiong","onSubscribe");
        }

        @Override
        public void onNext(@NonNull Integer integer) {
            Log.e("xiong","onNext"+integer);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            Log.e("xiong","onError");
        }

        @Override
        public void onComplete() {
            Log.e("xiong","onComplete");
        }
    };

    @Override
    public void onSuccess(RespDataBase data, boolean cache, String reqTag) {
        super.onSuccess(data, cache, reqTag);

    }

    @Override
    public void onError(int code, String message, String reqTag) {
        super.onError(code, message, reqTag);

    }
}
