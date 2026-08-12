package com.atillatpc.business.services.interfaces;

import com.atillatpc.business.services.ICrudService;
import com.atillatpc.business.services.IModelMapperService;

// D: Dto
// E: Entity
public interface IRoleService<D, E>  extends IModelMapperService<D,E>,
        ICrudService<D,E> {

}// end IRoleService