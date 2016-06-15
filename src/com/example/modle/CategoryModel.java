package com.example.modle;

public class CategoryModel {
	private int cid;
	private String Mprovince;

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getMprovince() {
		return Mprovince;
	}

	public void setMprovince(String mprovince) {
		Mprovince = mprovince;
	}

	public CategoryModel(int cid, String title) {
		this.cid = cid;
		this.Mprovince = title;

	}

}
