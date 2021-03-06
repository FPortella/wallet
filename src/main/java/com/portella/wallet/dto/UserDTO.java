package com.portella.wallet.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

public class UserDTO {

	private Long id;
	@Email(message="Email inválido")
	private String email;
	@Length(min=3, max=50, message="O nome deve conter entre 3 e 50 caracteres")
	private String nome;
	@NotNull
	@Length(min=6, message="A senha deve conter no mínimo 6 caracteres")
	private String password;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
