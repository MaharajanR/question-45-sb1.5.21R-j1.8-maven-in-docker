package org.codejudge.sb.impl;

import org.codejudge.sb.entity.Location_type;
import org.codejudge.sb.entity.Status;
import org.codejudge.sb.entity.TbCrmLeads;
import org.codejudge.sb.payload.FetchLeadsResponse;
import org.codejudge.sb.payload.LeadsRequest;
import org.codejudge.sb.payload.SaveLeadsResponse;
import org.codejudge.sb.payload.SucessResponse;
import org.codejudge.sb.repository.CrmLeadsRepo;
import org.codejudge.sb.service.CrmLeadsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrmServiceImpl implements CrmLeadsService {

	@Autowired
	CrmLeadsRepo crmLeadsRepo;
	
	private static final String SUCC = "success";

	@Override
	public SaveLeadsResponse saveLeads(LeadsRequest fetchLeadsRequest) {
		TbCrmLeads crmLeads = new TbCrmLeads();
		if (crmLeadsRepo.findByEmail(fetchLeadsRequest.getEmail()) != null)
			throw new RuntimeException("Email is already exists ,Please try another one");
		BeanUtils.copyProperties(fetchLeadsRequest, crmLeads);
		crmLeads.setLocation_type(Location_type.valueOf(fetchLeadsRequest.getLocation_type()));
		crmLeads.setStatus(Status.CR);
		TbCrmLeads crmLeadsSaved = crmLeadsRepo.save(crmLeads);
		SaveLeadsResponse saveLeadsResponse = new SaveLeadsResponse();
		BeanUtils.copyProperties(crmLeadsSaved, saveLeadsResponse);
		saveLeadsResponse.setLocation_type(crmLeadsSaved.getLocation_type().getValue());
		saveLeadsResponse.setStatus(crmLeadsSaved.getStatus().getValue());
		return saveLeadsResponse;
	}

	public FetchLeadsResponse findByLeadId(Long leadId) {
		FetchLeadsResponse fetchLeadsResponse = new FetchLeadsResponse();
		TbCrmLeads crmLeads = crmLeadsRepo.findOne(leadId);
		if (crmLeads == null)
			throw new RuntimeException("No Record found for the lead id.");

		BeanUtils.copyProperties(crmLeads, fetchLeadsResponse);
		fetchLeadsResponse.setLocation_type(crmLeads.getLocation_type().getValue());
		fetchLeadsResponse.setStatus(crmLeads.getStatus().getValue());
		return fetchLeadsResponse;
	}

	public SucessResponse updateLeads(LeadsRequest updateLeadReq, long leadId) {
		TbCrmLeads crmLead = crmLeadsRepo.findOne(leadId);
		if (crmLead == null)
			throw new RuntimeException("No Record found for the lead id.");

		TbCrmLeads crmLeads = new TbCrmLeads();
		BeanUtils.copyProperties(updateLeadReq, crmLeads);
		crmLeads.setId(leadId);
		crmLeads.setLocation_type(Location_type.valueOf(updateLeadReq.getLocation_type()));
		crmLeads.setStatus(crmLead.getStatus());
		SucessResponse sucessResponse = new SucessResponse();
		crmLeadsRepo.save(crmLeads);
		sucessResponse.setStatus(crmLeadsRepo.save(crmLeads).getStatus().getValue());
		return sucessResponse;
	}

	public SucessResponse removeLeads(Long leadId) {
		crmLeadsRepo.delete(leadId);
		SucessResponse response = new SucessResponse();
		response.setStatus(SUCC);
		return response;
	}

}
