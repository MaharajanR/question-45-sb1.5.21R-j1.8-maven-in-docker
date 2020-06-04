package org.codejudge.sb.rest;

import java.lang.invoke.MethodHandles;

import org.codejudge.sb.payload.EmptyJsonResponse;
import org.codejudge.sb.payload.ErrorResponse;
import org.codejudge.sb.payload.FetchLeadsRequest;
import org.codejudge.sb.payload.FetchLeadsResponse;
import org.codejudge.sb.payload.MarkUpdateRequest;
import org.codejudge.sb.payload.MarkUpdateResponse;
import org.codejudge.sb.payload.SaveLeadsResponse;
import org.codejudge.sb.payload.SucessResponse;
import org.codejudge.sb.service.CrmLeadsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;

import lombok.extern.slf4j.Slf4j;

@RestController
@Component
@RequestMapping("/api")
@Slf4j
@SessionScope
public class CrmRestApi {
	private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
	private static final String FAIL = "failure";
	@Autowired
	CrmLeadsService crmLeadSer;

	@GetMapping(value = "/leads/{leadId}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Object> getDetailsByLeadId(@PathVariable Long leadId) {
		FetchLeadsResponse fetchLeadsResponseWrapper = null;
		try {
			if (Long.toString(leadId).isEmpty()) {
				ErrorResponse errorResponse = new ErrorResponse();
				errorResponse.setReason("Lead id is mandatory for fetching" + leadId);
				errorResponse.setStatus(FAIL);
				return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
			} else {
				fetchLeadsResponseWrapper = crmLeadSer.findByLeadId(leadId);
				if (fetchLeadsResponseWrapper != null) {
					return new ResponseEntity<>(fetchLeadsResponseWrapper, HttpStatus.OK);
				}

			}
		} catch (Exception e) {
			LOG.error("getDetailsByLeadId Exception occure", e);
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setReason(e.getMessage());
			errorResponse.setStatus(FAIL);
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new EmptyJsonResponse(),HttpStatus.NOT_FOUND);
	}

	@PostMapping(value = "/leads", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Object> fecthByLeadId(@RequestBody FetchLeadsRequest fetchLeadsRequest) {
		SaveLeadsResponse saveLeadsResponse = null;
		try {
			saveLeadsResponse = crmLeadSer.saveLeads(fetchLeadsRequest);
		} catch (Exception e) {
			LOG.error("fecthByLeadId Exception occure", e);
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setReason(e.getMessage());
			errorResponse.setStatus(FAIL);
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(saveLeadsResponse, HttpStatus.CREATED);
	}

	@PutMapping(value = "/leads/{leadId}", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Object> updateByLeadId(@PathVariable Long leadId,
			@RequestBody FetchLeadsRequest fetchLeadsRequest) {
		SucessResponse sucessResponse = null;
		try {
			if (Long.toString(leadId).isEmpty()) {
				ErrorResponse errorResponse = new ErrorResponse();
				errorResponse.setReason("Lead id is mandatory for update");
				errorResponse.setStatus(FAIL);
				return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
			} else {
				sucessResponse = crmLeadSer.updateLeads(fetchLeadsRequest, leadId);
				if (sucessResponse == null) {
					ErrorResponse errorResponse = new ErrorResponse();
					errorResponse.setReason("Lead id is not present in DataBase,Kindly check" + leadId);
					errorResponse.setStatus(FAIL);
					return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
				}
			}
		} catch (Exception e) {
			LOG.error("updateByLeadId Exception occure", e);
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setReason(e.getMessage());
			errorResponse.setStatus(FAIL);
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(sucessResponse, HttpStatus.ACCEPTED);
	}

	@DeleteMapping(value = "/leads/{leadId}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Object> deleteByLeadId(@PathVariable Long leadId) {
		SucessResponse sucessResponse = null;
		try {
			if (Long.toString(leadId).isEmpty()) {
				ErrorResponse errorResponse = new ErrorResponse();
				errorResponse.setReason("Lead id is mandatory to delete the record" + leadId);
				errorResponse.setStatus(FAIL);
				return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
			} else {
				sucessResponse = crmLeadSer.removeLeads(leadId);
				if (sucessResponse == null) {
					ErrorResponse errorResponse = new ErrorResponse();
					errorResponse.setReason("Lead id is not present in DataBase,Kindly check");
					errorResponse.setStatus(FAIL);
					return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
				}
			}

		} catch (Exception e) {
			LOG.error("deleteByLeadId Exception occure", e);
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setReason(e.getMessage());
			errorResponse.setStatus(FAIL);
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(sucessResponse, HttpStatus.OK);
	}

	@PutMapping(value = "/api/mark_lead/{leadId}", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Object> updateMarkByLeadId(@PathVariable Long leadId,
			@RequestBody MarkUpdateRequest markUpdateRequest) {
		MarkUpdateResponse markUpdateResponse = null;
		try {
			if (Long.toString(leadId).isEmpty()) {
				ErrorResponse errorResponse = new ErrorResponse();
				errorResponse.setReason("Lead id is mandatory for update");
				errorResponse.setStatus(FAIL);
				return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
			} else {
				markUpdateResponse = crmLeadSer.markUpdate(markUpdateRequest, leadId);
				if (markUpdateResponse == null) {
					ErrorResponse errorResponse = new ErrorResponse();
					errorResponse.setReason("No Record found for the lead Id " + leadId);
					errorResponse.setStatus(FAIL);
					return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
				}
			}
		} catch (Exception e) {
			LOG.error("updateByLeadId Exception occure", e);
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setReason(e.getMessage());
			errorResponse.setStatus(FAIL);
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(markUpdateResponse, HttpStatus.ACCEPTED);
	}
}
