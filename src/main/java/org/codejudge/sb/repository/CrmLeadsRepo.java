package org.codejudge.sb.repository;

import java.math.BigInteger;

import org.codejudge.sb.entity.TbCrmLeads;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrmLeadsRepo extends CrudRepository<TbCrmLeads, Long> {

	TbCrmLeads findByEmail(String email);

	TbCrmLeads findByMobile(BigInteger mobile);

}
