package com.itcodes.myhub.idcardshow.pojo.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName PageResult    分页的实体类
 * @Author sussen
 * @Version 1.0
 * @Data 2019/8/22
 */
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 7084477580446095195L;
    private Long total; //总数量
    private List<T> rows; //一页显示的列表

    public PageResult() {
    }

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
