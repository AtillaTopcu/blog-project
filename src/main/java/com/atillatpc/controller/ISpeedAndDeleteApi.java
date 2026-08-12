package com.atillatpc.controller;

import com.atillatpc.error.ApiResult;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ISpeedAndDeleteApi<D> {

    public ResponseEntity<ApiResult<List<D>>> speedData(Integer data);
    public ResponseEntity<ApiResult<List<D>>> deleteData();
}
