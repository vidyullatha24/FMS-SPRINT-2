package com.capg.us;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.capg.us.dto.Users;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class UsersServiceApplicationTests 
{
	@Autowired
	TestRestTemplate testRestTemplate;
	public void setTestRestTemplate(TestRestTemplate testRestTemplate)
	{
		this.testRestTemplate=testRestTemplate;
	}
	@LocalServerPort
	int localServerPort;
	@Test
	public void testLogin_Positive() throws Exception
	{
		 String url="http://localhost:"+localServerPort+"login/Vinoothna/vvv";
		 ResponseEntity<Users> users = testRestTemplate.getForEntity(url,Users.class);
		 Assertions.assertEquals(200, users.getStatusCodeValue());
	}
	
	@Test
	public void testLogin_Negative() throws Exception
	{
		 String url="http://localhost:"+localServerPort+"login/Arun/monkey";
		 ResponseEntity<Users> users = testRestTemplate.getForEntity(url,Users.class);
		 Assertions.assertEquals(404, users.getStatusCodeValue());
	}
	
	@Test
	public void testRegister_Positive() throws Exception
	{
		 String url="http://localhost:"+localServerPort+"register";
		 Users users = new Users("Arun","Arun","N M Arun",'M',"atortouton@gmail.com",9494969622L);
		 ResponseEntity<String> response = testRestTemplate.postForEntity(url,users,String.class);
		 Assertions.assertEquals(200, response.getStatusCodeValue());
	}
	@Test
	public void testRegister_Negative() throws Exception
	{
		 String url="http://localhost:"+localServerPort+"register";
		 Users users = null;
		 ResponseEntity<String> response = testRestTemplate.postForEntity(url,users,String.class);
		 Assertions.assertEquals(415, response.getStatusCodeValue());
	}
}
