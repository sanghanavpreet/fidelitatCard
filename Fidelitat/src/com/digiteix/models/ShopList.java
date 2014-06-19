package com.digiteix.models;

import java.io.Serializable;
import java.util.ArrayList;

public class ShopList implements Serializable {

	private ArrayList<Shop> arrayShop;
	private int id;

	public ShopList() {
		super();
		this.arrayShop = new ArrayList<Shop>();
	}

	public void addShop(Shop shop) {
		if (arrayShop != null) {
			arrayShop.add(shop);
		}
	}

	public ArrayList<Shop> getArrayShop() {
		return arrayShop;
	}

	public void setArrayShop(ArrayList<Shop> arrayShop) {
		this.arrayShop = arrayShop;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
