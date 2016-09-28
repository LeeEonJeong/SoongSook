package com.hanium.myapp.GPS;

import org.apache.ibatis.session.SqlSession;

import com.hanium.myapp.KeyboardAndMessage_Templete;
import com.hanium.myapp.DB.ExtractReservationDB;

public class GuideArriveLeftTime extends KeyboardAndMessage_Templete{
	private String text;
	private int no;
	
	public GuideArriveLeftTime()
	{
		String text = "환영합니다. 당신은 도착시간 / 남은시간 확인을 선택하였습니다~\n";
		this.text = text;
		this.no = 3200; 
	}
	
	public String getString(String user_key, SqlSession sqlSession) throws Exception { 
		ExtractReservationDB extract = new ExtractReservationDB(user_key, sqlSession);
		text = extract.gettext();
		return text;
	}
	
	public int getno() { return this.no; }
	public String gettext() {return this.text;}
}
