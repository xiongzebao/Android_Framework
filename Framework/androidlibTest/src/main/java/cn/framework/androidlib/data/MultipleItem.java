package cn.framework.androidlib.data;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class MultipleItem implements MultiItemEntity {
    public static final int TEXT = 1;
    public static final int IMG = 2;
    public static final int IMG_TEXT = 3;
    public static final int TEXT_SPAN_SIZE = 3;
    public static final int IMG_SPAN_SIZE = 1;
    public static final int IMG_TEXT_SPAN_SIZE = 4;
    public static final int IMG_TEXT_SPAN_SIZE_MIN = 2;
    private int itemType;
    private int spanSize;
    private String content;
    private int imgResId;
    private Class<?> goClass;

    public MultipleItem(int itemType, int spanSize, String content,int imgResId,Class<?> goClass) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.content = content;
        this.imgResId = imgResId;
        this.goClass = goClass;
    }


    public MultipleItem(int itemType, int spanSize, String content,int imgResId) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.content = content;
        this.imgResId = imgResId;
    }

    public MultipleItem(int itemType, int spanSize) {
        this.itemType = itemType;
        this.spanSize = spanSize;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public Class<?> getGoClass() {
        return goClass;
    }

    public void setGoClass(Class<?> goClass) {
        this.goClass = goClass;
    }
}
