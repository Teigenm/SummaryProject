package com.tg.bean;

public class Goods {
	private String id;
	private String name;
	private double price;
	private int stock;
	//id,name,price,stock
	public Goods() {
		super();
		this.id=null;
		this.name=null;
		this.price=-1;
		this.stock=-1;
	}
	public Goods(String id) {
		super();
		this.id = id;
		this.name=null;
		this.price=-1;
		this.stock=-1;
	}
	public Goods(String id, String name, double price, int stock) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", price=" + price + ", stock=" + stock + "]";
	}
	
}
