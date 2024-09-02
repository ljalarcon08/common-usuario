package com.example.la.common.usuario.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.la.common.usuario.entity.Sesion;
import com.example.la.common.usuario.repository.SesionRepository;

@Service
public class SesionServiceImpl implements SesionService{

	@Autowired
	private SesionRepository sesionRepository;
	
	@Override
	public Sesion crearSesion(Sesion sesion) {
		return sesionRepository.save(sesion);
	}

	@Override
	public Optional<Sesion> getSesionById(String id) {
		return sesionRepository.findById(id);
	}

	@Override
	public void logout(String email) {
		Optional<Sesion> sesionAct=sesionRepository.findByEmailAndEstado(email, "ACTIVO");
		if(!sesionAct.isEmpty()) {
			Sesion sesionDB=sesionAct.get();
			sesionDB.setEstado("INACTIVO");
			sesionRepository.save(sesionDB);
		}
	}

	@Override
	public Optional<Sesion> getSesionActByEmail(String email) {
		Optional<Sesion> sesionAct=sesionRepository.findByEmailAndEstado(email, "ACTIVO");
		return sesionAct;
	}

}
