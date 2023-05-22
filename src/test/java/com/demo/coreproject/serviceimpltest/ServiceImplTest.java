package com.demo.coreproject.serviceimpltest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

//import static org.junit.jupiter.api.Assertions.assertEqualspiter.api.Test;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.demo.coreproject.dto.Userdto;
import com.demo.coreproject.entity.User;
import com.demo.coreproject.repository.UserRepository;
import com.demo.coreproject.serviceimpl.UserServiceImpl;

@SpringBootTest
public class ServiceImplTest {
	@MockBean
	UserRepository repository ;
	@Autowired
	UserServiceImpl userServiceImpl;
	@MockBean
	ModelMapper modelMapper;
	
	@Test
	public void saveuserinformation_ThenReturntheEntity() {

        // Create a userdto entity
        Userdto userdto = new Userdto();
        userdto.setUseraddress("pondy");
        userdto.setUsername("jack");
        userdto.setMail("jack@gmail.com");
        userdto.setPassword("123");
        
        //create a user entity
        User user = new User();
        user.setUserid(1);
        user.setUsername(userdto.getUsername());
        user.setMail(userdto.getMail());
        user.setUseraddress(userdto.getUseraddress());
        user.setPassword(userdto.getPassword());
        
        // Save the user
       Object savedUser= userServiceImpl.saveUserInformation(userdto);
	   Userdto userDto	= modelMapper.map(savedUser,Userdto.class);

        // Assert that the saved user matches the original user
        assertEquals(user.getUserid(), 1);
        assertEquals(user.getUsername(), ((Userdto) userDto).getUsername());
        assertEquals(user.getMail(), ((Userdto) userDto).getMail());
        assertEquals(user.getUseraddress(),((Userdto)userDto).getUseraddress());
        assertEquals(user.getPassword(),((Userdto)userDto).getPassword());

    }
	@Test
	public void findUserbyidTest() throws Exception  {
		
		int userid=1;
		
		 User user = new User();
	        user.setUserid(userid);
	        user.setUsername("jack");
	        user.setMail("jack@gmail.com");
	        user.setUseraddress("pondy");
	        user.setPassword("123");
	        
	        //mock the repository to use the findbyid method
	        Mockito.when(repository.findById(userid)).thenReturn(Optional.of(user));
	          
			Userdto founduser= userServiceImpl.findUserById(userid);
          assertEquals(user.getUserid(), 1 );
          assertEquals(user.getUsername(),founduser.getUsername());
          assertEquals(user.getUseraddress(),founduser.getUseraddress());
          assertEquals(user.getPassword(), founduser.getPassword());
          assertEquals(user.getMail(),founduser.getMail());
             
	}
	@Test
	public void deleteuserbyidTest() {
		int userid=1;
		
		User deletedUser=new User();
		deletedUser.setUserid(userid);
		//mock the repository for call the findbyid method 
        Mockito.when(repository.findById(userid)).thenReturn(Optional.of(deletedUser));
        
      String response=userServiceImpl.deleteUserbyid(userid);
      
      assertEquals("User deleted successfully", response);
      
	}
	@Test
	public void getalluserlistTest() {
		  // Create a list of sample User objects
        List<User> users = Arrays.asList(
                new User(1, "jack", "jack@gmail.com", "pondy", null, null, "123", null, null),
                new User(2, "peter", "peter@email.com", "pondy", null, null, "345", null, null)
        );
        // Mock the behavior of the UserRepository
      Mockito.when(repository.findAll()).thenReturn(users);
        List<Userdto> userdtos = userServiceImpl.getAlluser();
      Mockito.verify(repository,Mockito.times(1)).findAll();

        // Assert that the returned list is not null and has the expected size
        assertNotNull(userdtos);
        assertEquals(users.size(), userdtos.size());
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            Userdto userdto = userdtos.get(i);  
            assertEquals(user.getUsername(), userdto.getUsername());
            assertEquals(user.getMail(), userdto.getMail());
            assertEquals(user.getUseraddress(), userdto.getUseraddress());
            assertEquals(user.getPassword(), userdto.getPassword());
        }
    }
}  
	

