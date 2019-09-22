package Kabina.DTO;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

import Kabina.Model.Role;
import Kabina.Model.Unit;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -5892343753492809513L;

	private final String jwttoken;
	private long userId;
	private String userName;
	private String password;
	private String shortName;
	private String fullName;
	private String phone;
	private String email;
	private Role role;
	private Unit business;
	public JwtResponse(String jwttoken, long userId, String userName, String password, String shortName,
			String fullName, String phone, String email, Role role, Unit business) {
		super();
		this.jwttoken = jwttoken;
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.shortName = shortName;
		this.fullName = fullName;
		this.phone = phone;
		this.email = email;
		this.role = role;
		this.business = business;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Unit getBusiness() {
		return business;
	}
	public void setBusiness(Unit business) {
		this.business = business;
	}
	public String getJwttoken() {
		return jwttoken;
	}
	
	
	
}
