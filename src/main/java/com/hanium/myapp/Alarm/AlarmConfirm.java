package com.hanium.myapp.Alarm;

public class AlarmConfirm {
	private String text;
	private int state;
	
	public String initmessage()
	{
		String text = "환영합니다. 당신은  알람 확인을  선택하였습니다~\n 확인할 알람 내역을 선택해 주세요." ;
		
		this.text = text;
		this.state = 2200; 
				
		return this.text;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getNo() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	
}
