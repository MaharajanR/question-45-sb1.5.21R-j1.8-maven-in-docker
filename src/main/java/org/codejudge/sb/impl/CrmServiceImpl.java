package org.codejudge.sb.impl;

import org.codejudge.sb.entity.Location_type;
import org.codejudge.sb.entity.Status;
import org.codejudge.sb.entity.TbCrmLeads;
import org.codejudge.sb.exceptions.ExceptionHandling;
import org.codejudge.sb.messageutil.Messages;
import org.codejudge.sb.payload.request.LeadsRequest;
import org.codejudge.sb.payload.response.FetchLeadsResponse;
import org.codejudge.sb.payload.response.SaveLeadsResponse;
import org.codejudge.sb.payload.response.SucessResponse;
import org.codejudge.sb.repository.CrmLeadsRepo;
import org.codejudge.sb.service.CrmLeadsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrmServiceImpl implements CrmLeadsService {

	@Autowired
	CrmLeadsRepo crmLeadsRepo;

	@Override
	public SaveLeadsResponse saveLeads(LeadsRequest fetchLeadsRequest) {
		TbCrmLeads crmLeads = new TbCrmLeads();
		if (crmLeadsRepo.findByEmail(fetchLeadsRequest.getEmail()) != null
				|| crmLeadsRepo.findByMobile(fetchLeadsRequest.getMobile()) != null)
			throw new ExceptionHandling("Email and Mobile is already exists ,Please try another one");

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

	@Override
	public FetchLeadsResponse findByLeadId(Long leadId) {
		FetchLeadsResponse fetchLeadsResponse = new FetchLeadsResponse();
		TbCrmLeads crmLeads = crmLeadsRepo.findOne(leadId);
		if (crmLeads == null)
			throw new ExceptionHandling("Email and Mobile is already exists ,Please try another one");

		BeanUtils.copyProperties(crmLeads, fetchLeadsResponse);
		fetchLeadsResponse.setLocation_type(crmLeads.getLocation_type().getValue());
		fetchLeadsResponse.setStatus(crmLeads.getStatus().getValue());
		return fetchLeadsResponse;
	}

	@Override
	public SucessResponse updateLeads(LeadsRequest updateLeadReq, long leadId) {
		TbCrmLeads crmLead = crmLeadsRepo.findOne(leadId);
		if (crmLead == null)
			throw new ExceptionHandling(Messages.NO_RCRD_FND.getMessage());
		TbCrmLeads crmLeads = new TbCrmLeads();
		BeanUtils.copyProperties(crmLead, crmLeads);
		if (updateLeadReq.getEmail() != null) {
			crmLeads.setEmail(updateLeadReq.getEmail());
		}
		if (updateLeadReq.getFirst_name() != null) {
			crmLeads.setFirst_name(updateLeadReq.getFirst_name());
		}
		if (updateLeadReq.getLast_name() != null) {
			crmLeads.setLast_name(updateLeadReq.getLast_name());
		}
		if (updateLeadReq.getLocation_string() != null) {
			crmLeads.setLocation_string(updateLeadReq.getLocation_string());
		}
		if (updateLeadReq.getLocation_type() != null) {
			crmLeads.setLocation_type(Location_type.valueOf(updateLeadReq.getLocation_type()));
		}
		if (updateLeadReq.getMobile() != null) {
			crmLeads.setMobile(updateLeadReq.getMobile());
		}
		crmLeadsRepo.save(crmLeads);
		return new SucessResponse(Messages.SUCC.getMessage());
	}

	@Override
	public void removeLeads(Long leadId) {
		TbCrmLeads crmLead = crmLeadsRepo.findOne(leadId);
		if (crmLead == null)
			throw new ExceptionHandling(Messages.NO_RCRD_FND.getMessage());
		crmLeadsRepo.delete(leadId);
	}

	@Override
	public SucessResponse updateCommuniCationLeads(LeadsRequest updateLeadReq, long leadId) {
		TbCrmLeads crmLead = crmLeadsRepo.findOne(leadId);
		if (crmLead == null)
			throw new ExceptionHandling(Messages.NO_RCRD_FND.getMessage());
		TbCrmLeads crmLeads = new TbCrmLeads();
		BeanUtils.copyProperties(crmLead, crmLeads);
		if (updateLeadReq.getEmail() != null) {
			crmLeads.setEmail(updateLeadReq.getEmail());
		}
		if (updateLeadReq.getFirst_name() != null) {
			crmLeads.setFirst_name(updateLeadReq.getFirst_name());
		}
		if (updateLeadReq.getLast_name() != null) {
			crmLeads.setLast_name(updateLeadReq.getLast_name());
		}
		if (updateLeadReq.getLocation_string() != null) {
			crmLeads.setLocation_string(updateLeadReq.getLocation_string());
		}
		if (updateLeadReq.getLocation_type() != null) {
			crmLeads.setLocation_type(Location_type.valueOf(updateLeadReq.getLocation_type()));
		}
		if (updateLeadReq.getMobile() != null) {
			crmLeads.setMobile(updateLeadReq.getMobile());
		}
		if (updateLeadReq.getCommunication() != null) {
			crmLeads.setCommunication(updateLeadReq.getCommunication());
		} else {
			throw new ExceptionHandling("Communication detail is mandatory");
		}
		crmLeads.setStatus(Status.CO);
		TbCrmLeads updatedCrmLead = crmLeadsRepo.save(crmLeads);
		return new SucessResponse(Status.CO.getValue(), updatedCrmLead.getCommunication());
	}

}
