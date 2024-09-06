package com.example.la.common.usuario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.la.common.usuario.entity.Rol;
import com.example.la.common.usuario.repository.RolRepository;

import jakarta.transaction.Transactional;

@Service
public class RolServiceImpl implements RolService{

	@Autowired
	private RolRepository rolRepository;

	@Override
	public Iterable<Rol> obtenerRolesPorId(Long id) {
		return rolRepository.findRolesByUsuarioId(id);
	}

	@Override
	public Iterable<Rol> obtenerRoles() {
		return rolRepository.findAllByOrderByIdAsc();
	}
	
	@Transactional
	@Override
	public Rol crearRol(Rol rol) {
		Rol rolNuevo=null;
		
		List<Rol> roles=(List<Rol>)rolRepository.findByName(rol.getName());
		
		if(roles.isEmpty()) {
			rolNuevo=rolRepository.save(rol);
		}
		
		return rolNuevo;
	}

	@Transactional
	@Override
	public Rol actualizarRol(Rol rol) {
		Rol rolActualizado=null;
		
		List<Rol> roles=(List<Rol>)rolRepository.findByName(rol.getName());
		
		if(roles.isEmpty()) {
			rolActualizado=rolRepository.save(rol);
		}
		else if(rol.getId().equals(roles.get(0).getId())) {
			rolActualizado=rolRepository.save(rol);
		}
		
		
		return rolActualizado;
	}

	@Transactional
	@Override
	public void eliminarRol(Long id) {
		rolRepository.deleteById(id);
	}
	
	
}
