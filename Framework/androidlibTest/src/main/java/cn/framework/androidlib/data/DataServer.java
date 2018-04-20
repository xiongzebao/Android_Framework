package cn.framework.androidlib.data;

import java.util.ArrayList;
import java.util.List;

import cn.framework.androidlib.R;
import cn.framework.androidlib.act.BRVAHActivity;
import cn.framework.androidlib.act.ItemSwipDragActivity;

/**
 * Created by xiaoxiong on 2018/1/5.
 * 描述:
 * 路径:
 */

public class DataServer {

    public static List<MultipleItem> getMultipleItemData() {
        List<MultipleItem> list = new ArrayList<>();
        list.add(new MultipleItem(MultipleItem.IMG_TEXT, MultipleItem.IMG_TEXT_SPAN_SIZE_MIN,"BRVAH", R.mipmap.logo1, BRVAHActivity.class));
        list.add(new MultipleItem(MultipleItem.IMG_TEXT, MultipleItem.IMG_TEXT_SPAN_SIZE_MIN,"SWIPITEM", R.mipmap.head_img1, ItemSwipDragActivity.class));


        return list;
    }

    public static List<Status> getStatusItemData(){
        List<Status> list = new ArrayList<>();
        for (int i=0;i<10;i++){

            Status status = new Status("ff","sf");
            list.add(status);
        }
        return list;
    }

}
