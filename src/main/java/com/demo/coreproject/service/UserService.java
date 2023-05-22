package com.demo.coreproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.coreproject.dto.Userdto;
import com.demo.coreproject.entity.User;

@Service
public interface UserService {

	
	public Object saveUserInformation(Userdto userdto);

	public Userdto findUserById(int id) throws Exception;

	public List<Userdto> getAlluser();

	public String deleteUserbyid(int id);

	public User updateUserById(int id, Userdto userDTO) throws Exception;
	
}
