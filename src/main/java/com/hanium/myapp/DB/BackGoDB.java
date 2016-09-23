package com.hanium.myapp.DB;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class BackGoDB {
	
	private int backState;
	
	public BackGoDB(int currentUserState, SqlSession sqlSession)
	{
	    HashMap<String, String> input = new HashMap<String, String>();
		input.put("no", String.valueOf(currentUserState));
		List<HashMap<String, String>> outputs = sqlSession.selectList("userControlMapper.back_state", input);
		Map<String, String> re = null;
		
		re = outputs.get(0);
		String parent = String.valueOf(re.get("parent"));
		
		backState = Integer.parseInt(parent);
	}
	
	public int getbackState() { return this.backState;}
	
}
