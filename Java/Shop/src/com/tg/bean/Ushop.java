package com.tg.bean;

public class Ushop {
	private int id;
	private String username;
	private String goodsid;
	private String name;
	private double price;
	private int number;
	//id,name,price,stock
	public Ushop() {
		super();
	}
	public Ushop(int id, String username, String goodsid, String name, double price, int number) {
		super();
		this.id = id;
		this.username = username;
		this.goodsid = goodsid;
		this.name = name;
		this.price = price;
		this.number = number;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
}
