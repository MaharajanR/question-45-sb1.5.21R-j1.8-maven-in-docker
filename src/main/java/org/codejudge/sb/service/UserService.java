package org.codejudge.sb.service;

import org.codejudge.sb.dto.UserDto;
import org.codejudge.sb.payload.response.FetchLeadsResponse;
import org.codejudge.sb.payload.response.SucessResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{

	UserDto createUser(UserDto fetchLeadsRequest);

	FetchLeadsResponse findById(Long leadId);

	SucessResponse updateCustomer(UserDto updateLeadReq, long leadId);

	void removeLeads(Long leadId);


}
