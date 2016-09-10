package com.hanium.myapp.GPS;

/**
 * Handles requests for the application home page.
 */

public class HomeGPS {
	
	private String text;
	private int no;
	
	public String initmessage()
	{
		String text = "환영합니다. 당신은 위치 확인을 선택하였습니다~\n" +
					  "1. 터미널 위치 선택\n" +
					  "2. 나의 현재 위치 선택";
			
		this.text = text;
		this.no = 3000; 
				
		return this.text;
	}
	
	public int getno() { return this.no; }
	
}
