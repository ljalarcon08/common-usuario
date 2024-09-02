package com.example.la.common.usuario.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonInclude;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable{
	

	private static final long serialVersionUID = 7572181L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(unique=true)
	private String email;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String password;
	
	private String imagen;
	
	@Column(nullable = false, columnDefinition = "TINYINT", length = 1)
	private boolean google;
	
	@CreatedDate
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date create;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade={ CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinTable(name="usuario_rol",joinColumns = @JoinColumn(name="usuario_id"),
	inverseJoinColumns = @JoinColumn(name="rol_id"))
	private List<Rol> roles;
	
	
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
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	
	
	public Date getCreate() {
		return create;
	}

	public void setCreate(Date create) {
		Date date=new Date();
		this.create = date;
	}

	
	
	public boolean isGoogle() {
		return google;
	}

	public void setGoogle(boolean google) {
		this.google = google;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	@Override
	public boolean equals(Object obj) {
		
		if(obj==null) {
			return false;
		}
		
		if(obj.getClass()!=this.getClass()) {
			return false;
		}
		
		final Usuario objUsuario=(Usuario)obj;
		
		if(objUsuario.getId()==null || this.getId()!=null 
				) {
			return false;
		}
		else if(this.getId().compareTo(objUsuario.getId())!=0) {
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", imagen="
				+ imagen + ", google=" + google + ", create=" + create + ", roles=" + roles + "]";
	}
	
	
	
	
}
