package com.hanium.myapp.GPS.Terminal_Location;

public class Terminal_Location {
	
	private String text;
	private int no;
	
	public String initmessage()
	{
		String text = "환영합니다. 당신은 위치 확인을 선택하였습니다~\n" +
					  "지역 선택을 해주세요.";
			
		this.text = text;
		this.no = 3100; 
				
		return this.text;
	}
	
	public int getno() { return this.no; }
	
}
