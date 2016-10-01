package com.hanium.myapp;

import com.hanium.myapp.DB.BackGoDB;
import com.hanium.myapp.DB.SelectNextState;
import com.hanium.myapp.GPS.GPSController;
import com.hanium.myapp.Reservation.ReservationController;
import com.haniumpkg.myapp.KeyboardAndMessageVO;


import org.apache.ibatis.session.SqlSession;


public class FunctionController extends KeyboardAndMessage_Templete implements CurrentState_Templete{
	
	private int updatedUserState;
	private SelectNextState stateController;
	private String user_key;
	
	public FunctionController(String user_key) {this.user_key = user_key;}
	
	public KeyboardAndMessageVO getSystemAnswerMsgAndKeyboard
	(int previousUserState, String userAnswerString, SqlSession sqlSession) throws Exception
	{
		
		int selectedFunction;
		int currentUserState;
		KeyboardAndMessageVO keyboardAndMessageVO = new KeyboardAndMessageVO();
		
		if(userAnswerString.equals("00")) //뒤로 가기
		{
			BackGoDB back = new BackGoDB(previousUserState, sqlSession);
			currentUserState = back.getbackState();
		}
		
		else if(userAnswerString.equals("ㄱ")) //처음부터 다시 가기
			currentUserState = 0;
	
		
		else 
		{
			stateController = new SelectNextState(previousUserState, userAnswerString, sqlSession, user_key); 
			currentUserState = stateController.getNextState();
		}
	
		selectedFunction = currentUserState / 1000;
		
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
	public void setUpdatedUserState(int no) { this.updatedUserState = no;}
	
	@Override
	public int getUpdatedUserState() {return this.updatedUserState;}

	public boolean isNoError(KeyboardAndMessageVO keyboardAndMessageVO) {
		if(keyboardAndMessageVO.getMessage().getText().equals("error"))
			return false;
		else
			return true;
	}
}
