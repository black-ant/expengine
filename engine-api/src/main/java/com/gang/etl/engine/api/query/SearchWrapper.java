//CHECKSTYLE:OFF
package com.gang.etl.engine.api.query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

/**
 * @Classname SearchWrapper
 * @Description TODO
 * @Date 2020/2/24 17:53
 * @Created by zengzg
 */
public class SearchWrapper {

    private static Logger LOG = LoggerFactory.getLogger(SearchWrapper.class);

    private String filterStr = "";

    /**
     * 1 基本 Equals (orname1=1111)
     * ---> SearchWrapper.buid().eq("you key", "you value");
     * <p>
     * 2 基本 And 结构 (&(age=222)(name=333))
     * ---> SearchWrapper.buidAnd(item -> item.eq("name", "111").eq("age", "222"))
     * <p>
     * 3 基本 Or 结构
     * ---> SearchWrapper.buidOr(item -> item.eq("name", "111").eq("age", "222"))
     * <p>
     * 4 内部判断后添加 (&(age=222)(name=333))
     * SearchWrapper wrapper1323 = SearchWrapper.buidAnd(item -> {
     * if (Boolean.TRUE) {
     * item.eq("name", "111");
     * }
     * if (Boolean.TRUE) {
     * item.eq("age", "222");
     * }
     * });
     * <p>
     * 5 复杂功能 (&(|(orname1=1111)(orname2=222))(&(andname1=333)(andname2=444)))
     * <p>
     * SearchWrapper wrapper132 = SearchWrapper.buidAnd(item -> {
     * SearchWrapper wrapper1 = SearchWrapper.buidOr(wrapper -> wrapper.eq("orname1", "1111").eq("orname2", "222"));
     * SearchWrapper wrapper2 = SearchWrapper.buidAnd(wrapper -> wrapper.eq("andname1", "333").eq("andname2","444"));
     * item.link(wrapper1).link(wrapper2);
     * });
     **/
    public static void main(String[] args) {
    }

    public static SearchWrapper buid() {
        return new SearchWrapper();
    }

    public static SearchWrapper buid(String filterStr) {
        SearchWrapper wrapper = new SearchWrapper();
        wrapper.setFilterStr(filterStr);
        return wrapper;
    }

    public static SearchWrapper buidEQ(String key, String value) {
        return new SearchWrapper().eq(key, value);
    }

    public static SearchWrapper buidAnd(Consumer<SearchWrapper> consumer) {

        SearchWrapper filter = buid();
        return filter.and(consumer);
    }

    public static SearchWrapper buidAndString(Consumer<SearchWrapper> consumer, String filterStr) {
        SearchWrapper filter = buid(filterStr);
        return filter.and(consumer);
    }

    public static SearchWrapper buidOr(Consumer<SearchWrapper> consumer) {
        SearchWrapper filter = buid();
        return filter.or(consumer);
    }

    public SearchWrapper link(SearchWrapper wrapper) {
        this.filterStr = this.filterStr + wrapper.getFilterStr();
        return this;
    }

    public SearchWrapper eq(String column, String val) {
//        this.filterStr = filterStr + this.addCondition(LdapSearchType.EQ, column, val);
        return this;
    }

    public SearchWrapper and(Consumer<SearchWrapper> consumer) {
        consumer.accept(this);
//        this.filterStr = addNestedCondition(LdapSearchType.AND, this.getFilterStr());
        return this;
    }

    public SearchWrapper or(Consumer<SearchWrapper> consumer) {
        consumer.accept(this);
//        this.filterStr = addNestedCondition(LdapSearchType.OR, this.getFilterStr());
        return this;
    }

//    protected String addCondition(LdapSearchType linkType, String column, String val) {
//        return LdapSearchType.LEFT.getKeyword() + column + linkType.getKeyword() + val
//                + LdapSearchType.RIGHT.getKeyword();
//    }
//
//    protected String addNestedCondition(LdapSearchType linkType, String filter) {
//        return LdapSearchType.LEFT.getKeyword() + linkType.getKeyword() + filter + LdapSearchType.RIGHT.getKeyword();
//    }

    public String getFilterStr() {
        return filterStr;
    }

    public void setFilterStr(String filterStr) {
        this.filterStr = filterStr;
    }
}
