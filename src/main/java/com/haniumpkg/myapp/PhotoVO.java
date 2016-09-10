package com.haniumpkg.myapp;

public class PhotoVO {
	private String url = "";
	private int width = 0;
	private int height = 0;
	

	public PhotoVO() {
		// TODO Auto-generated constructor stub
	}


	public PhotoVO(String url, int width, int height) {
		super();
		this.url = url;
		this.width = width;
		this.height = height;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}

	
	
}
