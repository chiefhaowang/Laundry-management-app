package com.example.washingstore.bean;

public class QueryInfo {
    private String query;//查询信息username
    private int pageNum;//页数
    private int pageSize;//内页最大数据条数

    public QueryInfo() {
    }

    public QueryInfo(String query, int pageNum, int pageSize) {
        this.query = query;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public String getQuery() {
        return query;
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
