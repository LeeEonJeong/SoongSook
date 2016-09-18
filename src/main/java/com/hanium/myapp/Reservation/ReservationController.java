package com.hanium.myapp.Reservation;

import com.haniumpkg.myapp.KeyboardAndMessageVO;

public class ReservationController {
	private KeyboardAndMessageVO keyboardAndMessageVO;
	private int currentUserState;
	private String userAnswerString;
	private String userKey;
	private ReservationDB reservationDB;
	private final static int entireReservation = 111;
	
	
	public ReservationController (int currentUserState, String userAnswerString, String userKey) {
		this.currentUserState = currentUserState;
		this.userAnswerString = userAnswerString;
		//reservationDB = new reservationDB(currentUserState, userAnswerString, userKey);
		//String reservationConfirm = reservationDB.getReservationConfirm();
		analysisState();
	}

	public void analysisState() {
		int reservationMethod = currentUserState/10;
		switch(reservationMethod) {
			case entireReservation:
				break;
		}
	}
	
	public KeyboardAndMessageVO getKeyboardAndMessageVO() {
		return keyboardAndMessageVO;
	}

	public void setKeyboardAndMessageVO(KeyboardAndMessageVO keyboardAndMessageVO) {
		this.keyboardAndMessageVO = keyboardAndMessageVO;
	}
}

