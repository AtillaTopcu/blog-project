package com.atillatpc.controller;

import com.atillatpc.error.ApiResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface IImageApi<D> {

    public ResponseEntity<ApiResult<?>> objectServiceCreateWithFile(String json, MultipartFile multipartFile);
    public ResponseEntity<ApiResult<?>> objectServiceUpdateWithFile(Long id, String json, MultipartFile multipartFile);
}
