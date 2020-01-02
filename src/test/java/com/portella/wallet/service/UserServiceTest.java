package com.portella.wallet.service;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.portella.wallet.entity.User;
import com.portella.wallet.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

	@MockBean
	UserRepository repository;
	
	@Autowired
	UserService service;
	
	@BeforeEach
	public void onSetUp() {
		BDDMockito.given(repository.findByEmailEquals(Mockito.anyString()))
			.willReturn(Optional.of(new User()));
	}
	
	@Test
	public void testFindEmail() {
		Optional<User> user = service.findByEmail("email@teste.com");
		assertTrue(user.isPresent());
	}
}