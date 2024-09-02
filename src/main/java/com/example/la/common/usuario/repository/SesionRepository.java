package com.example.la.common.usuario.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.la.common.usuario.entity.Sesion;

@Repository
public interface SesionRepository extends CrudRepository<Sesion,String>{

	
	@Query("Select s from Sesion s where s.email=?1 and s.estado=?2")
	public Optional<Sesion> findByEmailAndEstado(String email,String estado);
	
}
