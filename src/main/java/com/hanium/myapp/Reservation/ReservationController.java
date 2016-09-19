package com.hanium.myapp.Reservation;

import com.haniumpkg.myapp.KeyboardAndMessageVO;
import com.haniumpkg.myapp.KeyboardVO;
import com.haniumpkg.myapp.MessageVO;

public class ReservationController {
	private KeyboardAndMessageVO keyboardAndMessageVO;
	private KeyboardVO keyboardVO;
	private String messageVOString;
	private int currentUserState;
	private int previousUserState;
	private String userAnswerString;
	private String userKey;
	private ReservationDB reservationDB;
	private final static int entireReservation = 111;
	private final static int locationReservation = 112;
	private final static int favoriteReservation = 113;
	private final static int reservationState = 11;
	private final static int busSelection = 12;
	private final static int seatSelection = 13;
	private final static int paymentSelection = 14;
	private final static int reservationComplete = 15;
	private final static int setFavoriteReservation = 16;
	private final static int setAlarm = 17;
	
	
	public ReservationController (int previousUserState, int currentUserState, String userAnswerString, String userKey) {
		this.currentUserState = currentUserState;
		this.userAnswerString = userAnswerString;
		this.previousUserState = previousUserState;
		
		if(ErrorCheck.errorCheck(currentUserState, userKey)) {
			//reservationDB = new ReservationDB(currentUserState, userAnswerString, userKey);
			//String reservationConfirm = reservationDB.getReservationConfirm();
			keyboardVO = new KeyboardVO();
			analysisState();
			//setSystemAnswer(reservationConfirm);
		}
		else {
			
		}
		
		
	}

	private void setSystemAnswer(String reservationConfirm) {
		MessageVO messageVO = new MessageVO(reservationConfirm + messageVOString);
		keyboardAndMessageVO = new KeyboardAndMessageVO(keyboardVO, messageVO);
	}

	public void analysisState() {
		
		switch(currentUserState/10) {

			case reservationState:
					reservationSelection();
				break;
			case busSelection:
					messageVOString = "원하는 버스 번호를 입력해주세요";
				break;
				
			case seatSelection:
					messageVOString = "원하는 좌석 번호를 입력해주세요";
				break;
				
			case paymentSelection:
					setPaymentString();
				break;
				
			case reservationComplete:
					messageVOString = "위와 같이 예약이 완료되었습니다.";
				break;
	
			case setFavoriteReservation:
					messageVOString = "즐겨찾기에 넣으시겠습니까?";
				break;
			
			case setAlarm:
					messageVOString = "알람설정을 하시겠습니까?";
				break;
			default:
				break;
		}
	}
	
	private void setPaymentString() {
		switch(currentUserState) {
			case 1411:
					messageVOString = "신용카드 종류를 입력해주세요";
				break;
			case 1412:
					messageVOString = "카드 번호를 입력해주세요";
				break;
			
			case 1413:
					messageVOString = "유효기간을 입력해주세요(MMYY)";
				break;
			case 1414:
					messageVOString = "비밀번호 앞 두자리를 입력해주세요";
				break;
			case 1415:
					messageVOString = "주민등록번호 앞 6자리를 입력해주세요";
				break;
				
			case 1421:
					messageVOString = "통신사를 선택해주세요";
					keyboardVO.addMenu("SKT", "KT", "LG");
					
				break;
			case 1422:
					messageVOString = "핸드폰번호를 입력해주세요";
				break;
			case 1423:
					messageVOString = "주민등록번호 앞 6자리와 뒤 1자리를 입력해주세요";
				break;
			case 1424:
					messageVOString = "인증번호가 발신되었습니다. 수신하시면 입력해주세요";
				break;
			case 1425:
					messageVOString = "";
				break;		
		}
		
	}

	private void reservationSelection() {
		int reservationMethod = currentUserState/10;
		
		switch(reservationMethod) {
			case entireReservation:
				setMsgForEntireReservation();				
				break;

			case locationReservation:
				setMsgForLocationResertavtion();
				break;
	
			case favoriteReservation:
				setMsgForFavoriteReservation();
				break;
				
			default:
				break;
		}
	}

	private void setMsgForFavoriteReservation() {
		messageVOString = "즐겨찾기 번호를 입력해주세요";
	}

	private void setMsgForEntireReservation() {
		messageVOString = "출발터미널.도착너미널.출발날짜.등급.어른매수.아동매수 를 입력해주세요\n(예: 동서울터미널.부산터미널.07.12.우등.2.3)";
	}

	private void setMsgForLocationResertavtion() {
		switch(currentUserState) {
			case 1120:
					messageVOString = "출발지역을 입력해주세요";
				break;
				
			case 1121:
					messageVOString = "출발터미널을 입력해주세요";
				break;
				
			case 1122:
					messageVOString = "도착지역을 입력해주세요";
				break;
			
			case 1123:
					messageVOString = "도착터미널을 입력해주세요";
				break;
				
			case 1124:
					messageVOString = "출발날짜를 입력해주세요";
				break;
			
			case 1125:
					messageVOString = "등급.어른매수.아동매수을 입력해주세요\n(에:우등.2.3)";
				break;
			
			case 1126:
					messageVOString = "";
				break;
				
		}
	}
	
	public KeyboardAndMessageVO getKeyboardAndMessageVO() {
		return keyboardAndMessageVO;
	}	
}

