package com.atillatpc.business.services.interfaces;

import com.atillatpc.business.dto.RegisterDto;
import com.atillatpc.business.services.ICrudService;
import com.atillatpc.business.services.IModelMapperService;

/*
 Register başlangıçta pasif durumda;
 1- Ancak mail onaylanmasıyla aktifleşecek.
 2- SMS pin code:
*/

// D: Dto
// E: Entity

// Register(N) Roles(M)
public interface IRegisterServices<D, E>  extends IModelMapperService<D,E>,
        ICrudService<D,E> {

    // SPEED DATA
    public String registerSpeedData(Long data);

    // USER ALL DELETE
    public String registerAllUSerDelete();

    // CREATE
    public RegisterDto objectServiceCreate(Long rolesId, D d);

    ////////////////////////////////////////////////
    // EMAIL CONFIRMATION - TOKEN
}// end IRegisterService