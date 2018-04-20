package cn.framework.myandroidlibrary.model;

import java.io.Serializable;

/**
 * Created by xiaoxiong on 2017/11/11.
 * 描述:
 * 路径:
 */

public class WishConditionModel implements Serializable {
    Long Id;
    String strId;
    String txt;

    public WishConditionModel(Long id, String strId, String txt) {
        Id = id;
        this.strId = strId;
        this.txt = txt;
    }

    public WishConditionModel(Long id, String txt) {
        Id = id;
        this.txt = txt;
    }

    public WishConditionModel(String strId, String txt) {
        this.strId = strId;
        this.txt = txt;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getStrId() {
        return strId;
    }

    public void setStrId(String strId) {
        this.strId = strId;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}
