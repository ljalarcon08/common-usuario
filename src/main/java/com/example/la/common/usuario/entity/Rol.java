package com.example.la.common.usuario.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="rol")
public class Rol{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false,unique=true)
	private String name;
	
	/*@ManyToMany(fetch = FetchType.LAZY,cascade={ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinTable(name="usuario_rol",joinColumns = @JoinColumn(name="rol_id"),
	inverseJoinColumns = @JoinColumn(name="usuario_id"))
	private List<Usuario> usuarios;*/
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.toUpperCase();
	}
	
	@Override
	public String toString() {
		return "Rol [id=" + id + ", name=" + name + "]";
	}

	@Override
	public boolean equals(Object obj) {
		
		if(obj==null) {
			return false;
		}
		
		if(this.getClass()!=obj.getClass()) {
			return false;
		}
		
		final Rol rolObj=(Rol)obj;
		
		if(this.getId()!=null && rolObj.getId()!=null && this.getId()==rolObj.getId()) {
			return true;
		}
		
		return super.equals(obj);
	}
	
	
	
}
