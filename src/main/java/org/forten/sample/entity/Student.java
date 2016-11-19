package org.forten.sample.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test_student")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	@Column
	private String number;
	@Column
	private String password;
	@Column
	private String name;
	@Column
	private String email;
	@Column
	private String gender;
	@Column(name="role_name")
	private String roleName;

	public Student() {
		super();
		this.roleName="student";
	}

	public Student(String number, String password, String name, String email, String gender) {
		super();
		this.number = number;
		this.password = password;
		this.name = name;
		this.email = email;
		this.gender = gender;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getId() {
		return id;
	}

}
