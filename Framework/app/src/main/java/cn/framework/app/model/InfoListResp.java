package cn.framework.app.model;

import java.io.Serializable;
import java.util.List;

import cn.framework.myandroidlibrary.http.PageInfo;

/**
 * Created by zhl on 2016/8/22.
 */
public class InfoListResp implements Serializable {

    private List<InfoListItem> list;
    private PageInfo page;

    public List<InfoListItem> getList() {
        return list;
    }

    public void setList(List<InfoListItem> list) {
        this.list = list;
    }

    public PageInfo getPage() {
        return page;
    }

    public void setPage(PageInfo page) {
        this.page = page;
    }
}
