package com.digiteix.models;

import java.io.Serializable;


public class Shop implements Serializable {

	private int shopId;
	private String shopName;
	private String desceiption;
	private int id_type;
	private String longitud;
	private String latitiud;
	private String address;
	private String email;
	private String web;
	private String telephones_fix;
	private String telephones_mob;
	private String zipCode;
	private String city;
	private String coverSmallUrl;
	private String coverBigUrl;
	private String status;
	private String facebookUrl;
	private String twitterUrl;

	public Shop() {
	}

	public Shop(int shopId, String shopName, String desceiption,
			int id_type, String longitud, String latitiud,
			String address, String email, String web, String telephones_fix,
			String telephones_mob, String facebookUrl, String twitterUrl, String zipCode, String city,
			String coverSmallUrl, String coverBigUrl, String status) {
		super();
		this.shopId = shopId;
		this.shopName = shopName;
		this.desceiption = desceiption;
		this.id_type = id_type;
		this.longitud = longitud;
		this.latitiud = latitiud;
		this.address = address;
		this.email = email;
		this.web = web;
		this.telephones_fix = telephones_fix;
		this.telephones_mob = telephones_mob;
		this.facebookUrl = facebookUrl;
		this.twitterUrl = twitterUrl;
		this.zipCode = zipCode;
		this.city = city;
		this.coverSmallUrl = coverSmallUrl;
		this.coverBigUrl = coverBigUrl;
		this.status = status;
	}

	

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getDesceiption() {
		return desceiption;
	}

	public void setDesceiption(String desceiption) {
		this.desceiption = desceiption;
	}

	public int getId_type() {
		return id_type;
	}

	public void setId_type(int id_type) {
		this.id_type = id_type;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getLatitiud() {
		return latitiud;
	}

	public void setLatitiud(String latitiud) {
		this.latitiud = latitiud;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getTelephones_fix() {
		return telephones_fix;
	}

	public void setTelephones_fix(String telephones_fix) {
		this.telephones_fix = telephones_fix;
	}

	public String getTelephones_mob() {
		return telephones_mob;
	}

	public void setTelephones_mob(String telephones_mob) {
		this.telephones_mob = telephones_mob;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCoverSmallUrl() {
		return coverSmallUrl;
	}

	public void setCoverSmallUrl(String coverSmallUrl) {
		this.coverSmallUrl = coverSmallUrl;
	}

	public String getCoverBigUrl() {
		return coverBigUrl;
	}

	public void setCoverBigUrl(String coverBigUrl) {
		this.coverBigUrl = coverBigUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFacebookUrl() {
		return facebookUrl;
	}

	public void setFacebookUrl(String facebookUrl) {
		this.facebookUrl = facebookUrl;
	}

	public String getTwitterUrl() {
		return twitterUrl;
	}

	public void setTwitterUrl(String twitterUrl) {
		this.twitterUrl = twitterUrl;
	}

}
