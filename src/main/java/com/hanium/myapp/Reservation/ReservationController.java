package com.hanium.myapp.Reservation;

import com.haniumpkg.myapp.KeyboardAndMessageVO;

public class ReservationController {
	private KeyboardAndMessageVO keyboardAndMessageVO;
	private int currentUserState;
	private String userAnswerString;
	
	public ReservationController (int currentUserState, String userAnswerString) {
		this.currentUserState = currentUserState;
		this.userAnswerString = userAnswerString;
	}

	
	
	public KeyboardAndMessageVO getKeyboardAndMessageVO() {
		return keyboardAndMessageVO;
	}

	public void setKeyboardAndMessageVO(KeyboardAndMessageVO keyboardAndMessageVO) {
		this.keyboardAndMessageVO = keyboardAndMessageVO;
	}
}

