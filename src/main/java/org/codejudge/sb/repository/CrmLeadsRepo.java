package org.codejudge.sb.repository;

import org.codejudge.sb.entity.TbCrmLeads;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrmLeadsRepo extends CrudRepository<TbCrmLeads, Long> {

	TbCrmLeads findByEmail(String email);

}
