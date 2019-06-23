package com.bean;

public class Student {
	private int id;
	private String name;
	private String sex;
	private String major;
	private String grade;
	private String home;
	
	public Student() {
		super();
	}

	public Student(String name, String sex, String major, String grade, String home) {
		super();
		this.name = name;
		this.sex = sex;
		this.major = major;
		this.grade = grade;
		this.home = home;
	}

	public Student(int id, String name, String sex, String major, String grade, String home) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.major = major;
		this.grade = grade;
		this.home = home;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", sex=" + sex + ", major=" + major + ", grade=" + grade
				+ ", home=" + home + "]";
	}
	
}
