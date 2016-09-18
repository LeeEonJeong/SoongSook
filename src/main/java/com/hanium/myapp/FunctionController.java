package com.hanium.myapp;

import com.hanium.myapp.GPS.MyLocation.MyLocation;
import com.hanium.myapp.GPS.Terminal_Location.Terminal_Location;
import com.hanium.myapp.Reservation.ReservationController;
import com.haniumpkg.myapp.KeyboardAndMessageVO;

public class FunctionController {
	private String text; 

	
	public KeyboardAndMessageVO getSystemAnswerMsgAndKeyboard(int currentUserState, String userAnswerString, String user_key)
	{
		currentUserState = currentUserState/1000;
		KeyboardAndMessageVO keyboardAndMessageVO = new KeyboardAndMessageVO();
		
		switch(currentUserState)
		{	

			case 1:
				ReservationController reservationController = new ReservationController(currentUserState, userAnswerString, user_key);
				keyboardAndMessageVO = reservationController.getKeyboardAndMessageVO();
				break;
				
			case 2:
				break;
				
			case 3: 
				break;
				
			default:
				break;

		}
		
		return keyboardAndMessageVO;
	}

	public int get_currentlocation() { return this.location;}
}
