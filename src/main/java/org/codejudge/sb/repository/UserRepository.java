package org.codejudge.sb.repository;

import java.math.BigInteger;

import org.codejudge.sb.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);

	UserEntity findByMobile(BigInteger mobile);

}
