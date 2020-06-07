package org.codejudge.sb.controller;

import org.codejudge.sb.dto.UserDto;
import org.codejudge.sb.exceptions.ExceptionHandling;
import org.codejudge.sb.impl.UserServiceImpl;
import org.codejudge.sb.payload.request.UserDetailRequestModel;
import org.codejudge.sb.payload.response.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	UserServiceImpl userServiceImpl;

	private static final String LEADEMPT = "Lead id is empty";

	@GetMapping(path = "/{userId}", produces = "application/json")
	public UserRest getDetailsByLeadId(@PathVariable String userId) {
		if (userId.isEmpty())
			throw new ExceptionHandling(LEADEMPT);
		UserRest userRest = new UserRest();
		UserDto returnValue = userServiceImpl.findByUserId(userId);
		BeanUtils.copyProperties(returnValue, userRest);
		return userRest;
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	public UserRest createUser(@RequestBody UserDetailRequestModel userDetailRequestModel) {
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetailRequestModel, userDto);
		UserDto returnValue = userServiceImpl.createUser(userDto);
		UserRest userRest = new UserRest();
		BeanUtils.copyProperties(returnValue, userRest);
		return userRest;
	}

	@PutMapping(path = "{leadId}", consumes = "application/json", produces = "application/json")
	public UserRest updateByLeadId(@PathVariable Long leadId, @RequestBody UserDto fetchLeadsRequest) {
		return null;
	}

	@DeleteMapping(path = "{leadId}")
	public UserRest deleteByLeadId(@PathVariable Long leadId) throws ExceptionHandling {
		return null;
	}
}
