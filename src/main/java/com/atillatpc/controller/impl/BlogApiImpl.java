package com.atillatpc.controller.impl;

import com.atillatpc.business.dto.BlogDto;
import com.atillatpc.business.services.impl.BlogServicesImpl;
import com.atillatpc.controller.interfaces.IBlogApi;
import com.atillatpc.error.ApiResult;
import com.atillatpc.utily.FrontEnd;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Log4j2

@RestController
@RequestMapping("/blog/category/api/v1.0.0")
@CrossOrigin(origins = FrontEnd.REACT_URL)
public class BlogApiImpl implements IBlogApi<BlogDto> {

    private final BlogServicesImpl iBlogServices;
    private final ObjectMapper objectMapper;

    @Override
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResult<?>> objectServiceCreate(@Valid @RequestBody BlogDto blogDto) {

        try {
            return ResponseEntity.ok(ApiResult.success(iBlogServices.objectServiceCreate(blogDto)));
        }catch (Exception exception) {
            return ResponseEntity.ok(ApiResult.error("serverError", exception.getMessage(), "/blog/api/v1.0.0/create"));
        }
    }

    @Override
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResult<?>> objectServiceCreateWithFile(
            @RequestPart("blog") String json,
            @RequestPart(value = "file", required = false) MultipartFile multipartFile) {

        try {
            BlogDto blogDto = objectMapper.readValue(json, BlogDto.class);
            return ResponseEntity.ok(ApiResult.success(iBlogServices.objectServiceCreateWithFile(blogDto, multipartFile)));
        }catch (Exception exception) {
            return ResponseEntity.ok(ApiResult.error("serverError", exception.getMessage(), "/blog/api/v1.0.0/create"));
        }
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<ApiResult<List<BlogDto>>> objectServiceList() {

        try {
            List<BlogDto> list = iBlogServices.objectServiceList();
            return ResponseEntity.ok(ApiResult.success(list));
        }catch (Exception exception) {
            return ResponseEntity.ok(ApiResult.error("serverError", exception.getMessage(), "/blog/api/v1.0.0/list"));
        }
    }

    @Override
    @GetMapping("/find/{id}")
    public ResponseEntity<ApiResult<?>> objectServiceFindById(@PathVariable(name="id") Long id) {

        try {
            return ResponseEntity.ok(ApiResult.success(iBlogServices.objectServiceFindById(id)));
        }catch (Exception exception) {
            return ResponseEntity.ok(ApiResult.error("serverError", exception.getMessage(), "/blog/api/v1.0.0/find/" + id));
        }
    }

    @Override
    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResult<?>> objectServiceUpdate(
            @PathVariable(name="id") Long id,
            @Valid @RequestBody BlogDto blogDto) {

        try {
            return ResponseEntity.ok(ApiResult.success(iBlogServices.objectServiceUpdate(id, blogDto)));
        }catch (Exception exception) {
            return ResponseEntity.ok(ApiResult.error("serverError", exception.getMessage(), "/blog/api/v1.0.0/update/" + id));
        }
    }

    @Override
    @PutMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResult<?>> objectServiceUpdateWithFile(
            @PathVariable(name="id") Long id,
            @RequestPart("blog") String json,
            @RequestPart(value = "file", required = false) MultipartFile multipartFile) {

        try {
            BlogDto blogDto = objectMapper.readValue(json, BlogDto.class);
            return ResponseEntity.ok(ApiResult.success(iBlogServices.objectServiceUpdateWithFile(id, blogDto, multipartFile)));
        }catch (Exception exception) {
            return ResponseEntity.ok(ApiResult.error("serverError", exception.getMessage(), "/blog/api/v1.0.0/create"));
        }
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResult<?>> objectServiceDelete(@PathVariable(name="id") Long id) {

        try {
            BlogDto deleteBlogDto = iBlogServices.objectServiceDelete(id);
            return ResponseEntity.ok(ApiResult.success(deleteBlogDto));
        }catch (Exception exception) {
            return ResponseEntity.ok(ApiResult.error("serverError", exception.getMessage(), "/blog/api/v1.0.0/delete/" + id));
        }
    }

    @Override
    @GetMapping("speed")
    public ResponseEntity<ApiResult<List<BlogDto>>> speedData(Integer data) {
        return null;
    }

    @Override
    @GetMapping("delete-all")
    public ResponseEntity<ApiResult<List<BlogDto>>> deleteData() {
        return null;
    }
}
