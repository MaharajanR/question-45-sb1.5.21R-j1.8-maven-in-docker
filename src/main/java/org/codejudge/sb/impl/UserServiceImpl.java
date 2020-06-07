package org.codejudge.sb.impl;

import java.util.ArrayList;

import org.codejudge.sb.dto.UserDto;
import org.codejudge.sb.entity.UserEntity;
import org.codejudge.sb.exceptions.ExceptionHandling;
import org.codejudge.sb.messageutil.ErrorMessages;
import org.codejudge.sb.repository.UserRepository;
import org.codejudge.sb.service.UserService;
import org.codejudge.sb.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	Utils utils;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto user) {
		UserEntity userEntity = new UserEntity();
		if (userRepository.findByEmail(user.getEmail()) != null)
			throw new ExceptionHandling(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
		BeanUtils.copyProperties(user, userEntity);
		String publicUserId = utils.generateUserId(30);
		userEntity.setUserId(publicUserId);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		UserEntity storedUserDetails = userRepository.save(userEntity);
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storedUserDetails, returnValue);
		return returnValue;
	}

	@Transactional
	@Override
	public void removeLeads(Long leadId) {
		userRepository.delete(leadId);

	}

	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new ExceptionHandling(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}

	public UserDto findByUserId(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)
			throw new ExceptionHandling(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

}
