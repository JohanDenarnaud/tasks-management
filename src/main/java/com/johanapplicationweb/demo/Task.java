package com.johanapplicationweb.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Locale.Category;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Column(nullable = true, length = 300)
	private String description;
	
	@Column(nullable = false, length = 300)
	private Date createDate =  new Date();	
	

	@Column(nullable = false, length = 100)
	private String category;
		
	
	@Column(nullable = false)
	private Long id_user;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId_user() {
		return id_user;
	}

	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

//	public Date getDueDate() {
//		return dueDate;
//	}
//
//	public void setDueDate(Date dueDate) {
//		this.dueDate = dueDate;
//	}

	
}
