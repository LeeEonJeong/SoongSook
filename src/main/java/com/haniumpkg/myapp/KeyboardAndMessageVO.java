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
	
	public KeyboardVO getKeyboardVO() {
		return keyboardVO;
	}

	public void setKeyboardVO(KeyboardVO keyboardVO) {
		this.keyboardVO = keyboardVO;
	}

	public MessageVO getMessageVO() {
		return messageVO;
	}

	public void setMessageVO(MessageVO messageVO) {
		this.messageVO = messageVO;
	}

	
}
