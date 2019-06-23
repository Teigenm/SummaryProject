package com.bean;

public class Goods {
	private String id;
	private String imgSrc;
	private double price;
	private int type;
	
	public Goods() {
		super();
	}
	public Goods(String id, String imgSrc, double price, int type) {
		super();
		this.id = id;
		this.imgSrc = imgSrc;
		this.price = price;
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Goods [id=" + id + ", imgSrc=" + imgSrc + ", price=" + price + ", type=" + type + "]";
	}
	
}
