package com.atillatpc.business.services;

import org.springframework.data.domain.Page;

import java.util.List;

public interface ISortingPagingService<D, E> {

    public Page<D> objectServicePagination(int currentPage, int pageSize);
    public List<D> objectServiceListSortedByDefault(String sortedBy);
    public List<D> objectServiceListSortedByAsc();
    public List<D> objectServiceListSortedByDesc();
}
