package com.hanium.myapp;

import com.hanium.myapp.GPS.MyLocation.MyLocation;
import com.hanium.myapp.GPS.Terminal_Location.Terminal_Location;

public class FunctionController { // 해당 함수에 대한 텍스트 리턴

	private String text; 
	private int location;
	
	public String getaddress(int current, String answer) //current는 현재위치 answer은 사용자가 입력한 텍스트
	{
		switch(current)
		{
		
		/*
			case 2000:
			{
				if(answer.contains("1"))
				{
					alarm_setting init = new alarm_setting();
					text = init.gettext();
					location = init.getno();
				}
				
				break;
			}
		*/
			
			
			case 3000 :
			{
				if(answer.contains("1"))
				{
					Terminal_Location init = new Terminal_Location();
					text = init.initmessage();
					location = init.getno();
				}
				
				else if(answer.contains("2"))
				{
					MyLocation init = new MyLocation();
					text = init.initmessage();
					location = init.getno();
				}
				
				break;
			}
			
			
			default : //basic
			{
				text = "0";
				location = 0;
				break;
			}
		}
		
		return this.text;
	}

	public int get_currentlocation() { return this.location;}
}
