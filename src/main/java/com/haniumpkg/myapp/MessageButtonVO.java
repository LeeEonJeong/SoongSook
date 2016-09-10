package com.haniumpkg.myapp;

public class MessageButtonVO {
	private String label = "";
	private String url = "";
	
	public MessageButtonVO(String label, String url) {
		super();
		this.label = label;
		this.url = url;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	

}
