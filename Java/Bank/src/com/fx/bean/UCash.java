package com.fx.bean;
/**
 * 客户存钱Ucah对象
 * @author asus
 *
 */
public class UCash {
	private String idcard;
	private String type;
	private Double endCash;
	public UCash() {
		super();
	}
	public UCash(String idcard, String type, Double endcash) {
		super();
		this.idcard = idcard;
		this.type = type;
		this.endCash = endcash;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getEndCash() {
		return endCash;
	}
	public void setEndCash(Double endcash) {
		this.endCash = endcash;
	}
	@Override
	public String toString() {
		return "UCash [idcard=" + idcard + ", type=" + type + ", endcash="
				+ endCash + "]";
	}
	
}
