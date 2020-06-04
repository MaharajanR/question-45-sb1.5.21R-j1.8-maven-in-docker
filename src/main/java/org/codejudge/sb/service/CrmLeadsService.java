package org.codejudge.sb.service;

import org.codejudge.sb.payload.FetchLeadsResponse;
import org.codejudge.sb.payload.LeadsRequest;
import org.codejudge.sb.payload.SaveLeadsResponse;
import org.codejudge.sb.payload.SucessResponse;


public interface CrmLeadsService {

	SaveLeadsResponse saveLeads(LeadsRequest fetchLeadsRequest);

	FetchLeadsResponse findByLeadId(Long leadId);

	SucessResponse updateLeads(LeadsRequest updateLeadReq, long leadId);

	SucessResponse removeLeads(Long leadId);

}
