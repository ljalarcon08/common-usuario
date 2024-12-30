package com.example.la.common.usuario.service.impl;

import com.example.la.common.usuario.entity.Sesion;
import com.example.la.common.usuario.repository.SesionRepository;
import com.example.la.common.usuario.service.SesionServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SesionServiceImplTest {

    @InjectMocks
    private SesionServiceImpl sesionService;

    @Mock
    private SesionRepository sesionRepository;
    private Sesion sesion;

    @BeforeAll
    public void init(){
        sesion=new Sesion();
        sesion.setId("id");
        sesion.setExpiration(new Date());
        sesion.setEstado("ACTIVO");
        sesion.setEmail("email");
    }

    @Test
    public void crearSesionTest(){

        Mockito.when(sesionRepository.save(any(Sesion.class))).thenReturn(sesion);
        Sesion respuesta = sesionService.crearSesion(sesion);
        Assert.notNull(respuesta.getEmail(),"OK");
        Assert.notNull(respuesta.getId(),"OK");
        Assert.notNull(respuesta.getExpiration(),"OK");
        Assert.notNull(respuesta.getEstado(),"OK");
    }

    @Test
    public void getSesionByIdTest(){
        Mockito.when(sesionRepository.findById(anyString())).thenReturn(Optional.of(sesion));
        Optional<Sesion> respuesta = sesionService.getSesionById("id");
        Assert.state(respuesta.isPresent(),"OK");
    }

    @Test
    public void logoutTest(){
        Mockito.when(sesionRepository.findByEmailAndEstado(anyString(),anyString())).thenReturn(Optional.of(sesion));
        sesionService.logout("email");
    }

    @Test
    public void logoutErrorTest(){
        Optional<Sesion> sesionOp=Optional.empty();
        Mockito.when(sesionRepository.findByEmailAndEstado(anyString(),anyString())).thenReturn(sesionOp);
        sesionService.logout("email");
    }

    @Test
    public void getSesionActByEmailTest(){
        Mockito.when(sesionRepository.findByEmailAndEstado(anyString(),anyString())).thenReturn(Optional.of(sesion));
        Optional<Sesion> sesionOp = sesionService.getSesionActByEmail("email");
        Assert.state(sesionOp.isPresent(),"OK");
    }
}
