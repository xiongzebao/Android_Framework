package cn.framework.myandroidlibrary.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.List;

import cn.framework.myandroidlibrary.R;
import cn.framework.myandroidlibrary.model.WishConditionModel;
import cn.framework.myandroidlibrary.utils.ABTextUtil;
import cn.framework.myandroidlibrary.utils.AppUtils;


/**
 * Created by xiaoxiong on 2017/11/6.
 * 描述:
 * 路径:
 */


public class MyFlexBoxLayout extends FlexboxLayout {

    public static int DEFAUTL_MODEL = 0;//不选
    public static int SINGLE_MODEL = 1;//单项
    public static int Mutiple_MODEL = 2;//多选

    private List<Object> selItems = new ArrayList<>();
    private View selItemView;
    private List<View> selItemViews = new ArrayList<>();


    private BaseAdapter adapter;
    private List<View> viewContainer = new ArrayList<View>();
    private OnClickListener onClicklistener;
    private int selectModel = 0;
    private int selDrawableResId = 0;
    private int unSelDrawableResId = 0;
    private int selTextColor = Color.parseColor("#ffffff");
    private int unSelTextColor = Color.parseColor("#333333");
    private int maxSize = 99999;


    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getSelDrawableResId() {
        return selDrawableResId;
    }

    public void setSelDrawableResId(int selDrawableResId) {
        this.selDrawableResId = selDrawableResId;
    }

    public int getUnSelDrawableResId() {
        return unSelDrawableResId;
    }

    public void setUnSelDrawableResId(int unSelDrawableResId) {
        this.unSelDrawableResId = unSelDrawableResId;
    }

    public int getSelectModel() {
        return selectModel;
    }

    public void setSelectModel(int selectModel) {
        this.selectModel = selectModel;
    }

    public OnClickListener getOnClicklistener() {
        return onClicklistener;
    }

    public void setOnClicklistener(OnClickListener onClicklistener) {
        this.onClicklistener = onClicklistener;
    }

    public MyFlexBoxLayout(Context context) {
        super(context);

    }

    public MyFlexBoxLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public MyFlexBoxLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    private void initViews() {
        if (adapter == null) {
            return;
        }
        this.removeAllViews();
        viewContainer.clear();
        if(ABTextUtil.isEmpty(adapter.mDatas)){
            return;
        }
        int maxsize = adapter.mDatas.size() < maxSize ? adapter.mDatas.size() : maxSize;
        for (int i = 0; i < maxsize; i++) {
            View v = getItemView(i);
            this.addView(v);
            viewContainer.add(v);
        }
        //  invalidate();
    }

/*
    private List<View> getViews(){
        List<View> views = new ArrayList<>();
        int maxsize = adapter.mDatas.size()<maxSize?adapter.mDatas.size():maxSize;
        for (int i = 0; i < maxsize; i++) {
            views.add(getItemView(i));
        }
        return views;
    }
*/


    private View getItemView(final int position) {
        View v = adapter.getItemView(position);
        // v.setTag(R.id.wishSelData,mDatas.get(position));
        v.setTag(R.id.wishSelflag, "unchecked");
        v.setTag(R.id.wishSelData, adapter.mDatas.get(position));

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectModel == SINGLE_MODEL) {
                    selItems.clear();
                    if (selItemView != null && selItemView != view) {
                        setUnSelectedState(selItemView);
                    }
                    String tag = (String) view.getTag(R.id.wishSelflag);
                    if (tag == null || tag.equals("unchecked")) {
                        selItems.add(view.getTag(R.id.wishSelData));
                        setSelectedState(view);
                        if (MyFlexBoxLayout.this.onClicklistener != null) {
                            MyFlexBoxLayout.this.onClicklistener.OnItemClick(selItems, selectModel);
                            MyFlexBoxLayout.this.onClicklistener.OnItemClick(selItems, position, selectModel);
                        }
                    } else {
                        setUnSelectedState(view);
                        if (MyFlexBoxLayout.this.onClicklistener != null) {
                            MyFlexBoxLayout.this.onClicklistener.OnItemClick(null, selectModel);
                            MyFlexBoxLayout.this.onClicklistener.OnItemClick(selItems, position, selectModel);
                        }
                    }
                    selItemView = view;
                    return;
                } else if (selectModel == Mutiple_MODEL) {
                    String tag = (String) view.getTag(R.id.wishSelflag);
                    if (tag != null && tag.equals("checked")) {
                        Object obj = view.getTag(R.id.wishSelData);
                        boolean b = selItems.remove(obj);

                        view.setTag(R.id.wishSelflag, "unckecked");
                        selItemViews.remove(view);
                        setUnSelectedState(view);
                    } else {
                        selItems.add(view.getTag(R.id.wishSelData));
                        view.setTag(R.id.wishSelflag, "checked");
                        selItemViews.add(view);
                        setSelectedState(view);
                    }
                    if (MyFlexBoxLayout.this.onClicklistener != null) {
                        MyFlexBoxLayout.this.onClicklistener.OnItemClick(selItems, selectModel);
                        MyFlexBoxLayout.this.onClicklistener.OnItemClick(selItems, position, selectModel);
                    }
                    return;
                } else {
                    selItems.clear();
                    selItems.add(view.getTag(R.id.wishSelData));
                    if (MyFlexBoxLayout.this.onClicklistener != null) {
                        MyFlexBoxLayout.this.onClicklistener.OnItemClick(selItems, selectModel);
                        MyFlexBoxLayout.this.onClicklistener.OnItemClick(selItems, position, selectModel);
                    }
                    return;
                }
            }
        });

        return v;
    }

    private void setSelectedState(View view) {
        Resources resources = getContext().getResources();
        Drawable drawable=null;
        view.setTag(R.id.wishSelflag, "checked");
        if (selDrawableResId == 0) {
           // drawable = resources.getDrawable(R.drawable.btn_wish_blue_bg_corner);
        } else {
            drawable = resources.getDrawable(selDrawableResId);
        }

        if (view instanceof TextView) {
            TextView textView = (TextView) view;
        } else {
            TextView textView = (TextView) view.findViewById(R.id.txt);
            if(selTextColor!=0){
                textView.setTextColor(selTextColor);
            }

            if (selDrawableResId == 0) {
                textView.setBackgroundResource(R.color.wish_primary_blue);
            } else {
                if(drawable!=null){
                    textView.setBackground(drawable);
                }

            }
        }
    }

    private void setUnSelectedState(View view) {
        Resources resources = getContext().getResources();
        view.setTag(R.id.wishSelflag, "unchecked");
        Drawable drawable=null;
        if (unSelDrawableResId == 0) {
            if(adapter.getBgDrawableResId()!=0){
                drawable = resources.getDrawable(adapter.getBgDrawableResId());
            }

        } else {
            drawable = resources.getDrawable(unSelDrawableResId);
        }

        TextView textView ;

        if (view instanceof TextView) {
            textView= (TextView) view;
        }else{
              textView = (TextView) view.findViewById(R.id.txt);
        }

        setUnSelTextViewColor(textView);
        if(drawable!=null){
            textView.setBackground(drawable);
        }else if(adapter.getBgColorResId()!=0){
            textView.setBackgroundColor(AppUtils.getColor(adapter.getBgColorResId()));
        }
    }

    private void setUnSelTextViewColor(TextView textView) {
        if (unSelTextColor != 0) {
            textView.setTextColor(unSelTextColor);
        } else {
            int color = 0;
            if (adapter != null) {
                color = adapter.getTextColor();
            }
            if (color == 0) {
                color = AppUtils.getColor(R.color.gray79);
            }
            textView.setTextColor(color);
        }
    }

    public List<Object> getSelItems() {
        return selItems;
    }

    public BaseAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(BaseAdapter adapter) {
        if (adapter == null) {
            return;
        }
        this.adapter = adapter;
        adapter.setFlexBoxLayout(this);
        initViews();
    }

    public void refresh() {
        initViews();
    }

    public void removeSelectedItemViews() {

        if (selectModel == DEFAUTL_MODEL) {

        } else if (selectModel == SINGLE_MODEL) {
            if (selItemView != null) {
                setUnSelectedState(selItemView);
            }
        } else if (selectModel == Mutiple_MODEL) {
            for (View item : selItemViews) {
                item.setTag(R.id.wishSelflag, "unchecked");
                setUnSelectedState(item);
            }
            selItemViews.clear();
        }
        selItems.clear();
        if (MyFlexBoxLayout.this.onClicklistener != null) {
            MyFlexBoxLayout.this.onClicklistener.OnItemClick(selItems, selectModel);
        }
    }

    public int getSelTextColor() {
        return selTextColor;
    }

    public void setSelTextColor(int selTextColor) {
        this.selTextColor = selTextColor;
    }

    public int getUnSelTextColor() {
        return unSelTextColor;
    }

    public void setUnSelTextColor(int unSelTextColor) {
        this.unSelTextColor = unSelTextColor;
    }

    public static abstract class BaseAdapter {

        protected Context context;
        protected List<Object> mDatas;
        protected MyFlexBoxLayout flexBoxLayout;
        protected ArrayList<View> allViews = new ArrayList<>();
        protected int textColor = Color.parseColor("#606060");
        protected int maxTextSize = 0;
        protected int itemWidth = 0;
        protected int paddingRL = 0;
        protected int marginRL = 0;
        protected int marginTB=0;
        protected int bgDrawableResId=0;
        protected int bgColorResId =0;




        public boolean isContainItem(Object item){
            String t = (String) item;
            if(item instanceof  String){
                for (int i=0;i<mDatas.size();i++){
                    String str = (String) mDatas.get(i);
                    if(t.equals(str)){
                        return true;
                    }
                }
                return false;
            }
            WishConditionModel model = (WishConditionModel) item;
            if(item instanceof WishConditionModel){
                for (int i=0;i<mDatas.size();i++){
                    WishConditionModel tmodel = (WishConditionModel) mDatas.get(i);
                    if(model.getTxt().equals(tmodel.getTxt())){
                        return true;
                    }
                }
                return false;
            }
          return false;
        }

        public int getMarginTB() {
            return marginTB;
        }

        public void setMarginTB(int marginTB) {
            this.marginTB = marginTB;
        }

        public int getBgDrawableResId() {
            return bgDrawableResId;
        }

        public void setBgDrawableResId(int bgDrawableResId) {
            this.bgDrawableResId = bgDrawableResId;
        }

        public int getBgColorResId() {
            return bgColorResId;
        }

        public void setBgColorResId(int bgColorResId) {
            this.bgColorResId = bgColorResId;
        }

        public int getPaddingRL() {
            return paddingRL;
        }

        public void setPaddingRL(int paddingRL) {
            this.paddingRL = paddingRL;
        }

        public int getMarginRL() {
            return marginRL;
        }

        public void setMarginRL(int marginRL) {
            this.marginRL = marginRL;
        }

        public int getItemWidth() {
            return itemWidth;
        }

        public void setItemWidth(int itemWidth) {
            this.itemWidth = itemWidth;
        }

        public int getMaxTextSize() {
            return maxTextSize;
        }

        public void setMaxTextSize(int maxTextSize) {
            this.maxTextSize = maxTextSize;
        }

        public int getTextColor() {
            return textColor;
        }

        public void setTextColor(int textColor) {
            this.textColor = textColor;
        }

        public ArrayList<View> getAllViews() {
            return allViews;
        }

        public void setAllViews(ArrayList<View> allViews) {
            this.allViews = allViews;
        }

        public Context getContext() {
            return context;
        }

        public void setContext(Context context) {
            this.context = context;
        }

        public List<Object> getmDatas() {
            return mDatas;
        }

      /*  public void setDatas(List mDatas) {
            this.mDatas = mDatas;
        }
*/
        public MyFlexBoxLayout getFlexBoxLayout() {
            return flexBoxLayout;
        }

        public void setFlexBoxLayout(MyFlexBoxLayout flexBoxLayout) {
            this.flexBoxLayout = flexBoxLayout;
        }

        public BaseAdapter(Context context, List<Object> datas) {
            this.context = context;
            if(datas==null){
                this.mDatas = new ArrayList<>();
            }else{
                this.mDatas = datas;
            }

        }

        public abstract View getItemView(int position);


        protected View makeItemView(int LayoutResID) {
            View v = LayoutInflater.from(context).inflate(LayoutResID, null);

            TextView tv;
            if (v instanceof TextView) {
                tv = (TextView) v;

            } else {
                tv = (TextView) v.findViewById(R.id.txt);
            }
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) tv.getLayoutParams();

            if (itemWidth != 0) {
                tv.setMaxWidth(itemWidth);
                tv.setMinWidth(itemWidth);
                tv.setWidth(itemWidth);
            }

            if (paddingRL != 0) {
                tv.setPadding(paddingRL, tv.getPaddingTop(), paddingRL, tv.getPaddingBottom());
            }



            if (marginRL != 0) {
                lp.setMargins(marginRL, lp.topMargin, marginRL, lp.bottomMargin);
            }

            if(marginTB!=0){
                lp.setMargins(lp.leftMargin,marginTB , lp.rightMargin, marginTB);
            }

            if (textColor != 0) {
                tv.setTextColor(textColor);
            }

            if(bgDrawableResId!=0){
                tv.setBackground(AppUtils.getDrawable(bgDrawableResId));
            }
            if(bgColorResId !=0){
                tv.setBackgroundColor(AppUtils.getColor(bgColorResId));
            }

            tv.setLayoutParams(lp);
            return v;
        }

        public void removeItem(Object obj) {
            mDatas.remove(obj);
            flexBoxLayout.refresh();
        }

        public void addItem(Object obj) {

            mDatas.add(obj);
            View v=null;
            if(mDatas.size()>0){
                v = getItemView(mDatas.size() - 1);
            }else{
                v = getItemView(0);
            }

            allViews.add(v);
            refresh();
        }


        public void removeItem(int position) {
            mDatas.remove(position);
            flexBoxLayout.refresh();
        }

        public void setDatas(List<Object> mDatas) {
            this.mDatas = mDatas;
            flexBoxLayout.refresh();
        }

        public void removeAll() {
            mDatas.clear();
            flexBoxLayout.refresh();
        }

        public void refresh() {
            flexBoxLayout.refresh();
        }

        public boolean isEmpty() {
            if (mDatas.size() == 0) {
                return true;
            } else {
                return false;
            }
        }

        public View getViewByTag(String tag) {
            for (View v : allViews) {
                TextView tv = (TextView) v.findViewById(R.id.txt);
                if (tv == null) {
                    continue;
                }
                if (tv.getText().toString().equals(tag)) {
                    return v;
                }
            }
            return null;
        }

        public void setSelItems(List<String> selItems) {
            for (Object obj : selItems) {
                if (obj instanceof String) {
                    this.mDatas.add(obj);
                    flexBoxLayout.setSelectedState(getViewByTag((String) obj));
                } else if (obj instanceof WishConditionModel) {
                    this.mDatas.add(obj);
                    flexBoxLayout.setSelectedState(getViewByTag(((WishConditionModel) obj).getTxt()));
                }
            }
        }

        public int getIndexByTag(String obj) {
            for (int i = 0; i < mDatas.size(); i++) {
                if (mDatas.get(i) instanceof String) {
                    String t = (String) mDatas.get(i);
                    if (t.equals(obj)) {
                        return i;
                    }
                } else if (mDatas.get(i) instanceof WishConditionModel) {
                    WishConditionModel t = (WishConditionModel) mDatas.get(i);
                    if (t.getTxt().equals(obj)) {
                        return i;
                    }
                }
            }
            return -1;
        }


        public void setWishConditionSelItems(List<WishConditionModel> selItems) {
            if (selItems == null) {
                return;
            }
            for (Object obj : selItems) {
                if (obj instanceof String) {
                    // this.mDatas.add(obj);
                    View v = getViewByTag((String) obj);
                    if (v == null) {
                        continue;
                    }
                    flexBoxLayout.selItemViews.add(v);
                    flexBoxLayout.selItems.add(obj);
                    flexBoxLayout.setSelectedState(v);
                } else if (obj instanceof WishConditionModel) {

                    View v = getViewByTag(((WishConditionModel) obj).getTxt());
                    WishConditionModel model = (WishConditionModel) obj;
                    String txt = model.getTxt();
                    if (v == null) {
                        continue;
                    }
                    flexBoxLayout.selItemViews.add(v);
                    int index = getIndexByTag(txt);
                    flexBoxLayout.selItems.add(mDatas.get(index));
                    flexBoxLayout.setSelectedState(v);
                }
            }
        }

        protected String truncateStr(String txt) {
            if (txt == null) {
                return null;
            }

            return txt;
          /*  if( maxTextSize==0){
                return txt;
            }
            if(txt.length()<=maxTextSize){
                return txt;
            }else{
                return txt.substring(0,maxTextSize)+"...";
            }*/
        }

        public int getCount(){
            return  mDatas.size();
        }
    }

    public static class OnClickListener {

        public void OnItemClick(List<Object> obj, int selectMode) {
        }

        public void OnItemClick(List<Object> obj, int position, int selectMode) {
        }
        // void OnItemLongClick();
    }

}
