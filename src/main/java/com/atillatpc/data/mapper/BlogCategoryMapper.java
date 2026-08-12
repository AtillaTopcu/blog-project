package com.atillatpc.data.mapper;

import com.atillatpc.business.dto.BlogCategoryDto;
import com.atillatpc.data.entity.BlogCategoryEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BlogCategoryMapper {

    public BlogCategoryDto toDto (BlogCategoryEntity blogCategoryEntity) {
        if (blogCategoryEntity == null)
            return null;
        return BlogCategoryDto.builder()
                .categoryId(blogCategoryEntity.getBlogCategoryId())
                .categoryName(blogCategoryEntity.getCategoryName())
                .build();
    }

    public BlogCategoryEntity toEntity (BlogCategoryDto blogCategoryDto){
        if (blogCategoryDto == null)
            return null;
        return BlogCategoryEntity.builder()
                .blogCategoryId(blogCategoryDto.getCategoryId())
                .categoryName(blogCategoryDto.getCategoryName())
                .build();
    }
}