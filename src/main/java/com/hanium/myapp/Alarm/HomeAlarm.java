package com.hanium.myapp.Alarm;

/**
 * Handles requests for the application home page.
 */

public class HomeAlarm {
	
	private String text;
	private int state;
	
	public String initMessage()
	{
		String text = "환영합니다. 당신은 알람을 선택하였습니다~\n" +
					  "1. 알람 설정 선택" +
					  "2. 알람 확인 선택" +
					  "3. 알람 취소 선택";
		this.text = text;
		this.state = 2000; 
				
		return this.text;
	}
	
	public int getState() { return this.state; }
	
}
