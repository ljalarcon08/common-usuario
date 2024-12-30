package com.example.la.common.usuario.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.la.common.usuario.entity.Rol;

@Repository
public interface RolRepository extends CrudRepository<Rol,Long>{

	public Iterable<Rol> findByName(String name);
	
	@Query("Select r from Usuario u join u.roles r where u.id=?1")
	public Iterable<Rol> findRolesByUsuarioId(Long id);
	
	
	public Iterable<Rol> findAllByOrderByIdAsc();
}
