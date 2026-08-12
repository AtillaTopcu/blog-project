package com.atillatpc.controller.interfaces;

import com.atillatpc.business.services.ICrudService;
import com.atillatpc.business.services.IImageService;
import com.atillatpc.business.services.IModelMapperService;
import com.atillatpc.business.services.ISpeedAndDeleteData;
import com.atillatpc.controller.ICrudApi;
import com.atillatpc.controller.IImageApi;
import com.atillatpc.controller.ISpeedAndDeleteApi;

public interface IBlogApi<D> extends
        ISpeedAndDeleteApi<D>,
        ICrudApi<D>,
        IImageApi<D> {
}
