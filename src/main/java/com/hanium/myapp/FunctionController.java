package com.hanium.myapp;

import com.hanium.myapp.DB.BackGoDB;
import com.hanium.myapp.DB.Selected_Next_State;
import com.hanium.myapp.GPS.GPSController;
import com.hanium.myapp.Reservation.ReservationController;
import com.haniumpkg.myapp.KeyboardAndMessageVO;
import com.haniumpkg.myapp.MessageVO;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.parser.ParseException;

public class FunctionController extends KeyboardAndMessage_Templete implements CurrentState_Templete{
	
	private int updatedUserState;
	private Selected_Next_State stateController;
	
	public KeyboardAndMessageVO getSystemAnswerMsgAndKeyboard
	(int previousUserState, String userAnswerString, String user_key, SqlSession sqlSession) throws Exception
	{
		
		int selectedFunction;
		int currentUserState;
		KeyboardAndMessageVO keyboardAndMessageVO = new KeyboardAndMessageVO();
		
		if(userAnswerString.contains("00"))
		{
			BackGoDB back = new BackGoDB(previousUserState, sqlSession);
			selectedFunction = back.getbackState() / 1000;
			currentUserState = back.getbackState();
		}
		
		else if(userAnswerString.contains("¤¡"))
		{
			selectedFunction = 0;
			currentUserState = 0;
		}
		else 
		{
			stateController = new Selected_Next_State(previousUserState, userAnswerString, sqlSession); 
			selectedFunction = stateController.getNextState() / 1000;
			currentUserState = stateController.getNextState();
		}
	
		
		switch(selectedFunction)
		{	
			case 1:
				ReservationController reservationController = new ReservationController(previousUserState, currentUserState, userAnswerString, user_key);
				keyboardAndMessageVO = reservationController.getKeyboardAndMessageVO();
				break;
				
			case 2:
				break;
				
			case 3:
				GPSController gpscontroller = new GPSController(user_key, currentUserState, userAnswerString, sqlSession);
				keyboardAndMessageVO = gpscontroller.getKeyboardAndMessageVO();
				break;
				
				
			default:
				InitController initcontroller = new InitController();
				keyboardAndMessageVO = initcontroller.getKeyboardAndMessageVO();
				break;

		}
		
		if(isNoError(keyboardAndMessageVO))
			setUpdatedUserState(currentUserState);
	
		return keyboardAndMessageVO;
	}

	@Override
	public void setUpdatedUserState(int no) {
		this.updatedUserState = no;
	}

	@Override
	public int getUpdatedUserState() {return this.updatedUserState;}

	public boolean isNoError(KeyboardAndMessageVO keyboardAndMessageVO) {
		if(keyboardAndMessageVO.getMessage().getText().equals("error"))
			return false;
		else
			return true;
	}
}
