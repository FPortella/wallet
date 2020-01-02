package com.portella.wallet.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.portella.wallet.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTest {
	private static final String EMAIL= "email@teste.com";
	
	@Autowired
	UserRepository repository;
	
	@BeforeEach
	public void onSetUp() {
		System.out.println("SETUP");
		User u= new User();
		u.setNome("Set up User");
		u.setPassword("Senha123");
		u.setEmail(EMAIL);
		repository.save(u);
	}
	
	
	@AfterEach
	public void onTearDown() {
		System.out.println("TEAR DOWN");
		repository.deleteAll();
	}
	
	@Test
	public void testSave() {
		System.out.println("TEST SAVE");
		User  u  =new User();
		u.setNome("Teste");
		u.setPassword("123456");
		u.setEmail("test@teste.com");
		
		User response = repository.save(u);
		
		assertNotNull(response);
	}
	
	@Test
	public void testFindByEmail() {
		System.out.println("TESTE FIND BY EMAIL");
		Optional<User> response = repository.findByEmailEquals(EMAIL);
		assertTrue(response.isPresent());
		assertEquals(response.get().getEmail(), EMAIL);
	}
	
}
