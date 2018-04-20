package cn.framework.myandroidlibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by xiaoxiong on 2017/11/10.
 * 描述:
 * 路径:
 */

public class MyHorizontalScrollView extends HorizontalScrollView {

    private int showMaxSize = 5;
    private ArrayList<View> itemViews = new ArrayList<>();
    private LinearLayout container;

    public int getShowMaxSize() {
        return showMaxSize;
    }

    public void setShowMaxSize(int showMaxSize) {
        this.showMaxSize = showMaxSize;
    }

    public MyHorizontalScrollView(Context context) {
        super(context);
        init();

    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        container = new LinearLayout(getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        container.setLayoutParams(lp);
        container.setGravity(Gravity.CENTER_VERTICAL);
        addView(container);
    }


    public void removeAllItemViews() {
        container.removeAllViews();
        itemViews.clear();
    }

    public void addItemView(View v) {
        if (v == null) {
            return;
        }
        if (itemViews.size() < showMaxSize) {
            container.addView(v);
        }
        itemViews.add(v);
    }

    public void removeView(int position) {
        if (position >= itemViews.size()) {
            return;
        }
        if (position < showMaxSize) {
            container.removeView(itemViews.get(position));
        }
        itemViews.remove(position);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean b = super.onTouchEvent(ev);
        return false;
    }
}
