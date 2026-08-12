package com.atillatpc.controller;

import com.atillatpc.error.ApiResult;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICrudApi<D> {

    public ResponseEntity<ApiResult<?>> objectServiceCreate(D d);
    public ResponseEntity<ApiResult<List<D>>> objectServiceList();
    public ResponseEntity<ApiResult<?>> objectServiceFindById(Long id);
    public ResponseEntity<ApiResult<?>> objectServiceUpdate(Long id, D d);
    public ResponseEntity<ApiResult<?>> objectServiceDelete(Long id);
}
