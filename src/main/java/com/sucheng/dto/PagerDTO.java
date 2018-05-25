package com.sucheng.dto;

import java.util.List;

/**
 * 分页对象，包括分页需要的参数pageSize和pageNo及分页结果total和rows
 * 创建于2017-08-15
 *
 * @author 7025
 * @version 1.0
 */
public class PagerDTO {

    private Integer pageNo;
    private Integer pageSize;

    private List<Object> rows;
    private Long total;

    private Long pages;

    private Integer status;
    private String message;

    public PagerDTO() {
    }

    public PagerDTO(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<Object> getRows() {
        return rows;
    }

    public void setRows(List<Object> rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getPages() {
        return total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public Integer getStatus() {
        return 0;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return "pager";
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getBeginIndex() {
        return (pageNo - 1) * pageSize;
    }

    @Override
    public String toString() {
        return "PagerDTO{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", rows=" + rows +
                '}';
    }
}
