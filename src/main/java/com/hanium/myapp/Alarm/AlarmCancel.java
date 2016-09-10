package com.hanium.myapp.Alarm;

public class AlarmCancel {
	private String text;
	private int state;
	
	public String initmessage()
	{
		String text = "환영합니다. 당신은  알람 취소을  선택하였습니다~\n" +
					  "취소할 번호를 선택해 주세요.";
		
		this.text = text;
		this.state = 2300; 
				
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
