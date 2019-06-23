package com.fx.bean;
/**
 * 使用银行系统的用户的User对象
 * @author asus
 *
 */

public class User {
	private String idcard;
	private String password;
	private String user_name;
	private String id;
	private String age;
	private String sex;
	private String address;
	private String tel;
	private String state;//0 离职  1 在职 2 激活 3挂失
	private String user_type;//0管理员 1客户 2业务员
	public User() {
		super();
	}

	public User(String idcard, String password) {
		super();
		this.idcard = idcard;
		this.password = password;
	}



	public User(String idcard, String password, String user_name, String id,
			String tel, String user_type) {
		super();
		this.idcard = idcard;
		this.password = password;
		this.user_name = user_name;
		this.id = id;
		this.tel = tel;
		this.user_type = user_type;
	}

	public User(String idcard, String password, String user_type) {
		super();
		this.idcard = idcard;
		this.password = password;
		this.user_type = user_type;
	}


	public User(String idcard, String password, String user_name, String id,
			String age, String sex, String address, String tel, String state,
			String user_type) {
		super();
		this.idcard = idcard;
		this.password = password;
		this.user_name = user_name;
		this.id = id;
		this.age = age;
		this.sex = sex;
		this.address = address;
		this.tel = tel;
		this.state = state;
		this.user_type = user_type;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	




	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getUser_name() {
		return user_name;
	}
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idcard == null) ? 0 : idcard.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((tel == null) ? 0 : tel.hashCode());
		result = prime * result
				+ ((user_name == null) ? 0 : user_name.hashCode());
		result = prime * result
				+ ((user_type == null) ? 0 : user_type.hashCode());
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
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idcard == null) {
			if (other.idcard != null)
				return false;
		} else if (!idcard.equals(other.idcard))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (tel == null) {
			if (other.tel != null)
				return false;
		} else if (!tel.equals(other.tel))
			return false;
		if (user_name == null) {
			if (other.user_name != null)
				return false;
		} else if (!user_name.equals(other.user_name))
			return false;
		if (user_type == null) {
			if (other.user_type != null)
				return false;
		} else if (!user_type.equals(other.user_type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [idcard=" + idcard + ", password=" + password
				+ ", user_name=" + user_name + ", id=" + id + ", age=" + age
				+ ", sex=" + sex + ", address=" + address + ", tel=" + tel
				+ ", state=" + state + ", user_type=" + user_type + "]";
	}



}
