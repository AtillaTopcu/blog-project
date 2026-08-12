package com.atillatpc.business.services;

import com.atillatpc.data.entity.BlogEntity;
import org.springframework.web.multipart.MultipartFile;

public interface IImageService <D> {

    public D objectServiceCreateWithFile(D d, MultipartFile multipartFile);
    public D objectServiceUpdateWithFile(Long id, D d, MultipartFile multipartFile);
}
