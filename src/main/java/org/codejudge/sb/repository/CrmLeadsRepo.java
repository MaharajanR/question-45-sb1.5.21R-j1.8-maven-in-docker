package org.codejudge.sb.repository;

import org.codejudge.sb.entity.TbCrmLeads;
import org.codejudge.sb.entity.TbCrmLeadsPk;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CrmLeadsRepo extends CrudRepository<TbCrmLeads, TbCrmLeadsPk> {

	TbCrmLeads findById(long leadId);

	@Modifying
	@Transactional
	@Query("delete from TbCrmLeads where id = :leadId")
	int delete(@Param("leadId") Long leadId);

}
