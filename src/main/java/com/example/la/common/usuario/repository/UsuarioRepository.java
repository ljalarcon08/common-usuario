package com.example.la.common.usuario.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.la.common.usuario.entity.Usuario;
import com.example.la.common.usuario.proyection.UsuarioInterface;



@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>,PagingAndSortingRepository<Usuario,Long>{

	public Iterable<Usuario> findByEmail(String email);
	
	@Query("select u.id as id,u.name as name,u.email as email,u.imagen as imagen,u.google as google from Usuario u where u.email=?1")
	public Iterable<UsuarioInterface> findByEmailNoPs(String email);
	
	@Query("select u.id as id,u.name as name,u.email as email,u.imagen as imagen,u.google as google from Usuario u where u.id=?1")
	public Optional<UsuarioInterface> findUsuarioByIdNoPass(Long id);
	
	@Query("select u.id as id,u.name as name,u.email as email,u.imagen as imagen,u.google as google from Usuario u")
	public Iterable<UsuarioInterface> findAllUsuariosInt();
	
	@Query("select u.id as id,u.name as name,u.email as email,u.imagen as imagen,u.google as google from Usuario u")
	public Page<UsuarioInterface> findAllUsuariosIntPag(Pageable pageable);
}
