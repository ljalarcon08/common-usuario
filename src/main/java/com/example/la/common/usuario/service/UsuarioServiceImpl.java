package com.example.la.common.usuario.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.la.common.usuario.entity.Rol;
import com.example.la.common.usuario.entity.Usuario;
import com.example.la.common.usuario.proyection.UsuarioInterface;
import com.example.la.common.usuario.repository.UsuarioRepository;



@Service
public class UsuarioServiceImpl implements UsuarioService{

	
	Logger logger=LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Optional<UsuarioInterface> findUsuarioById(Long id) {
		return usuarioRepository.findUsuarioByIdNoPass(id);
	}

	@Override
	public Iterable<Usuario> findUsuarioByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

	
	@Override
	public Iterable<UsuarioInterface> findUsuarioByEmailNoPs(String email) {
		return usuarioRepository.findByEmailNoPs(email);
	}
	
	@Transactional
	@Override
	public Usuario crearUsuario(Usuario usuario) {

		List<UsuarioInterface> usuariosEmail=(List<UsuarioInterface>)usuarioRepository.findByEmailNoPs(usuario.getEmail());

		if(!usuariosEmail.isEmpty()) {
			return null;
		}
	
		List<Rol> roles=new ArrayList<Rol>();
		Rol rol=new Rol();
		rol.setId(2L);
		rol.setName("USER");
		roles.add(rol);
		usuario.setRoles(roles);
		
		Usuario response=usuarioRepository.save(usuario);
		return response;
	}

	@Transactional
	@Override
	public Usuario actualizarUsuario(Usuario usuario) {

		
		Optional<Usuario> usuarioDB=usuarioRepository.findById(usuario.getId());
		
		List<UsuarioInterface> usuarioEmail=(List<UsuarioInterface>)this.findUsuarioByEmailNoPs(usuario.getEmail());
		
		if(usuarioDB.isEmpty()) {
			return null;
		}
		
		Usuario usuarioAct=usuarioDB.get();
		
		
		if(usuarioAct!=null && 
				(usuarioEmail.size()==0 || 
				(usuarioEmail.size()==1 && usuarioEmail.get(0).getEmail().equals(usuarioAct.getEmail())))) {
			usuarioAct.setName(usuario.getName());

			if(usuario.getPassword()!=null && !usuario.getPassword().isEmpty()) {
				usuarioAct.setPassword(usuario.getPassword());
			}else {
				usuarioAct.setPassword(usuarioDB.get().getPassword());
			}

			List<Rol> roles=usuarioAct.getRoles();
			if(usuario.getRoles()!=null  && !usuario.getRoles().isEmpty()) {
				usuario.getRoles().stream().forEach(rol->{
					if(!roles.contains(rol)) {
						roles.add(rol);
					}
				});
			}
			
			usuarioAct.setRoles(roles);
			Usuario response= usuarioRepository.save(usuarioAct);
			return response;
		}
		
		
		return null;
	}

	@Transactional
	@Override
	public void eliminarUsuario(Long id) {
		
		Optional<Usuario> usuario=usuarioRepository.findById(id);
		
		if(!usuario.isEmpty()) {
			usuarioRepository.delete(usuario.get());
		}
	}

	@Override
	public void actualizarPassUsuario(String email, String nuevoPass) {
		List<Usuario> usuariosEmail=(List<Usuario>)usuarioRepository.findByEmail(email);

		Usuario usuario=usuariosEmail.get(0);
		
		usuario.setPassword(nuevoPass);
		
		usuarioRepository.save(usuario);
	}

	@Override
	public Iterable<UsuarioInterface> findAllUsuarios() {
		return usuarioRepository.findAllUsuariosInt();
	}

	@Transactional(readOnly = true)
	@Override
	public Page<UsuarioInterface> findAllUsuariosPage(Pageable pageable) {
		return usuarioRepository.findAllUsuariosIntPag(pageable);
	}

	@Override
	public Usuario actualizaImagenUsuario(Long id, String imagen) {
		
		Optional<Usuario> usuarioDB=usuarioRepository.findById(id);
		Usuario usuarioAct=usuarioDB.get();
		
		if(usuarioAct!=null) {
			usuarioAct.setImagen(imagen);
			usuarioRepository.save(usuarioAct);
		}
		
		return usuarioAct;
	}
	
	
	
}
