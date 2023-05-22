package com.demo.coreproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.coreproject.dto.Userdto;
import com.demo.coreproject.service.UserService;

@Controller
@RequestMapping("api/user")
public class UserController {

	@Autowired
	private UserService userservices;

	@PostMapping("/save")
	public ResponseEntity<Object> saveUser(@RequestBody Userdto userdto) {
		return ResponseEntity.ok().body(userservices.saveUserInformation(userdto));
	}

	@GetMapping("/{id}")
	public Userdto finduserbyid(@PathVariable Integer id) throws Exception {
		return userservices.findUserById(id);
	}

	@GetMapping("/alluser")
	public List<Userdto> getallUsers() {
		return userservices.getAlluser();
	}

	@PutMapping("/update/{id}")
	public Object updateuser(@PathVariable int id, @RequestBody Userdto user) throws Exception {
		return userservices.updateUserById(id, user);
	}

	@DeleteMapping("/delete/{id}")
	public Object deleteuser(@PathVariable Integer id) {
		return userservices.deleteUserbyid(id);
	}

}
