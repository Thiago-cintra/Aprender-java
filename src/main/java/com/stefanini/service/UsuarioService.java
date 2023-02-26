package com.stefanini.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ValidationException;

import com.stefanini.model.Usuario;
import com.stefanini.repository.UsuarioRepository;

@ApplicationScoped
public class UsuarioService {

	@Inject
	private UsuarioRepository repository;

	public List<Usuario> listarUsuarios() {
		return repository.listAll();
	}
	
	public List<String> listarProvedoresEmail() {
		return repository.listarDominiosEmails();
	}

	public Usuario criarUsuario(Usuario user) {
		if (!isSenhaValida(user.getPassword())) {
			throw new ValidationException("Senha inválida");
		}
		cifrarSenha(user);

		repository.save(user);
		return user;
	}

	private boolean isSenhaValida(String senha) {
		if (senha == null || senha.isBlank()) {
			return false;
		}

		if (senha.length() < 4 || senha.length() > 10) {
			return false;
		}

		String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[-+_!@#$%^&*., ?]).+$";

		if (!senha.matches(regex)) {
			return false;
		}

		return true;
	}

	private void cifrarSenha(Usuario usuario) {

		String senha = usuario.getPassword();

		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			byte messageDigest[];
			messageDigest = algorithm.digest(senha.getBytes("UTF-8"));
			String bsa64 = Base64.getEncoder().encodeToString(messageDigest);
			usuario.setPassword(bsa64);

		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public void removerUsuario(Long idUsuario) {
		Usuario toDelete = repository.findById(idUsuario);
		
		if(toDelete == null) {
			throw new ValidationException("Usuário Não Encontrado");
		}
		
		repository.delete(idUsuario);
	}
}
