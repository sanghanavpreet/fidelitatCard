package com.digiteix.models;

import java.io.Serializable;
import java.util.ArrayList;

public class TypeShopList implements Serializable {

	private ArrayList<TypeShop> arrayTypeShop;

	public TypeShopList() {
		super();
		this.arrayTypeShop = new ArrayList<TypeShop>();
	}

	



	public void addTypeShop(TypeShop typeShop) {
		if (arrayTypeShop != null) {
			arrayTypeShop.add(typeShop);
		}
	}

	public ArrayList<TypeShop> getArraytypeShop() {
		return arrayTypeShop;
	}

	public void setArrayTypeShop(ArrayList<TypeShop> arrayTypeShop) {
		this.arrayTypeShop = arrayTypeShop;
	}


}
