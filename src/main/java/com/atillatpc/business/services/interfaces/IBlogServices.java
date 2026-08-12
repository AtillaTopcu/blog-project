package com.atillatpc.business.services.interfaces;

import com.atillatpc.business.services.ICrudService;
import com.atillatpc.business.services.IImageService;
import com.atillatpc.business.services.IModelMapperService;
import com.atillatpc.business.services.ISpeedAndDeleteData;

public interface IBlogServices<D, E> extends
        IModelMapperService<D, E>,
        ISpeedAndDeleteData<D, E>,
        ICrudService<D, E>,
        IImageService<D> {
}
