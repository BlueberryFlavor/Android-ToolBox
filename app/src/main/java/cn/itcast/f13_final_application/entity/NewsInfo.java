package cn.itcast.f13_final_application.entity;

import java.util.List;

public class NewsInfo extends NewsDetailInfo{
    private String stat;
    private List<NewsDetailInfo> data;
    private String page;
    private String pageSize;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public List<NewsDetailInfo> getData() {
        return data;
    }

    public void setData(List<NewsDetailInfo> data) {
        this.data = data;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
