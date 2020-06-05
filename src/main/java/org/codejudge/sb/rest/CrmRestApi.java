package org.codejudge.sb.rest;

import org.codejudge.sb.exceptions.ExceptionHandling;
import org.codejudge.sb.impl.CrmServiceImpl;
import org.codejudge.sb.payload.Request.LeadsRequest;
import org.codejudge.sb.payload.Response.FetchLeadsResponse;
import org.codejudge.sb.payload.Response.SaveLeadsResponse;
import org.codejudge.sb.payload.Response.SucessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/leads")
public class CrmRestApi {

	@Autowired
	CrmServiceImpl crmServiceImpl;

	private static final String LEADEMPT = "Lead id is empty";

	@GetMapping(path = "/{leadId}", produces = "application/json")
	public FetchLeadsResponse getDetailsByLeadId(@PathVariable Long leadId) {
		if (Long.toString(leadId).isEmpty())
			throw new ExceptionHandling(LEADEMPT);
		return crmServiceImpl.findByLeadId(leadId);
	}

	@PostMapping
	public ResponseEntity<SaveLeadsResponse> fetchByLeadId(@RequestBody LeadsRequest fetchLeadsRequest)
			throws Exception {
		SaveLeadsResponse saveLeadsResponse = crmServiceImpl.saveLeads(fetchLeadsRequest);
		return new ResponseEntity<>(saveLeadsResponse, HttpStatus.CREATED);
	}

	@PutMapping(path = "/{leadId}")
	public SucessResponse updateByLeadId(@PathVariable Long leadId, @RequestBody LeadsRequest fetchLeadsRequest) {
		if (Long.toString(leadId).isEmpty())
			throw new ExceptionHandling(LEADEMPT);
		return crmServiceImpl.updateLeads(fetchLeadsRequest, leadId);
	}

	@DeleteMapping(path = "{leadId}")
	public SucessResponse deleteByLeadId(@PathVariable Long leadId) {
		if (Long.toString(leadId).isEmpty())
			throw new RuntimeException(LEADEMPT);
		return crmServiceImpl.removeLeads(leadId);
	}
}
