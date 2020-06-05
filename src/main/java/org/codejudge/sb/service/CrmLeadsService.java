package org.codejudge.sb.service;

import org.codejudge.sb.payload.Request.LeadsRequest;
import org.codejudge.sb.payload.Response.FetchLeadsResponse;
import org.codejudge.sb.payload.Response.SaveLeadsResponse;
import org.codejudge.sb.payload.Response.SucessResponse;


public interface CrmLeadsService {

	SaveLeadsResponse saveLeads(LeadsRequest fetchLeadsRequest);

	FetchLeadsResponse findByLeadId(Long leadId);

	SucessResponse updateLeads(LeadsRequest updateLeadReq, long leadId);

	SucessResponse removeLeads(Long leadId);

}
