package com.pragma.hogar360_microservice_house.commons.configurations.utils.Pagination;

import java.util.List;

public class Pagination<T> {

    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private int totalElements;
    private int totalPages;
    private boolean last;

    public Pagination() {
    }

    public Pagination(List<T> content, int pageNumber, int pageSize, int totalElements) {
        this.content = content;
        this.pageNumber = pageNumber+1;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = (int) Math.ceil((double) totalElements / pageSize);
        this.last = pageNumber >= totalPages;
    }

    public List<T> getContent() {
        return content;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public boolean isLast() {
        return last;
    }

}
