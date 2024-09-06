package com.example.la.common.usuario.domain;

import com.example.la.common.usuario.proyection.UsuarioInterface;

public class UsuarioInterfaceImpl implements UsuarioInterface {

    private Long id;
    private String name;
    private String email;
    private String imagen;
    private Boolean google;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getImagen() {
        return this.imagen;
    }

    @Override
    public Boolean getGoogle() {
        return this.google;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setGoogle(Boolean google) {
        this.google = google;
    }
}
