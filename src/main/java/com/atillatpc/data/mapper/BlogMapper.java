package com.atillatpc.data.mapper;

import com.atillatpc.business.dto.BlogDto;
import com.atillatpc.data.entity.BlogEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BlogMapper {

    public BlogDto toDto (BlogEntity blogEntity){
        if (blogEntity == null)
            return null;
        return BlogDto.builder()
                .blogId(blogEntity.getBlogId())
                .header(blogEntity.getHeader())
                .title(blogEntity.getHeader())
                .content(blogEntity.getContent())
                .image(blogEntity.getImage())
                .blogCategoryDto(BlogCategoryMapper.toDto(blogEntity.getBlogCategoryEntity()))
                .build();
    }

    public BlogEntity toEntity (BlogDto blogDto){
        if (blogDto == null)
            return null;
        return BlogEntity.builder()
                .blogId(blogDto.getBlogId())
                .header(blogDto.getHeader())
                .title(blogDto.getHeader())
                .content(blogDto.getContent())
                .image(blogDto.getImage())
                .build();
    }
}
