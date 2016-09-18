package com.hanium.myapp.GPS;

public class MyLocation {
	private String text;
	private int no;
	
	public String initmessage()
	{
		String text = "환영합니다. 당신은 위치 확인을 선택하였습니다~\n" +
					  "현재 위치";
			
		this.text = text;
		this.no = 3200; 
				
		return this.text;
	}
	
	public int getno() { return this.no; }
}
