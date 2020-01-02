package com.portella.wallet.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portella.wallet.dto.UserDTO;
import com.portella.wallet.entity.User;
import com.portella.wallet.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {
	private static final Long ID= 1L;
	private static final String EMAIL="email@teste.com";
	private static final String NOME="User Test";
	private static final String PASSWORD = "123456";
	private static final String URL = "/user";
	
	
	@MockBean
	UserService service;
	
	@Autowired
	MockMvc mvc;

	@Test
	public void testSave() throws JsonProcessingException, Exception {
		//Em Testes unitários, tem que faze ro mock do servico, indicanod o que será executado.
		BDDMockito.given(service.save(Mockito.any(User.class))).willReturn(getMockUser());
		
		mvc.perform(MockMvcRequestBuilders.post(URL)
				.content(getJsonPayload(ID, EMAIL, NOME, PASSWORD))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.data.id").value(ID))
			.andExpect(jsonPath("$.data.email").value(EMAIL))
			.andExpect(jsonPath("$.data.nome").value(NOME))
			.andExpect(jsonPath("$.data.password").value(PASSWORD));
	}
	
	@Test
	public void testSaveInvalidUser() throws JsonProcessingException, Exception {
		//Em Testes unitários, tem que faze ro mock do servico, indicanod o que será executado.
		BDDMockito.given(service.save(Mockito.any(User.class))).willReturn(getMockUser());
		
		mvc.perform(MockMvcRequestBuilders.post(URL)
				.content(getJsonPayload(ID, "email", NOME, PASSWORD))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.errors[0]").value("Email inválido"));
	}
	
	public User getMockUser() {
		User u = new User();
		u.setId(ID);
		u.setEmail(EMAIL);
		u.setNome(NOME);
		u.setPassword(PASSWORD);
		return u;
	}
	
	public String getJsonPayload(Long id, String email, String nome, String password) throws JsonProcessingException {
		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setEmail(email);
		dto.setNome(nome);
		dto.setPassword(password);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(dto);
	}
}
