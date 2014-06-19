package com.digiteix.models;

import java.io.Serializable;
import java.sql.Date;

public class StampCard implements Serializable {

	private int id;
	private Date stamp_date;

	public StampCard(int id, Date stamp_date) {
		super();
		this.id = id;
		this.stamp_date = stamp_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStamp_date() {
		return stamp_date;
	}

	public void setStamp_date(Date stamp_date) {
		this.stamp_date = stamp_date;
	}

}
