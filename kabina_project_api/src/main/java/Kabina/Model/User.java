package Kabina.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "User")
public class User {
	@Id
	@GeneratedValue(generator = "userId", strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@NotBlank
	@Column(name = "userName")
	private String userName;

	@NotBlank
	@Column(name = "password")
	private String password;
	
	@Column(name = "shortName")
	private String shortName;
	
	@Column(name = "fullName")
	private String fullName;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "email")
	private String email;
	
	@ManyToOne
	@JoinColumn(name="roleId")
	private Role role;
	
	@ManyToOne
	@JoinColumn(name="unitId")
	private Unit business;
	
	public User() {

	}

	public User(String userName, String userShortName) {
		this.userName  = userName;
		this.shortName = userShortName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
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
}
