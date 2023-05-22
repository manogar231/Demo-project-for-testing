package com.demo.coreproject.entity;

import java.time.LocalDate;
import java.util.Date;



import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usertable")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;
	@Column(name = "user_name" , unique = true)
	private String username;
	@Column(name = "mail")
	private String mail;
	@Column(name = "user_address")
	private String useraddress;
	@Column(name = "create_at")
	private LocalDate createdAt;
	@Column(name = "update_at")
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	@Column(name = "password")
	private String password;
	@Column(name = "user_status")
	private String status;
	@Column(name = "last_login")
	private Date lastlogin;
}
