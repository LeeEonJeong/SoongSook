package com.hanium.myapp.Reservation;

import com.haniumpkg.myapp.KeyboardAndMessageVO;
import com.haniumpkg.myapp.KeyboardVO;
import com.haniumpkg.myapp.MessageVO;

public class ReservationController {
	private KeyboardAndMessageVO keyboardAndMessageVO;
	private KeyboardVO keyboardVO;
	private String messageVOString;
	private int currentUserState;
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
		
		if(ErrorCheck.isNoError(previousUserState, userAnswerString)) {
			ReservationDB reservationDB = new ReservationDB(currentUserState, userAnswerString, userKey);
			String reservationConfirm = reservationDB.getReservationConfirm();
			keyboardVO = new KeyboardVO();
			analysisState();
			setSystemAnswer(reservationConfirm + messageVOString);
		}
		else {
			setErrorSystemAnswer();
		}
		
	}

	private void setErrorSystemAnswer() {
		MessageVO messageVO = new MessageVO("error");
		keyboardAndMessageVO = new KeyboardAndMessageVO(keyboardVO, messageVO);
	}

	private void setSystemAnswer(String systemAnswerString) {
		MessageVO messageVO = new MessageVO(systemAnswerString);
		keyboardAndMessageVO = new KeyboardAndMessageVO(keyboardVO, messageVO);
	}

	public void analysisState() {
		
		switch(currentUserState/10) {

			case reservationState:
					reservationSelection();
				break;
			case busSelection:
					messageVOString = "���ϴ� ���� ��ȣ�� �Է����ּ���";
				break;
				
			case seatSelection:
					messageVOString = "���ϴ� �¼� ��ȣ�� �Է����ּ���";
				break;
				
			case paymentSelection:
					setPaymentString();
				break;
				
			case reservationComplete:
					messageVOString = "���� ���� ������ �Ϸ�Ǿ����ϴ�.";
				break;
	
			case setFavoriteReservation:
					messageVOString = "���ã�⿡ �����ðڽ��ϱ�?";
				break;
			
			case setAlarm:
					messageVOString = "�˶������� �Ͻðڽ��ϱ�??";
				break;
			default:
				break;
		}
	}
	
	private void setPaymentString() {
		switch(currentUserState) {
			case 1411:
					messageVOString = "�ſ�ī�� ������ �Է����ּ���";
				break;
			case 1412:
					messageVOString = "ī�� ��ȣ�� �Է����ּ���";
				break;
			
			case 1413:
					messageVOString = "��ȿ�Ⱓ�� �Է����ּ���(MMYY)";
				break;
			case 1414:
					messageVOString = "��й�ȣ �� ���ڸ��� �Է����ּ���";
				break;
			case 1415:
					messageVOString = "�ֹε�Ϲ�ȣ �� 6�ڸ��� �Է����ּ���";
				break;
				
			case 1421:
					messageVOString = "��Ż縦 �������ּ���";
					keyboardVO.addMenu("SKT", "KT", "LG");
					
				break;
			case 1422:
					messageVOString = "�ڵ�����ȣ�� �Է����ּ���";
				break;
			case 1423:
					messageVOString = "�ֹε�Ϲ�ȣ �� 6�ڸ��� �� 1�ڸ��� �Է����ּ���";
				break;
			case 1424:
					messageVOString = "������ȣ�� �߽ŵǾ����ϴ�. �����Ͻø� �Է����ּ���";
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
		messageVOString = "���ã�� ��ȣ�� �Է����ּ���";
	}

	private void setMsgForEntireReservation() {
		messageVOString = "����͹̳�.�����ʹ̳�.��߳�¥.���.��ż�.�Ƶ��ż� �� �Է����ּ���\n(��: �������͹̳�.�λ��͹̳�.07.12.���.2.3)";
	}

	private void setMsgForLocationResertavtion() {
		switch(currentUserState) {
			case 1120:
					messageVOString = "��������� �Է����ּ���";
				break;
				
			case 1121:
					messageVOString = "����͹̳��� �Է����ּ���";
				break;
				
			case 1122:
					messageVOString = "���������� �Է����ּ���";
				break;
			
			case 1123:
					messageVOString = "�����͹̳��� �Է����ּ���";
				break;
				
			case 1124:
					messageVOString = "��߳�¥�� �Է����ּ���";
				break;
			
			case 1125:
					messageVOString = "���.��ż�.�Ƶ��ż��� �Է����ּ���\n(��:���.2.3)";
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

