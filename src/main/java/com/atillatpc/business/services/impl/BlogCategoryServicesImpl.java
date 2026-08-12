package com.atillatpc.business.services.impl;

import com.atillatpc.bean.ModelMapperBean;
import com.atillatpc.business.dto.BlogCategoryDto;
import com.atillatpc.business.services.interfaces.IBlogCategoryService;
import com.atillatpc.data.entity.BlogCategoryEntity;
import com.atillatpc.data.mapper.BlogCategoryMapper;
import com.atillatpc.data.repository.IBlogCategoryRepository;
import com.atillatpc.exception.HamitMizrakException;
import com.atillatpc.exception._404_NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Log4j2
@Service
public class BlogCategoryServicesImpl implements IBlogCategoryService<BlogCategoryDto, BlogCategoryEntity> {

    //injection
    private final IBlogCategoryRepository iBlogCategoryRepository;
    private final ModelMapperBean modelMapperBean;

    @Override
    @Transactional
    public BlogCategoryDto objectServiceCreate(BlogCategoryDto blogCategoryDto) {

        if (blogCategoryDto == null
                || blogCategoryDto.getCategoryName() == null
                || blogCategoryDto.getCategoryName().isBlank()){
            throw new NullPointerException("Kategori adı zorunludur!");
        }

        if (iBlogCategoryRepository.existsByCategoryNameIgnoreCase(blogCategoryDto.getCategoryName())){
            throw new NullPointerException("Böyle bir kategori adı bulunmaktadır!");
        }

        BlogCategoryEntity saved = iBlogCategoryRepository.save(dtoToEntity(blogCategoryDto));
        return entityToDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BlogCategoryDto> objectServiceList() {
        return iBlogCategoryRepository.findAll().stream().map(this::entityToDto).toList();
    }

    @Override
    public BlogCategoryDto objectServiceFindById(Long id) {
        BlogCategoryEntity blogCategoryEntity = iBlogCategoryRepository.findById(id)
                .orElseThrow(() -> new _404_NotFoundException(id + " id'li kategori bulunamadı!"));
        return entityToDto(blogCategoryEntity);
    }

    @Override
    @Transactional
    public BlogCategoryDto objectServiceUpdate(Long id, BlogCategoryDto blogCategoryDto) {
        BlogCategoryEntity findUpdate = dtoToEntity(objectServiceFindById(id));
        if (iBlogCategoryRepository.existsByCategoryNameIgnoreCase(blogCategoryDto.getCategoryName())){
            throw new HamitMizrakException("Kategori zaten var!" + blogCategoryDto.getCategoryName());
        }
        findUpdate.setCategoryName(blogCategoryDto.getCategoryName());
        return entityToDto(iBlogCategoryRepository.save(findUpdate));
    }

    @Override
    @Transactional
    public BlogCategoryDto objectServiceDelete(Long id) {
        BlogCategoryEntity findDelete = dtoToEntity(objectServiceFindById(id));
        iBlogCategoryRepository.deleteById(id);
        return entityToDto(findDelete);
    }

    @Override
    public BlogCategoryDto entityToDto(BlogCategoryEntity blogCategoryEntity) {
        return BlogCategoryMapper.toDto(blogCategoryEntity);
    }

    @Override
    public BlogCategoryEntity dtoToEntity(BlogCategoryDto blogCategoryDto) {
        return BlogCategoryMapper.toEntity(blogCategoryDto);
    }

    @Override
    @Transactional
    public List<BlogCategoryDto> speedData(Integer data) {

        List<BlogCategoryDto> listData = new ArrayList<>();

        if (data != null){
            for (int i = 0; i < data; i++) {
                BlogCategoryEntity blogCategoryEntity = new BlogCategoryEntity();
                blogCategoryEntity.setCategoryName(UUID.randomUUID().toString());
                iBlogCategoryRepository.save(blogCategoryEntity);
                listData.add(BlogCategoryMapper.toDto(blogCategoryEntity));
            }
        }else {
            throw new NullPointerException("Integer null");
        }
        return listData;
    }

    @Override
    @Transactional
    public List<BlogCategoryDto> deleteData() {
        iBlogCategoryRepository.deleteAll();
        return List.of();
    }
}
