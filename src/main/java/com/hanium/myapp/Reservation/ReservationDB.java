package com.hanium.myapp.Reservation;

import com.hanium.myapp.DB.UserDBCheck;

public class ReservationDB {
	//currentUserState, userAnswerString, userKey를 넘겨받아서, 스테이트별로 저장하는거 case나누고 
	//현재까지 저장된 디비를 스트링으로 리턴 method : getReservationConfirm();
	private int currentUserState;
	private String userAnswerString;
	private String userKey;
	private String returnMessage="initialize";
	private UserDBCheck userDBCheck;
	
	public ReservationDB(int currentUserState, String userAnswerString, String userKey) {
		this.currentUserState = currentUserState;
		this.userAnswerString = userAnswerString;
		this.userKey = userKey;
	}
	
	public String getReservationConfirm(){
		switch (currentUserState) {		
			case 1111:	//출발터미널.토착터미널.출발날짜.등급.어른매수.아동매수 입력(예: 동서울터미널.부산터미널.07.12.우등.2.3)
				splitAndSaveInline();
				returnMessage = userAnswerString;				
				break;
			case 1122 :
				break;
			case 1124 :
				break;
			case 1125 :
				break;
			case 1126 :
				break;
			case 1131 :
				break;
			case 1200 :
				break;
			case 1300 :
				break;
			case 1400 :
				break;
			case 1411 :
				break;
			case 1412 :
				break;
			case 1413 :
				break;
			case 1414 :
				break;
			case 1415 :
				break;
			case 1421 :
				break;
			case 1422 :
				break;
			case 1423 :
				break;
			case 1424 :
				break;
			case 1425 :
				break;
			default:
				returnMessage = "error";
				break;
		}		
		return returnMessage;
	}	
	private void splitAndSaveInline(){
		String[] reservationLine = new String[6];
		reservationLine = userAnswerString.split(".");
		
	}
}
