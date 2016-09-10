package com.hanium.myapp.DB;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class UpdateDB {
	
	
	public UpdateDB(String user_key, int location, SqlSession sqlSession)
	{
		
		 HashMap<String, String> input = new HashMap<String, String>();
		 input.put("user_key", user_key);
		 input.put("current_location", String.valueOf(location));
		 List<HashMap<String, String>> outputs = sqlSession.selectList("userControlMapper.client_update", input);
		 
	}
	
}
