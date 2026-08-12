package com.atillatpc.business.dto;

import com.atillatpc.audit.AuditingAwareBaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

// LOMBOK
@Getter
@Setter

// Serializable:
// abstract: BaseDto instance(örnek) yapılmasını istemiyorum
abstract public class BaseDto extends AuditingAwareBaseDto {

    // FIELD
    // ID
    protected Long id;

    // DATE
    protected Date systemCreatedDate;
} //end BaseDto