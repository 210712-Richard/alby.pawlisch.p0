package com.revature.beans;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
	
	private Integer id;
	private String username;
	private String email;
	private String phone;
	private UserType type;
	private Long money;
	// add the stuff for loan requests and approval later

}

public User() {
	super();
	this.type = UserType.CUSTOMER;
}

public User(Integer id, String username, String email, String phone, Long money) {
	this();
	this.id = id;
	this.username = username;
	this.email = email;
	this.phone = phone;
	this.money = money;
}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public UserType getType() {
	return type;
}

public void setType(UserType type) {
	this.type = type;
}

public Long getMoney() {
	return money;
}

public void setMoney(Long money) {
	this.money = money;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((money == null) ? 0 : money.hashCode());
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((phone == null) ? 0 : phone.hashCode());
	result = prime * result + ((type == null) ? 0 : type.hashCode());
	result = prime * result + ((username == null) ? 0 : username.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	User other = (User) obj;
	if (money == null) {
		if (other.money != null)
			return false;
	} else if (!money.equals(other.money))
		return false;
	if (email == null) {
		if (other.email != null)
			return false;
	} else if (!email.equals(other.email))
		return false;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	if (phone == null) {
		if (other.phone != null)
			return false;
	} else if (!phone.equals(other.phone))
		return false;
	if (type != other.type)
		return false;
	if (username == null) {
		if (other.username != null)
			return false;
	} else if (!username.equals(other.username))
		return false;
	return true;
}

@Override
public String toString() {
	return "User [id=" + id + ", username=" + username + ", email=" + email + ", phone=" + phone + ", type=" + type
			+ ", money=" + money + "]";
}




