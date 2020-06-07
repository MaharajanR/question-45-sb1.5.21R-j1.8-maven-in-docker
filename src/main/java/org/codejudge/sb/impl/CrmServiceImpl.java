package org.codejudge.sb.impl;

import org.codejudge.sb.dto.UserDto;
import org.codejudge.sb.entity.UserEntity;
import org.codejudge.sb.exceptions.ExceptionHandling;
import org.codejudge.sb.messageutil.ErrorMessages;
import org.codejudge.sb.payload.response.FetchLeadsResponse;
import org.codejudge.sb.payload.response.SucessResponse;
import org.codejudge.sb.repository.UserRepository;
import org.codejudge.sb.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CrmServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto user) {
		UserEntity userEntity = new UserEntity();
		if (userRepository.findByEmail(user.getEmail()) != null)
			throw new ExceptionHandling("Email is already exists ,Please try another one");
		BeanUtils.copyProperties(user, userEntity);
		UserEntity storedUserDetails = userRepository.save(userEntity);
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storedUserDetails, returnValue);
		return returnValue;
	}

	@Override
	public FetchLeadsResponse findById(Long leadId) {
		FetchLeadsResponse fetchLeadsResponse = new FetchLeadsResponse();
		UserEntity crmLeads = userRepository.findOne(leadId);
		if (crmLeads == null)
			throw new ExceptionHandling("Email is already exists ,Please try another one");

		BeanUtils.copyProperties(crmLeads, fetchLeadsResponse);

		return fetchLeadsResponse;
	}

	@Override
	public SucessResponse updateCustomer(UserDto updateLeadReq, long leadId) {
		UserEntity crmLead = userRepository.findOne(leadId);
		if (crmLead == null)
			throw new ExceptionHandling(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		UserEntity crmLeads = new UserEntity();
		BeanUtils.copyProperties(crmLead, crmLeads);
		userRepository.save(crmLeads);
		return new SucessResponse("success");
	}

	@Transactional
	@Override
	public void removeLeads(Long leadId) {
		UserEntity crmLead = userRepository.findOne(leadId);
		if (crmLead == null)
			throw new ExceptionHandling(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		userRepository.delete(leadId);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}

}
