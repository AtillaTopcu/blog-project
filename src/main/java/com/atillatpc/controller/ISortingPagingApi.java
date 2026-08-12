package com.atillatpc.controller;

import org.springframework.data.domain.Page;

import java.util.List;

public interface ISortingPagingApi<D, E> {

    public Page<D> objectServicePagination(int currentPage, int pageSize);
    public List<D> objectServiceListSortedByDefault(String sortedBy);
    public List<D> objectServiceListSortedByAsc();
    public List<D> objectServiceListSortedByDesc();
}
