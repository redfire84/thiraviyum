package com.thiraviyum.domain;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public abstract class BaseObject {

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	private Date modDate;
	
	@Column(length = 50, nullable = false)
	@JsonIgnore
	private String modUser;
	
	public Date getModDate() {
		return modDate;
	}
	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}
	public String getModUser() {
		return modUser;
	}
	public void setModUser(String modUser) {
		this.modUser = modUser;
	}
	@PrePersist
	public void onCreate() {
		setModDate(new Timestamp(new Date().getTime()));
	}
	@PreUpdate
	public void onUpdate() {
		setModDate(new Timestamp(new Date().getTime()));
	}
}
