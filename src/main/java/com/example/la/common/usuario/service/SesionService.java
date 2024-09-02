package com.example.la.common.usuario.service;

import java.util.Optional;

import com.example.la.common.usuario.entity.Sesion;

public interface SesionService {
	
	public Sesion crearSesion(Sesion sesion);
	
	public Optional<Sesion> getSesionById(String id);
	
	public Optional<Sesion> getSesionActByEmail(String email);
	
	public void logout(String email);
}
