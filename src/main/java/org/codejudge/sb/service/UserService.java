package org.codejudge.sb.service;

import org.codejudge.sb.payload.request.UserDto;
import org.codejudge.sb.payload.response.FetchLeadsResponse;
import org.codejudge.sb.payload.response.SaveLeadsResponse;
import org.codejudge.sb.payload.response.SucessResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{

	SaveLeadsResponse saveLeads(UserDto fetchLeadsRequest);

	FetchLeadsResponse findByLeadId(Long leadId);

	SucessResponse updateLeads(UserDto updateLeadReq, long leadId);

	void removeLeads(Long leadId);


}
