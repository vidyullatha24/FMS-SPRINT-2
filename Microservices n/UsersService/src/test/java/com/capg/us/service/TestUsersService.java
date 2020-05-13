package com.capg.us.service;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.us.dto.Users;

@SpringBootTest
class TestUsersService {
	@Autowired
	UsersService usersService;

	@Test
	public void testGetUser_Positive() throws Exception
	{
		Optional<Users> flight = usersService.getUser("Vinoothna");
		Assertions.assertEquals(true,flight.isPresent());
	}

	@Test
	public void testGetUser_Negative() throws Exception
	{
		Optional<Users> users = usersService.getUser("xyz");
		Assertions.assertEquals(false,users.isPresent());
	}

	@Test
	public void testAddUser_Positive() throws Exception
	{
		Users users = new Users("S","V","SV",'M',"SV@gmail.com",9874563110L);

		Users u = usersService.addUser(users);
		Assertions.assertEquals(u.getUsername(),users.getUsername());
	}


}
