package com.atillatpc.audit;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
abstract public class AuditingAwareBaseDto {

    private String createdBy;   // Kim Ekledi
    private Date createdDate;   // Kim Ne zaman Ekledi
    private String lastUserBy;  // Kim Güncelledi
    private Date lastUserDate;  // Kim Ne Zaman Güncelledi
}
//Sisteme giriş yapmış kişinin bir tabloya ekleme, güncelleme yaptığında
//Database eklenecek kısımdır.
