package org.codejudge.sb.service;

import org.codejudge.sb.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	UserDto createUser(UserDto fetchLeadsRequest);
	UserDto findByUserId(String userid);
	UserDto getUser(String email);
	void removeLeads(Long leadId);

}
	