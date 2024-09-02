package com.example.la.common.usuario.service;

import java.util.Optional;

import com.example.la.common.usuario.entity.Rol;

public interface RolService {

	public Rol crearRol(Rol rol);
	
	public Rol actualizarRol(Rol rol);
	
	public void eliminarRol(Long id);
	
	public Iterable<Rol> obtenerRolesPorId(Long id);
	
	public Iterable<Rol> obtenerRoles();
	
}
