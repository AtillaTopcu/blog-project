package com.atillatpc.business.services.impl;

import com.atillatpc.bean.ModelMapperBean;
import com.atillatpc.business.dto.BlogDto;
import com.atillatpc.business.services.interfaces.IBlogServices;
import com.atillatpc.data.entity.BlogCategoryEntity;
import com.atillatpc.data.entity.BlogEntity;
import com.atillatpc.data.mapper.BlogMapper;
import com.atillatpc.data.repository.IBlogCategoryRepository;
import com.atillatpc.data.repository.IBlogRepository;
import com.atillatpc.exception.HamitMizrakException;
import com.atillatpc.exception._404_NotFoundException;
import com.atillatpc.file_upload.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Service
public class BlogServicesImpl implements IBlogServices<BlogDto, BlogEntity> {

    //injection
    private final IBlogRepository iBlogRepository;
    private final IBlogCategoryRepository iBlogCategoryRepository;
    private final ModelMapperBean modelMapperBean;
    private final ImageService imageService;

    //resimsiz create
    @Override
    @Transactional
    public BlogDto objectServiceCreate(BlogDto blogDto) {
        validate(blogDto, true);

        //blogtan önce kategorisine bakmak gerekiyor.
        Long catId = blogDto.getBlogCategoryDto()!= null ? blogDto.getBlogCategoryDto().getCategoryId() : null;
        if(catId == null)
            throw new HamitMizrakException("Kategori seçiniz!");

        BlogCategoryEntity blogCategoryEntity = iBlogCategoryRepository.findById(catId)
                .orElseThrow(()-> new _404_NotFoundException(catId + " id'li kategori bulunamadı!"));

        //blog entityi çağır ve kategori eşle
        BlogEntity blogEntity = dtoToEntity(blogDto);
        blogEntity.setBlogCategoryEntity(blogCategoryEntity);

        //repoyu save
        BlogEntity createdEntity = iBlogRepository.save(blogEntity);

        return entityToDto(createdEntity);
    }

    //resimli create
    @Override
    @Transactional
    public BlogDto objectServiceCreateWithFile(BlogDto blogDto, MultipartFile multipartFile) {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String relative = imageService.saveBlogImage(multipartFile);
            blogDto.setImage(relative);
        }
        return objectServiceCreate(blogDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BlogDto> objectServiceList() {
        return iBlogRepository.findAll().stream().map(this::entityToDto).toList();
    }

    @Override
    public BlogDto objectServiceFindById(Long id) {
        BlogEntity blogEntity = iBlogRepository.findById(id)
                .orElseThrow(() -> new _404_NotFoundException(id + " id'li kategori bulunamadı!"));
        return entityToDto(blogEntity);
    }

    @Override
    @Transactional
    public BlogDto objectServiceUpdate(Long id, BlogDto blogDto) {
        validate(blogDto, true);

        BlogEntity blogEntity = iBlogRepository.findById(id)
                .orElseThrow(() -> new HamitMizrakException(id + " id'li bir blog bulunmamaktadır!"));

        if (blogDto.getBlogCategoryDto() != null && blogDto.getBlogCategoryDto().getCategoryId() != null){
            Long catId = blogDto.getBlogCategoryDto().getCategoryId();
            BlogCategoryEntity blogCategoryEntity = iBlogCategoryRepository.findById(catId)
                    .orElseThrow(()-> new _404_NotFoundException(id + " id'li bir kategori bulunamadı!"));
            blogEntity.setBlogCategoryEntity(blogCategoryEntity);
        }

        return entityToDto(blogEntity);
    }

    @Override
    @Transactional
    public BlogDto objectServiceUpdateWithFile(Long id, BlogDto blogDto, MultipartFile multipartFile) {

        BlogEntity current = iBlogRepository.findById(id)
                .orElseThrow(()-> new _404_NotFoundException(id + " id'li blog bulunamadı!"));

        //Resim güncelleneceği zaman öncelikle eski resim silinir yoksa server şişer!
        String oldUrl = current.getImage();
        if (multipartFile != null && !multipartFile.isEmpty()){
            String relative = imageService.saveBlogImage(multipartFile);
            blogDto.setImage(relative);
        }
        BlogDto updated = objectServiceUpdate(id, blogDto);

        if (multipartFile != null
                && !multipartFile.isEmpty()
                && oldUrl != null
                && oldUrl.startsWith("/upload/")
                && !oldUrl.equals(updated.getImage())){
            try {
                imageService.deleteByUrl(oldUrl);
            }catch (Exception exception){
                exception.printStackTrace();
                log.error(exception.getMessage());
            }
        }

        return updated;
    }

    @Override
    @Transactional
    public BlogDto objectServiceDelete(Long id) {
        BlogEntity findDelete = dtoToEntity(objectServiceFindById(id));

        String img = findDelete.getImage();
        if (img != null && img.startsWith("/upload/")){
            try {
                imageService.deleteByUrl(img);
            }catch (Exception exception){
                exception.printStackTrace();
                log.error(exception.getMessage());
            }
        }

        iBlogRepository.deleteById(id);
        return entityToDto(findDelete);
    }

    @Override
    @Transactional
    public List<BlogDto> deleteData() {
        iBlogRepository.deleteAll();
        return List.of();
    }

    @Override
    public BlogDto entityToDto(BlogEntity blogEntity) {
        return BlogMapper.toDto(blogEntity);
    }

    @Override
    public BlogEntity dtoToEntity(BlogDto blogDto) {
        return BlogMapper.toEntity(blogDto);
    }

    @Override
    @Transactional
    public List<BlogDto> speedData(Integer data) {
        return null;
    }

    private void validate(BlogDto blogDto, boolean isResult) {
        if (blogDto == null)
            throw new HamitMizrakException("Blog verisi boş!");

        if (isResult){
            if (blogDto.getHeader() == null || blogDto.getHeader().isBlank())
                throw new HamitMizrakException("Header zorunludur!");
            if (blogDto.getTitle() == null || blogDto.getTitle().isBlank())
                throw new HamitMizrakException("Title zorunludur!");
            if (blogDto.getContent() == null || blogDto.getContent().isBlank())
                throw new HamitMizrakException("Content zorunludur!");
        }
    }

    private void validateImage(BlogDto blogDto, boolean isResult) {
        if (blogDto == null)
            throw new HamitMizrakException("Blog verisi boş!");

        if (isResult){
            if (blogDto.getHeader() == null || blogDto.getHeader().isBlank())
                throw new HamitMizrakException("Header zorunludur!");
            if (blogDto.getTitle() == null || blogDto.getTitle().isBlank())
                throw new HamitMizrakException("Title zorunludur!");
            if (blogDto.getContent() == null || blogDto.getContent().isBlank())
                throw new HamitMizrakException("Content zorunludur!");
            if  (blogDto.getImage() == null || blogDto.getImage().isBlank())
                throw new HamitMizrakException("Image zorunludur!");
        }
    }
}
