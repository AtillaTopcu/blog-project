package com.atillatpc.business.services.interfaces;

import com.atillatpc.business.services.IModelMapperService;
import com.atillatpc.token_mail.dto.EmailDto;

// Email
public interface IEmailServices<D, E> extends IModelMapperService<D, E> {

    // Maili Öncelikle Database kaydedelim.
    public D mailDatabase(D d);

    /////////////////////////////////////////////////////////////////////////////////////
    //**** EMAIL DATABASE **************************************************//
    // Email Database
    EmailDto mailDatabase(EmailDto emailDto);

    ////////////////////////////////////////////
    // EMAIL BASIC SEND (text)
    public D basicSendEmail(D d);

    // EMAIL INTERMEDIA ATTACHMENT SEND (image,word,text,files)
    public D intermediaSendEmail(D d);

}// end IRegisterService