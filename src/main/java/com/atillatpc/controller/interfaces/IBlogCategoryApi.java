package com.atillatpc.controller.interfaces;

import com.atillatpc.controller.ICrudApi;
import com.atillatpc.controller.ISpeedAndDeleteApi;

public interface IBlogCategoryApi<D> extends
        ISpeedAndDeleteApi<D>,
        ICrudApi<D> {
}
