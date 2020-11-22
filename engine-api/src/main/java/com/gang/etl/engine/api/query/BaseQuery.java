package com.gang.etl.engine.api.query;

/**
 * @Classname BaseQuery
 * @Description TODO
 * @Date 2020/11/22 21:40
 * @Created by zengzg
 */
public class BaseQuery {

    protected Integer page;

    protected Integer size;

    protected String filter;

    protected String order;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
