package com.stefanini.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;

import com.stefanini.dao.GenericDAO;
import com.stefanini.model.Usuario;

@ApplicationScoped
public class UsuarioRepository extends GenericDAO<Usuario, Long>{

	@SuppressWarnings("unchecked")
	public List<String> listarDominiosEmails(){
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT distinct substr(email, position('@' in email)+1, 1000) ");
		query.append(" FROM tb_usuario ");
		
		Query q = getEntityManager().createNativeQuery(query.toString());
		List<String> emails = q.getResultList();
		return emails;
	}
	
}
