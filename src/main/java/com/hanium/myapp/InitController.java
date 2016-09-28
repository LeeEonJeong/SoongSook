package com.hanium.myapp;


import com.haniumpkg.myapp.KeyboardAndMessageVO;
import com.haniumpkg.myapp.KeyboardVO;
import com.haniumpkg.myapp.MessageVO;


public class InitController extends KeyboardAndMessage_Templete{

	private String text = "숭숙버스터미널에 오신 것을 환영합니다^^?\n\n" +
			  "1. 예매 설정\n" +
		      "2. 알람 설정\n" +
			  "3. 위치 안내 설정";
	
	public InitController()
	{
		
		keyboardAndMessageVO = new KeyboardAndMessageVO();
		
		String text = this.text;
		
		MessageVO messageVO = new MessageVO(text);
		KeyboardVO keyboardVO = null;
		
		keyboardAndMessageVO.setMessage(messageVO);
		keyboardAndMessageVO.setKeyboard(keyboardVO);
	}
	
	public String gettext() {return this.text;}
	
}
