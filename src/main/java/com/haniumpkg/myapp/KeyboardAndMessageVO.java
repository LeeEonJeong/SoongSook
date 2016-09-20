package com.haniumpkg.myapp;

public class KeyboardAndMessageVO {
	private KeyboardVO keyboardVO;
	private MessageVO messageVO;
	
	public KeyboardAndMessageVO() {
		// TODO Auto-generated constructor stub
	}

	public KeyboardAndMessageVO(KeyboardVO keyboardVO, MessageVO messageVO) {
		// TODO Auto-generated constructor stub
		this.keyboardVO = keyboardVO;
		this.messageVO = messageVO;
	}
	
	public KeyboardVO getKeyboard() {
		return keyboardVO;
	}

	public void setKeyboard(KeyboardVO keyboardVO) {
		this.keyboardVO = keyboardVO;
	}

	public MessageVO getMessage() {
		return messageVO;
	}

	public void setMessage(MessageVO messageVO) {
		this.messageVO = messageVO;
	}

	
}
