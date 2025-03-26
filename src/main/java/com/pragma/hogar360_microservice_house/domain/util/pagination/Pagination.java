package com.pragma.hogar360_microservice_house.domain.util.pagination;

import com.pragma.hogar360_microservice_house.domain.exceptions.PageNotFoundException;

import java.util.Comparator;
import java.util.List;

public class Pagination<T> {

    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private int totalElements;
    private int totalPages;
    private boolean last;

    public Pagination(List<T> content, int pageNumber, int pageSize, Comparator<T> orderBy, boolean orderAsc) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = content.size();
        this.content = paginationContent(content, orderBy, orderAsc);
        this.totalPages = (int) Math.ceil((double) totalElements / pageSize);
        this.last = pageNumber >= totalPages - PaginationConstants.PAGE_DIFF_INDEX;
    }

    public Pagination(List<T> content, int pageNumber, int pageSize, int totalPages, boolean last) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.content = content;
        this.totalElements = content.size();
        this.totalPages = totalPages;
        this.last = last;
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

    private List<T> paginationContent(List<T> modelList, Comparator<T> orderBy, boolean orderAsc){

        if (modelList.isEmpty()){
            return modelList;
        }

        List<T> sortedModelList = orderList(modelList, orderBy, orderAsc);

        int fromIndex = pageNumber * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, totalElements);

        if (fromIndex >= sortedModelList.size() || fromIndex < PaginationConstants.PAGE_INVALID_NEGATIVE) {
            throw new PageNotFoundException();
        }

        return sortedModelList.subList(fromIndex, toIndex);
    }

    private List<T> orderList(List<T> modelList, Comparator<T> orderBy, boolean orderAsc){

        if (modelList.size() == PaginationConstants.SIZE_ONE_LIST_PAGINATION){
            return modelList;
        }

        if (orderAsc) {
            return modelList.stream()
                    .sorted(orderBy)
                    .toList();
        } else {
            return modelList.stream()
                    .sorted(orderBy.reversed())
                    .toList();
        }
    }

}
