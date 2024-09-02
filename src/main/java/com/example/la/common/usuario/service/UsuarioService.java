package com.example.la.common.usuario.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.la.common.usuario.entity.Usuario;
import com.example.la.common.usuario.proyection.UsuarioInterface;



public interface UsuarioService{

	public Optional<UsuarioInterface> findUsuarioById(Long id);

	public Iterable<UsuarioInterface> findUsuarioByEmailNoPs(String email);
	
	public Iterable<Usuario> findUsuarioByEmail(String email);
	
	public Usuario crearUsuario(Usuario usuario);
	
	public Usuario actualizarUsuario(Usuario usuario);
	
	public void actualizarPassUsuario(String email,String nuevoPass);
	
	public void eliminarUsuario(Long id);
	
	public Iterable<UsuarioInterface> findAllUsuarios();
	
	public Page<UsuarioInterface> findAllUsuariosPage(Pageable pageable);
	
	public Usuario actualizaImagenUsuario(Long id,String imagen);
}
