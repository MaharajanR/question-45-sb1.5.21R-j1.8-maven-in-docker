package org.codejudge.sb.service;

import org.codejudge.sb.payload.LeadsRequest;
import org.codejudge.sb.payload.SaveLeadsResponse;
import org.springframework.stereotype.Component;

@Component
public interface CrmLeadsService {
	
	SaveLeadsResponse saveLeads(LeadsRequest fetchLeadsRequest);

}
