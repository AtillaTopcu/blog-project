package com.atillatpc.token_mail.repository;

import com.atillatpc.token_mail.entity.ForRegisterTokenEmailConfirmationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// CrudRepository<RegisterEntity,Long>
// JpaRepository<RegisterEntity,Long>
// PagingAndSortingRepository<RegisterEntity,Long>

@Repository
public interface IForRegisterTokenEmailConfirmationEntity extends CrudRepository<ForRegisterTokenEmailConfirmationEntity,Long> {

    // Delivered Query
    Optional<ForRegisterTokenEmailConfirmationEntity>  findTokenConfirmationEntityByToken(String token);
}