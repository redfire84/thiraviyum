package com.thiraviyum.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "THIRAVIYUM_USER")
@SuppressWarnings("serial")
public class User extends BaseObject implements UserDetails {

	@Id
	@NotBlank
	@Email
	@Column(length = 50)
	private String username;
	
	@NotBlank
	@Length(min = 10, max = 60)
	@Column(length = 60, nullable = false)
	@JsonIgnore
	private String password;
	
	@NotBlank
	@Length(min = 1, max = 50)
	@Column(length = 50, nullable = false)
	private String firstName;
	
	@NotBlank
	@Length(min = 1, max = 50)
	@Column(length = 50, nullable = false)
	private String lastName;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
}
