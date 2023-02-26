package com.stefanini.resources;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.stefanini.model.Usuario;
import com.stefanini.service.UsuarioService;

@Path("/usuarios")
public class UsuarioResource {

	@Inject
	private UsuarioService usuarioService;

	@GET
	@Produces("application/json")
	public Response buscarUsuarios() {
		List<Usuario> usuarios = usuarioService.listarUsuarios();
		return Response.ok(usuarios).build();
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/email")
	public Response buscarEmails() {
		return Response.ok(usuarioService.listarProvedoresEmail()).build();
	} 

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response criarUsuario(@Valid Usuario user) {
		try {
			Usuario criarUsuario = usuarioService.criarUsuario(user);	
			return Response.status(Status.CREATED).entity(criarUsuario).build();
		} catch (ValidationException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	public Response deletarUsuario(@PathParam("id") Long  idUsuario) {
		try {
			usuarioService.removerUsuario(idUsuario);	
			return Response.noContent().build();
		} catch (ValidationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
		}
	}

}
