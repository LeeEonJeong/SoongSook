package com.hanium.myapp.Alarm;

public class AlarmConfig {
	private String text;
	private int state;
	
	public String initmessage()
	{
		String text = "환영합니다. 당신은  알람 확인을  선택하였습니다~\n"
				 		+ "알람 설정할 예약을 선택을 해주세요.";
		
		this.text = text;
		this.state = 2100; 
				
		return this.text;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
}
