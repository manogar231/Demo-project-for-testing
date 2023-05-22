package com.demo.coreproject.serviceimpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.coreproject.dto.Userdto;
import com.demo.coreproject.entity.User;
import com.demo.coreproject.repository.UserRepository;
import com.demo.coreproject.service.UserService;

@Component
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper;

	
	public Object saveUserInformation(Userdto userdto) {
		User users = new User();
		users.setUsername(userdto.getUsername());
		users.setUseraddress(userdto.getUseraddress());
		users.setMail(userdto.getMail());
		users.setPassword(userdto.getPassword());
		return userRepository.save(users);
	}

	public Userdto findUserById(int id) throws Exception {

		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			System.out.println("User is Not Available");
		}
		return modelMapper.map(user.get(),Userdto.class);
	}

	public List<Userdto> getAlluser() {
		List<User> users = userRepository.findAll();
		return users.stream().map(User -> modelMapper.map(User, Userdto.class)).collect(Collectors.toList());
	}

	public String deleteUserbyid(int id) {

		Optional<User> user1 = userRepository.findById(id);
		if (user1.isEmpty()) {
			return "User Not Found !!";
		}
		userRepository.deleteById(id);
		return "User deleted successfully";
	}

	public User updateUserById(int id, Userdto userDTO) throws Exception {
		User user1 = userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));

		if (Objects.nonNull(userDTO.getUsername())) {
			user1.setUsername(userDTO.getUsername());
		}
		if (Objects.nonNull(userDTO.getUseraddress())) {
			user1.setUseraddress(userDTO.getUseraddress());
		}
		return userRepository.save(user1);
	}



}
