package com.hanium.myapp;

import com.hanium.myapp.GPS.MyLocation.MyLocation;
import com.hanium.myapp.GPS.Terminal_Location.Terminal_Location;
import com.haniumpkg.myapp.KeyboardAndMessageVO;

public class FunctionController {
	private String text; 
	private int location;
	
	public KeyboardAndMessageVO getSystemAnswerMsgAndKeyboard(int currentUserState, String userAnswerString)
	{
		currentUserState = currentUserState/1000;
		KeyboardAndMessageVO keyboardAndMessageVO = new KeyboardAndMessageVO();
		
		switch(currentUserState)
		{	

			case 1:
				
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
