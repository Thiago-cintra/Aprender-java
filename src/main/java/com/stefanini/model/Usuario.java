package com.stefanini.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "grid")
	private Long id;
	
	@NotBlank(message = "O campo é de preenchimento obrigatório!")
	@Size(max = 50)
	@Column(name = "nome", length = 50, nullable = false )
	private String nome;
	
	@NotBlank(message = "O campo é de preenchimento obrigatório!")
	@Size(min = 10, max = 50)
	@Email
	@Column(name = "email", length = 50, nullable = false)
	private String email;
	

	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "dt_nascimento")
	@Temporal(TemporalType.DATE)
	private Date dtNascimento;
	
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd kk:mm:ss")
	@Column(name = "dt_gerado")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtGerado;
	
	@NotBlank(message = "O campo é de preenchimento obrigatório!")
	@Size(min = 5 , max = 20, message = "Login deve ter entre 5 e 20 digitos!")
	@Column(name = "login", length = 20, unique = true)
	private String login;
	
	@Column(name = "password", length = 128)
	private String password;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDtGerado() {
		return dtGerado;
	}
	
	public void setDtGerado(Date dtGerado) {
		this.dtGerado = dtGerado;
	}
	
	public Date getDtNascimento() {
		return dtNascimento;
	}
	
	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}