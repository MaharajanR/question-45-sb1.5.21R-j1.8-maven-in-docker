package org.codejudge.sb.service;

import org.codejudge.sb.entity.Location_type;
import org.codejudge.sb.entity.Status;
import org.codejudge.sb.entity.TbCrmLeads;
import org.codejudge.sb.payload.FetchLeadsRequest;
import org.codejudge.sb.payload.FetchLeadsResponse;
import org.codejudge.sb.payload.MarkUpdateRequest;
import org.codejudge.sb.payload.MarkUpdateResponse;
import org.codejudge.sb.payload.SaveLeadsResponse;
import org.codejudge.sb.payload.SucessResponse;
import org.codejudge.sb.repository.CrmLeadsRepo;
import org.codejudge.sb.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrmLeadsService {

	@Autowired
	CrmLeadsRepo crmLeadsRepo;

	private static final String SUCC = "success";

	public SaveLeadsResponse saveLeads(FetchLeadsRequest fetchLeadsRequest) {
		TbCrmLeads crmLeads = new TbCrmLeads();
		crmLeads.setId(CommonUtil.getRandomValue());
		crmLeads.setEmail(fetchLeadsRequest.getEmail());
		crmLeads.setFirst_name(fetchLeadsRequest.getFirst_name());
		crmLeads.setLast_name(fetchLeadsRequest.getLast_name());
		crmLeads.setLocation_type(Location_type.valueOf(fetchLeadsRequest.getLocation_type()));
		crmLeads.setLocation_string(fetchLeadsRequest.getLocation_string());
		crmLeads.setMobile(fetchLeadsRequest.getMobile());
		crmLeads.setStatus(Status.CR);
		TbCrmLeads crmLeadsSaved = crmLeadsRepo.save(crmLeads);
		SaveLeadsResponse saveLeadsResponse = new SaveLeadsResponse();
		saveLeadsResponse.setEmail(crmLeadsSaved.getEmail());
		saveLeadsResponse.setFirst_name(crmLeadsSaved.getFirst_name());
		saveLeadsResponse.setId(crmLeadsSaved.getId() + "");
		saveLeadsResponse.setLast_name(crmLeadsSaved.getLast_name());
		saveLeadsResponse.setLocation_string(crmLeadsSaved.getLocation_string());
		saveLeadsResponse.setLocation_type(crmLeadsSaved.getLocation_type().getValue());
		saveLeadsResponse.setMobile(crmLeadsSaved.getMobile() + "");
		saveLeadsResponse.setStatus(crmLeadsSaved.getStatus().getValue());
		return saveLeadsResponse;
	}

	public FetchLeadsResponse findByLeadId(long leadId) {
		FetchLeadsResponse fetchLeadsResponse = new FetchLeadsResponse();
		TbCrmLeads crmLeads = crmLeadsRepo.findById(leadId);
		if (crmLeads != null) {
			fetchLeadsResponse.setCommunication(crmLeads.getCommunication());
			fetchLeadsResponse.setEmail(crmLeads.getEmail());
			fetchLeadsResponse.setFirst_name(crmLeads.getFirst_name());
			fetchLeadsResponse.setLast_name(crmLeads.getLast_name());
			fetchLeadsResponse.setLocation_string(crmLeads.getLocation_string());
			fetchLeadsResponse.setLocation_type(crmLeads.getLocation_type().getValue());
			fetchLeadsResponse.setMobile(crmLeads.getMobile() + "");
			fetchLeadsResponse.setStatus(crmLeads.getStatus().getValue());
			return fetchLeadsResponse;
		}
		return null;
	}

	public SucessResponse updateLeads(FetchLeadsRequest fetchLeadsRequest, long leadId) {
		TbCrmLeads crmLead = crmLeadsRepo.findById(leadId);
		TbCrmLeads crmLeads = new TbCrmLeads();
		SucessResponse sucessResponse = new SucessResponse();
		if (crmLead != null) {
			crmLeads.setId(leadId);
			crmLeads.setEmail(fetchLeadsRequest.getEmail());
			crmLeads.setFirst_name(fetchLeadsRequest.getFirst_name());
			crmLeads.setLast_name(fetchLeadsRequest.getLast_name());
			crmLeads.setLocation_type(Location_type.valueOf(fetchLeadsRequest.getLocation_type()));
			crmLeads.setLocation_string(fetchLeadsRequest.getLocation_string());
			crmLeads.setMobile(fetchLeadsRequest.getMobile());
			crmLeads.setStatus(Status.CR);
			crmLeadsRepo.delete(crmLead);
			crmLeadsRepo.save(crmLeads);
			sucessResponse.setStatus(SUCC);
			return sucessResponse;
		}
		return null;
	}

	public SucessResponse removeLeads(Long leadId) {
		crmLeadsRepo.delete(leadId);
		SucessResponse response = new SucessResponse();
		response.setStatus(SUCC);
		return response;
	}

	public MarkUpdateResponse markUpdate(MarkUpdateRequest markUpdateRequest, long leadId) {
		TbCrmLeads crmLead = crmLeadsRepo.findById(leadId);
		MarkUpdateResponse markUpdateResponse = new MarkUpdateResponse();
		if (crmLead != null) {
			TbCrmLeads crmLeads = new TbCrmLeads();
			crmLeads.setId(leadId);
			crmLeads.setEmail(crmLead.getEmail());
			crmLeads.setFirst_name(crmLead.getFirst_name());
			crmLeads.setLast_name(crmLead.getLast_name());
			crmLeads.setLocation_type(crmLead.getLocation_type());
			crmLeads.setLocation_string(crmLead.getLocation_string());
			crmLeads.setMobile(crmLead.getMobile());
			crmLeads.setStatus(Status.CO);
			crmLeads.setCommunication(markUpdateRequest.getCommunication());
			crmLeadsRepo.delete(crmLead);
			TbCrmLeads updateTb = crmLeadsRepo.save(crmLeads);
			markUpdateResponse.setStatus(updateTb.getStatus().getValue());
			markUpdateResponse.setCommunication(updateTb.getCommunication());
			return markUpdateResponse;
		}
		return null;
	}
}
