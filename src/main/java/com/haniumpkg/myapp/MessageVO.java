package com.haniumpkg.myapp;

public class MessageVO {

	private String text;
	private PhotoVO photo;
	private MessageButtonVO message_button;

	public MessageVO(String text) {
		this.text = text;
	}

	public MessageVO(String text, PhotoVO photo, MessageButtonVO message_button) {
		this.text = text;
		this.photo = photo;
		this.message_button = message_button;
	}

	public MessageVO() {}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public PhotoVO getPhoto() {
		return photo;
	}

	public void setPhoto(PhotoVO photo) {
		this.photo = photo;
	}

	public MessageButtonVO getMessage_Button() {
		return message_button;
	}

	public void setMessage_Button(MessageButtonVO message_button) {
		this.message_button = message_button;
	}

}