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
package com.hanium.myapp;

import com.hanium.myapp.DB.Selected_Next_State;
import com.hanium.myapp.GPS.GPSController;
import com.hanium.myapp.Reservation.ReservationController;
import com.haniumpkg.myapp.KeyboardAndMessageVO;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.parser.ParseException;

public class FunctionController extends KeyboardAndMessage_Templete implements CurrentState_Templete{
	
	private int no;
	private Selected_Next_State sns;
	
	public KeyboardAndMessageVO getSystemAnswerMsgAndKeyboard
	(int currentUserState, String userAnswerString, String user_key, SqlSession sqlSession) throws Exception
	{
		sns = new Selected_Next_State(currentUserState,userAnswerString, sqlSession); 
		int CurrentUserState = sns.getNextState() / 1000;
		KeyboardAndMessageVO keyboardAndMessageVO = new KeyboardAndMessageVO();
		
			
		//System.out.println("currentUserState = " + currentUserState);
		
	
		switch(CurrentUserState)
		{	
			case 1:
				ReservationController reservationController = new ReservationController(currentUserState, userAnswerString, user_key);
				keyboardAndMessageVO = reservationController.getKeyboardAndMessageVO();
				break;
				
			case 2:
				break;
				
			case 3:
				GPSController gpscontroller = new GPSController(sns.getNextState(), userAnswerString, sqlSession);
				keyboardAndMessageVO = gpscontroller.getKeyboardAndMessageVO();
				setno(sns.getNextState());
				break;
				
				
			default:
				InitController initcontroller = new InitController();
				keyboardAndMessageVO = initcontroller.getKeyboardAndMessageVO();
				break;

		}
		
		return keyboardAndMessageVO;
	}

	@Override
	public void setno(int no) {
		this.no = no;
	}

	@Override
	public int getno() {return this.no;}
	
	
}
package com.hanium.myapp;

import com.hanium.myapp.DB.Selected_Next_State;
import com.hanium.myapp.GPS.GPSController;
import com.hanium.myapp.Reservation.ReservationController;
import com.haniumpkg.myapp.KeyboardAndMessageVO;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.parser.ParseException;

public class FunctionController extends KeyboardAndMessage_Templete implements CurrentState_Templete{
	
	private int no;
	private Selected_Next_State sns;
	
	public KeyboardAndMessageVO getSystemAnswerMsgAndKeyboard
	(int currentUserState, String userAnswerString, String user_key, SqlSession sqlSession) throws Exception
	{
		sns = new Selected_Next_State(currentUserState,userAnswerString, sqlSession); 
		int CurrentUserState = sns.getNextState() / 1000;
		KeyboardAndMessageVO keyboardAndMessageVO = new KeyboardAndMessageVO();
		
			
		//System.out.println("currentUserState = " + currentUserState);
		
	
		switch(CurrentUserState)
		{	
			case 1:
				ReservationController reservationController = new ReservationController(currentUserState, userAnswerString, user_key);
				keyboardAndMessageVO = reservationController.getKeyboardAndMessageVO();
				break;
				
			case 2:
				break;
				
			case 3:
				GPSController gpscontroller = new GPSController(sns.getNextState(), userAnswerString, sqlSession);
				keyboardAndMessageVO = gpscontroller.getKeyboardAndMessageVO();
				setno(sns.getNextState());
				break;
				
				
			default:
				InitController initcontroller = new InitController();
				keyboardAndMessageVO = initcontroller.getKeyboardAndMessageVO();
				break;

		}
		
		return keyboardAndMessageVO;
	}

	@Override
	public void setno(int no) {
		this.no = no;
	}

	@Override
	public int getno() {return this.no;}
	
	
}
package com.hanium.myapp;

import com.hanium.myapp.DB.Selected_Next_State;
import com.hanium.myapp.GPS.GPSController;
import com.hanium.myapp.Reservation.ReservationController;
import com.haniumpkg.myapp.KeyboardAndMessageVO;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.parser.ParseException;

public class FunctionController extends KeyboardAndMessage_Templete implements CurrentState_Templete{
	
	private int no;
	private Selected_Next_State sns;
	
	public KeyboardAndMessageVO getSystemAnswerMsgAndKeyboard
	(int currentUserState, String userAnswerString, String user_key, SqlSession sqlSession) throws Exception
	{
		sns = new Selected_Next_State(currentUserState,userAnswerString, sqlSession); 
		int CurrentUserState = sns.getNextState() / 1000;
		KeyboardAndMessageVO keyboardAndMessageVO = new KeyboardAndMessageVO();
		
			
		//System.out.println("currentUserState = " + currentUserState);
		
	
		switch(CurrentUserState)
		{	
			case 1:
				ReservationController reservationController = new ReservationController(currentUserState, userAnswerString, user_key);
				keyboardAndMessageVO = reservationController.getKeyboardAndMessageVO();
				break;
				
			case 2:
				break;
				
			case 3:
				GPSController gpscontroller = new GPSController(sns.getNextState(), userAnswerString, sqlSession);
				keyboardAndMessageVO = gpscontroller.getKeyboardAndMessageVO();
				setno(sns.getNextState());
				break;
				
				
			default:
				InitController initcontroller = new InitController();
				keyboardAndMessageVO = initcontroller.getKeyboardAndMessageVO();
				break;

		}
		
		return keyboardAndMessageVO;
	}

	@Override
	public void setno(int no) {
		this.no = no;
	}

	@Override
	public int getno() {return this.no;}
	
	
}
package com.hanium.myapp;

import com.hanium.myapp.DB.Selected_Next_State;
import com.hanium.myapp.GPS.GPSController;
import com.hanium.myapp.Reservation.ReservationController;
import com.haniumpkg.myapp.KeyboardAndMessageVO;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.parser.ParseException;

public class FunctionController extends KeyboardAndMessage_Templete implements CurrentState_Templete{
	
	private int no;
	private Selected_Next_State sns;
	
	public KeyboardAndMessageVO getSystemAnswerMsgAndKeyboard
	(int currentUserState, String userAnswerString, String user_key, SqlSession sqlSession) throws Exception
	{
		sns = new Selected_Next_State(currentUserState,userAnswerString, sqlSession); 
		int CurrentUserState = sns.getNextState() / 1000;
		KeyboardAndMessageVO keyboardAndMessageVO = new KeyboardAndMessageVO();
		
			
		//System.out.println("currentUserState = " + currentUserState);
		
	
		switch(CurrentUserState)
		{	
			case 1:
				ReservationController reservationController = new ReservationController(currentUserState, userAnswerString, user_key);
				keyboardAndMessageVO = reservationController.getKeyboardAndMessageVO();
				break;
				
			case 2:
				break;
				
			case 3:
				GPSController gpscontroller = new GPSController(sns.getNextState(), userAnswerString, sqlSession);
				keyboardAndMessageVO = gpscontroller.getKeyboardAndMessageVO();
				setno(sns.getNextState());
				break;
				
				
			default:
				InitController initcontroller = new InitController();
				keyboardAndMessageVO = initcontroller.getKeyboardAndMessageVO();
				break;

		}
		
		return keyboardAndMessageVO;
	}

	@Override
	public void setno(int no) {
		this.no = no;
	}

	@Override
	public int getno() {return this.no;}
	
	
}
