package com.example.la.common.usuario.service.impl;

import com.example.la.common.usuario.entity.Rol;
import com.example.la.common.usuario.repository.RolRepository;
import com.example.la.common.usuario.service.RolServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RolServiceImplTest {

    @InjectMocks
    private RolServiceImpl rolService;

    @Mock
    private RolRepository rolRepository;

    private Rol rol;
    private List<Rol> roles;

    @BeforeAll
    public void init(){
        rol=new Rol();
        rol.setId(1L);
        rol.setName("ROL");
        roles=new ArrayList<>();
        roles.add(rol);
    }

    @Test
    public void obtenerRolesPorIdTest(){
        Mockito.when(rolRepository.findRolesByUsuarioId(anyLong())).thenReturn(roles);
        List<Rol> respuesta = (List<Rol>) rolService.obtenerRolesPorId(1L);
        Assert.notNull(respuesta,"OK");
    }

    @Test
    public void obtenerRolesTest(){
        Mockito.when(rolRepository.findAllByOrderByIdAsc()).thenReturn(roles);
        List<Rol> respuesta = (List<Rol>) rolService.obtenerRoles();
        Assert.state(!respuesta.isEmpty(),"OK");
    }

    @Test
    public void crearRolTest(){
        Mockito.when(rolRepository.findByName(anyString())).thenReturn(new ArrayList<Rol>());
        Mockito.when(rolRepository.save(any(Rol.class))).thenReturn(rol);
        Rol respuesta=rolService.crearRol(rol);
        Assert.notNull(respuesta,"OK");
        Assert.state(!respuesta.equals(null),"OK");
        Assert.state(!respuesta.equals("asdf"),"OK");
        Assert.state(!respuesta.equals(new Rol()),"OK");
        Assert.state(!new Rol().equals(respuesta),"OK");
        Assert.state(rol.equals(respuesta),"OK");
    }

    @Test
    public void crearRolErrorTest(){
        Mockito.when(rolRepository.findByName(anyString())).thenReturn(roles);
        Rol respuesta=rolService.crearRol(rol);
        Assert.isNull(respuesta,"OK");
    }

    @Test
    public void actualizarRolTest(){
        Mockito.when(rolRepository.findByName(anyString())).thenReturn(roles);
        Mockito.when(rolRepository.save(any(Rol.class))).thenReturn(rol);
        Rol respuesta=rolService.actualizarRol(rol);
        Assert.notNull(respuesta,"OK");
    }

    @Test
    public void actualizarRol2Test(){
        Mockito.when(rolRepository.findByName(anyString())).thenReturn(new ArrayList<>());
        Mockito.when(rolRepository.save(any(Rol.class))).thenReturn(rol);
        Rol roln=new Rol();
        roln.setId(3L);
        roln.setName("ROLN");
        Rol respuesta=rolService.actualizarRol(roln);
        Assert.notNull(respuesta,"OK");
    }

    @Test
    public void actualizarRolErrorTest(){
        Mockito.when(rolRepository.findByName(anyString())).thenReturn(roles);
        Rol roln=new Rol();
        roln.setId(3L);
        roln.setName("ROLN");
        Rol respuesta=rolService.actualizarRol(roln);
        Assert.isNull(respuesta,"OK");
    }

    @Test
    public void eliminarRolTest(){
        Mockito.doNothing().when(rolRepository).deleteById(anyLong());
        rolService.eliminarRol(1L);
    }
}
