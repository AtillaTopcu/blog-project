package com.atillatpc.controller.impl;

import com.atillatpc.business.dto.BlogCategoryDto;
import com.atillatpc.business.services.interfaces.IBlogCategoryService;
import com.atillatpc.controller.interfaces.IBlogCategoryApi;
import com.atillatpc.data.entity.BlogCategoryEntity;
import com.atillatpc.error.ApiResult;
import com.atillatpc.utily.FrontEnd;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Log4j2

@RestController
@RequestMapping("/blog/api/v1.0.0")
@CrossOrigin(origins = FrontEnd.REACT_URL)
public class BlogCategoryApiImpl implements IBlogCategoryApi<BlogCategoryDto> {

    private final IBlogCategoryService<BlogCategoryDto, BlogCategoryEntity> iBlogCategoryService;

    @Override
    @PostMapping("/create")
    public ResponseEntity<ApiResult<?>> objectServiceCreate(@Valid @RequestBody BlogCategoryDto blogCategoryDto) {

        try {
            return ResponseEntity.ok(ApiResult.success(iBlogCategoryService.objectServiceCreate(blogCategoryDto)));
        }catch (Exception exception) {
            return ResponseEntity.ok(ApiResult.error("serverError", exception.getMessage(), "/blog/category/api/v1.0.0/create"));
        }
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<ApiResult<List<BlogCategoryDto>>> objectServiceList() {

        try {
            List<BlogCategoryDto> list = iBlogCategoryService.objectServiceList();
            return ResponseEntity.ok(ApiResult.success(list));
        }catch (Exception exception) {
            return ResponseEntity.ok(ApiResult.error("serverError", exception.getMessage(), "/blog/category/api/v1.0.0/list"));
        }
    }

    @Override
    @GetMapping("/find/{id}")
    public ResponseEntity<ApiResult<?>> objectServiceFindById(@PathVariable(name="id") Long id) {

        try {
            return ResponseEntity.ok(ApiResult.success(iBlogCategoryService.objectServiceFindById(id)));
        }catch (Exception exception) {
            return ResponseEntity.ok(ApiResult.error("serverError", exception.getMessage(), "/blog/category/api/v1.0.0/find/" + id));
        }
    }

    @Override
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResult<?>> objectServiceUpdate(@PathVariable Long id, @Valid @RequestBody BlogCategoryDto blogCategoryDto) {

        try {
            return ResponseEntity.ok(ApiResult.success(iBlogCategoryService.objectServiceUpdate(id, blogCategoryDto)));
        }catch (Exception exception) {
            return ResponseEntity.ok(ApiResult.error("serverError", exception.getMessage(), "/blog/category/api/v1.0.0/update/" + id));
        }
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResult<?>> objectServiceDelete(@PathVariable(name="id") Long id) {

        try {
            return ResponseEntity.ok(ApiResult.success(iBlogCategoryService.objectServiceDelete(id)));
        }catch (Exception exception) {
            return ResponseEntity.ok(ApiResult.error("serverError", exception.getMessage(), "/blog/category/api/v1.0.0/delete/" + id));
        }
    }

    @Override
    @GetMapping("speed")
    public ResponseEntity<ApiResult<List<BlogCategoryDto>>> speedData(Integer data) {
        return ResponseEntity.ok(ApiResult.success(iBlogCategoryService.speedData(5)));
    }

    @Override
    @GetMapping("delete-all")
    public ResponseEntity<ApiResult<List<BlogCategoryDto>>> deleteData() {
        return ResponseEntity.ok(ApiResult.success(iBlogCategoryService.deleteData()));
    }
}
