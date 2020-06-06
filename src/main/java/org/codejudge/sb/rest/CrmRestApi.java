package org.codejudge.sb.rest;

import org.codejudge.sb.exceptions.ExceptionHandling;
import org.codejudge.sb.impl.CrmServiceImpl;
import org.codejudge.sb.messageutil.Messages;
import org.codejudge.sb.payload.request.UserDto;
import org.codejudge.sb.payload.response.FetchLeadsResponse;
import org.codejudge.sb.payload.response.SaveLeadsResponse;
import org.codejudge.sb.payload.response.SucessResponse;
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
@RequestMapping("/leads")
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

	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<SaveLeadsResponse> fetchByLeadId(@RequestBody UserDto fetchLeadsRequest) {
		SaveLeadsResponse saveLeadsResponse = crmServiceImpl.saveLeads(fetchLeadsRequest);
		return new ResponseEntity<>(saveLeadsResponse, HttpStatus.CREATED);
	}

	@PutMapping(path = "{leadId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<SucessResponse> updateByLeadId(@PathVariable Long leadId,
			@RequestBody UserDto fetchLeadsRequest) {
		if (Long.toString(leadId).isEmpty())
			throw new ExceptionHandling(LEADEMPT);
		SucessResponse sucessResponse = crmServiceImpl.updateLeads(fetchLeadsRequest, leadId);
		return new ResponseEntity<>(sucessResponse, HttpStatus.ACCEPTED);
	}

	@DeleteMapping(path = "{leadId}")
	public SucessResponse deleteByLeadId(@PathVariable Long leadId) throws ExceptionHandling {
		if (Long.toString(leadId).isEmpty())
			throw new ExceptionHandling(LEADEMPT);
		crmServiceImpl.removeLeads(leadId);
		return new SucessResponse(Messages.SUCC.getMessage());
	}
}
