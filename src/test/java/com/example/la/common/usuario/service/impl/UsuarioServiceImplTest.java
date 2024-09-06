package com.example.la.common.usuario.service.impl;

import com.example.la.common.usuario.domain.UsuarioInterfaceImpl;
import com.example.la.common.usuario.entity.Rol;
import com.example.la.common.usuario.entity.Usuario;
import com.example.la.common.usuario.proyection.UsuarioInterface;
import com.example.la.common.usuario.repository.UsuarioRepository;
import com.example.la.common.usuario.service.UsuarioServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioServiceImplTest {

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    private UsuarioInterfaceImpl usuarioI;

    private List<Usuario> usuarios;
    private Usuario usuario;

    @BeforeAll
    public void init(){
        usuarioI=new UsuarioInterfaceImpl();
        usuarioI.setEmail("email");

        usuarios=new ArrayList<>();
        usuario=new Usuario();
        usuario.setName("name");
        usuario.setPassword("pass");
        usuario.setEmail("email");
        usuario.setImagen("img");
        usuario.setId(1L);
        usuario.setCreate(new Date());
        usuario.setGoogle(true);
        usuarios.add(usuario);

        usuario.setRoles(new ArrayList<Rol>());

        Rol rol=new Rol();
        rol.setId(2L);
        rol.setName("name");
        usuario.getRoles().add(rol);
    }

    @Test
    public void findUsuarioByIdTest(){
        Optional<UsuarioInterface> usuarioOp=Optional.of(usuarioI);
        Mockito.when(usuarioRepository.findUsuarioByIdNoPass(anyLong())).thenReturn(usuarioOp);
        Optional<UsuarioInterface> respuesta=usuarioService.findUsuarioById(1L);
        Assert.state(!respuesta.isEmpty(),"OK");
    }

    @Test
    public void findUsuarioByEmailTest(){
        Mockito.when(usuarioRepository.findByEmail(anyString())).thenReturn(usuarios);
        List<Usuario> respuesta = (List<Usuario>) usuarioService.findUsuarioByEmail("email");
        Assert.state(!respuesta.isEmpty(),"OK");
    }

    @Test
    public void findUsuarioByEmailNoPsTest(){
        List<UsuarioInterface> usuariosI=new ArrayList<>();
        usuariosI.add(usuarioI);
        usuariosI.add(usuarioI);
        Mockito.when(usuarioRepository.findByEmailNoPs(anyString())).thenReturn(usuariosI);
        List<UsuarioInterface> respuesta=(List<UsuarioInterface>)usuarioService.findUsuarioByEmailNoPs("email");
        Assert.state(!respuesta.isEmpty(),"OK");
    }

    @Test
    public void crearUsuarioTest(){
        List<UsuarioInterface> usuariosI=new ArrayList<>();
        Mockito.when(usuarioRepository.findByEmailNoPs(anyString())).thenReturn(usuariosI);

        Mockito.when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        Usuario respuesta=usuarioService.crearUsuario(usuario);
        Assert.notNull(respuesta.getId(),"OK");
        Assert.notNull(respuesta.getName(),"OK");
        Assert.notNull(respuesta.getEmail(),"OK");
        Assert.notNull(respuesta.getImagen(),"OK");
        Assert.notNull(respuesta.getPassword(),"OK");
        Assert.notNull(respuesta.getCreate(),"OK");
        Assert.state(respuesta.isGoogle(),"OK");
        Assert.state(!respuesta.getRoles().isEmpty(),"OK");
    }

    @Test
    public void crearUsuarioErrorTest(){
        List<UsuarioInterface> usuariosI=new ArrayList<>();
        usuariosI.add(usuarioI);
        usuariosI.add(usuarioI);
        Mockito.when(usuarioRepository.findByEmailNoPs(anyString())).thenReturn(usuariosI);
        Usuario respuesta=usuarioService.crearUsuario(usuario);
        Assert.isNull(respuesta,"OK");
    }

    @Test
    public void actualizarUsuarioTest(){

        Mockito.when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));

        List<UsuarioInterface> usuariosI=new ArrayList<>();
        usuarioI.setEmail("email");
        usuariosI.add(usuarioI);
        usuariosI.add(usuarioI);
        Mockito.when(usuarioRepository.findByEmailNoPs(anyString())).thenReturn(usuariosI);
        Mockito.when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        Usuario act=new Usuario();
        Rol rol2=new Rol();
        rol2.setName("name");
        rol2.setId(2L);
        rol2.setId(3L);
        Rol rol=new Rol();
        rol.setName("ROL2");
        rol.setId(3L);
        act.setRoles(new ArrayList<>());
        act.getRoles().add(rol);
        act.getRoles().add(rol2);
        act.setEmail("email");
        act.setId(1L);
        Usuario respuesta=usuarioService.actualizarUsuario(act);
        Assert.notNull(respuesta,"OK");
    }

    @Test
    public void actualizarUsuario2Test(){

        Mockito.when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));

        List<UsuarioInterface> usuariosI=new ArrayList<>();
        usuarioI.setEmail("email");
        usuariosI.add(usuarioI);
        usuariosI.add(usuarioI);
        Mockito.when(usuarioRepository.findByEmailNoPs(anyString())).thenReturn(usuariosI);
        Mockito.when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        Usuario act=new Usuario();
        act.setRoles(new ArrayList<>());
        act.setEmail("email");
        act.setPassword("pass");
        act.setId(1L);
        Usuario respuesta=usuarioService.actualizarUsuario(act);
        Assert.notNull(respuesta,"OK");
    }

    @Test
    public void actualizarUsuario3Test(){

        Mockito.when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));

        List<UsuarioInterface> usuariosI=new ArrayList<>();
        usuarioI.setEmail("email");
        usuariosI.add(usuarioI);
        usuariosI.add(usuarioI);
        Mockito.when(usuarioRepository.findByEmailNoPs(anyString())).thenReturn(usuariosI);
        Mockito.when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        Usuario act=new Usuario();
        act.setEmail("email");
        act.setPassword("pass");
        act.setId(1L);
        Usuario respuesta=usuarioService.actualizarUsuario(act);
        Assert.notNull(respuesta,"OK");
        Assert.notNull(respuesta.toString(),"OK");
        Assert.state(!usuario.equals(null),"OK");
        Assert.state(!usuario.equals("asdf"),"OK");
        Assert.state(!usuario.equals(new Usuario()),"OK");
        Assert.state(!new Usuario().equals(usuario),"OK");
        Assert.state(usuario.equals(respuesta),"OK");
    }

    @Test
    public void actualizarUsuarioErrorTest(){

        Mockito.when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));

        List<UsuarioInterface> usuariosI=new ArrayList<>();
        Mockito.when(usuarioRepository.findByEmailNoPs(anyString())).thenReturn(usuariosI);
        Usuario respuesta=usuarioService.actualizarUsuario(usuario);
        Assert.isNull(respuesta,"OK");
    }

    @Test
    public void actualizarUsuarioError3Test(){

        Mockito.when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));

        List<UsuarioInterface> usuariosI=new ArrayList<>();
        usuarioI.setEmail("email1");
        usuariosI.add(usuarioI);
        usuariosI.add(usuarioI);
        Mockito.when(usuarioRepository.findByEmailNoPs(anyString())).thenReturn(usuariosI);
        Usuario respuesta=usuarioService.actualizarUsuario(usuario);
        Assert.isNull(respuesta,"OK");
    }

    @Test
    public void eliminarUsuarioTest(){
        Mockito.when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));

        Mockito.doNothing().when(usuarioRepository).delete(usuario);
        usuarioService.eliminarUsuario(1L);
    }

    @Test
    public void eliminarUsuario2Test(){
        Optional<Usuario> usuarioOp=Optional.empty();
        Mockito.when(usuarioRepository.findById(anyLong())).thenReturn(usuarioOp);

        usuarioService.eliminarUsuario(1L);
    }

    @Test
    public void actualizarPassUsuarioTest(){
        Mockito.when(usuarioRepository.findByEmail(anyString())).thenReturn(usuarios);
        Mockito.when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        usuarioService.actualizarPassUsuario("email","pass");
    }

    @Test
    public void findAllUsuariosTest(){
        List<UsuarioInterface> usuariosI=new ArrayList<>();
        usuariosI.add(usuarioI);
        usuariosI.add(usuarioI);
        Mockito.when(usuarioRepository.findAllUsuariosInt()).thenReturn(usuariosI);
        List<UsuarioInterface> respuesta = (List<UsuarioInterface>) usuarioService.findAllUsuarios();
        Assert.state(!respuesta.isEmpty(),"OK");
    }

    @Test
    public void findAllUsuariosPageTest(){
        List<UsuarioInterface> usuariosI=new ArrayList<>();
        usuariosI.add(usuarioI);
        usuariosI.add(usuarioI);
        Page<UsuarioInterface> page=new PageImpl<>(usuariosI);
        Mockito.when(usuarioRepository.findAllUsuariosIntPag(any(Pageable.class))).thenReturn(page);
        Pageable pageable=Pageable.ofSize(10);
        Page<UsuarioInterface> respuesta=usuarioService.findAllUsuariosPage(pageable);
        Assert.state(!respuesta.isEmpty(),"OK");
    }

    @Test
    public void actualizaImagenUsuarioTest(){
        Mockito.when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));
        Mockito.when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        Usuario respuesta=usuarioService.actualizaImagenUsuario(1L,"imagen");
        Assert.notNull(respuesta,"OK");
    }

    @Test
    public void actualizaImagenUsuarioErrorTest(){
        Optional<Usuario> usuarioOp=Optional.empty();
        Mockito.when(usuarioRepository.findById(anyLong())).thenReturn(usuarioOp);
        Usuario respuesta=usuarioService.actualizaImagenUsuario(1L,"imagen");
        Assert.isNull(respuesta,"OK");
    }
}
