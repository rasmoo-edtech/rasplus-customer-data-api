package com.client.rasplus.api.customer.repository;

import com.client.rasplus.api.customer.model.UserRecoveryCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRecoveryCodeRepository extends CrudRepository<UserRecoveryCode, String> {

    Optional<UserRecoveryCode> findByEmail(String email);
}
