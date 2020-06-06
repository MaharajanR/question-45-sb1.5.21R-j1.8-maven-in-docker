package org.codejudge.sb.impl;

import org.codejudge.sb.entity.UserEntity;
import org.codejudge.sb.exceptions.ExceptionHandling;
import org.codejudge.sb.messageutil.Messages;
import org.codejudge.sb.payload.request.UserDto;
import org.codejudge.sb.payload.response.FetchLeadsResponse;
import org.codejudge.sb.payload.response.SaveLeadsResponse;
import org.codejudge.sb.payload.response.SucessResponse;
import org.codejudge.sb.repository.CrmLeadsRepo;
import org.codejudge.sb.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CrmServiceImpl implements UserService {

	@Autowired
	CrmLeadsRepo crmLeadsRepo;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public SaveLeadsResponse saveLeads(UserDto fetchLeadsRequest) {
		UserEntity crmLeads = new UserEntity();
		if (crmLeadsRepo.findByEmail(fetchLeadsRequest.getEmail()) != null)
			throw new ExceptionHandling("Email and Mobile is already exists ,Please try another one");
		BeanUtils.copyProperties(fetchLeadsRequest, crmLeads);
		UserEntity crmLeadsSaved = crmLeadsRepo.save(crmLeads);
		SaveLeadsResponse saveLeadsResponse = new SaveLeadsResponse();
		BeanUtils.copyProperties(crmLeadsSaved, saveLeadsResponse);
		return saveLeadsResponse;
	}

	@Override
	public FetchLeadsResponse findByLeadId(Long leadId) {
		FetchLeadsResponse fetchLeadsResponse = new FetchLeadsResponse();
		UserEntity crmLeads = crmLeadsRepo.findOne(leadId);
		if (crmLeads == null)
			throw new ExceptionHandling("Email and Mobile is already exists ,Please try another one");

		BeanUtils.copyProperties(crmLeads, fetchLeadsResponse);

		return fetchLeadsResponse;
	}

	@Override
	public SucessResponse updateLeads(UserDto updateLeadReq, long leadId) {
		UserEntity crmLead = crmLeadsRepo.findOne(leadId);
		if (crmLead == null)
			throw new ExceptionHandling(Messages.NO_RCRD_FND.getMessage());
		UserEntity crmLeads = new UserEntity();
		BeanUtils.copyProperties(crmLead, crmLeads);
		crmLeadsRepo.save(crmLeads);
		return new SucessResponse(Messages.SUCC.getMessage());
	}

	@Override
	public void removeLeads(Long leadId) {
		UserEntity crmLead = crmLeadsRepo.findOne(leadId);
		if (crmLead == null)
			throw new ExceptionHandling(Messages.NO_RCRD_FND.getMessage());
		crmLeadsRepo.delete(leadId);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}

}
