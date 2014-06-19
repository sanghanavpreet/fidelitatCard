package com.digiteix.models;

import java.io.Serializable;
import java.sql.Date;

public class Targetes implements Serializable {

	private int id_card;
	private int id_shop;
	private int cols;
	private int rows;
	private String icon_stamp;
	private String card_logo;
	private Date final_date;
	private Date valid_date;
	private int num_stamp;

	public Targetes(int id_card, int id_shop, int cols, int rows,
			String icon_stamp, String card_logo, Date final_date,
			Date valid_date, int num_stamp) {
		super();
		this.id_card = id_card;
		this.id_shop = id_shop;
		this.cols = cols;
		this.rows = rows;
		this.icon_stamp = icon_stamp;
		this.card_logo = card_logo;
		this.final_date = final_date;
		this.valid_date = valid_date;
		this.num_stamp = num_stamp;
	}

	public int getId_card() {
		return id_card;
	}

	public void setId_card(int id_card) {
		this.id_card = id_card;
	}

	public int getId_shop() {
		return id_shop;
	}

	public void setId_shop(int id_shop) {
		this.id_shop = id_shop;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getIcon_stamp() {
		return icon_stamp;
	}

	public void setIcon_stamp(String icon_stamp) {
		this.icon_stamp = icon_stamp;
	}

	public String getCard_logo() {
		return card_logo;
	}

	public void setCard_logo(String card_logo) {
		this.card_logo = card_logo;
	}

	public Date getFinal_date() {
		return final_date;
	}

	public void setFinal_date(Date final_date) {
		this.final_date = final_date;
	}

	public Date getValid_date() {
		return valid_date;
	}

	public void setValid_date(Date valid_date) {
		this.valid_date = valid_date;
	}

	public int getNum_stamp() {
		return num_stamp;
	}

	public void setNum_stamp(int num_stamp) {
		this.num_stamp = num_stamp;
	}

}
