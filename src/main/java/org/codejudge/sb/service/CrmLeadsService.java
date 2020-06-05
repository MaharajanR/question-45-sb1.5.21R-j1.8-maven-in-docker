package org.codejudge.sb.service;

import org.codejudge.sb.payload.request.LeadsRequest;
import org.codejudge.sb.payload.response.FetchLeadsResponse;
import org.codejudge.sb.payload.response.SaveLeadsResponse;
import org.codejudge.sb.payload.response.SucessResponse;

public interface CrmLeadsService {

	SaveLeadsResponse saveLeads(LeadsRequest fetchLeadsRequest);

	FetchLeadsResponse findByLeadId(Long leadId);

	SucessResponse updateLeads(LeadsRequest updateLeadReq, long leadId);

	void removeLeads(Long leadId);

	SucessResponse updateCommuniCationLeads(LeadsRequest updateLeadReq, long leadId);

}
