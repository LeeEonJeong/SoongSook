package com.hanium.myapp.DB;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.json.simple.JSONObject;


public class UserDBCheck {
	
	private int result;
	
	public UserDBCheck(JSONObject jsonObj, SqlSession sqlSession) throws Exception
	{
		String user_key =  (String) jsonObj.get("user_key");
	    HashMap<String, String> input = new HashMap<String, String>();
		input.put("user_key", user_key);
		List<HashMap<String, String>> outputs = sqlSession.selectList("userControlMapper.client_check", input);
		String get_userkey = "0";
		Map<String, String> ValueResult = null;
		String get_current_location = "";
	
		if(!outputs.isEmpty())
		{
			ValueResult = outputs.get(0);
			get_userkey = ValueResult.get("user");
			get_current_location = String.valueOf(ValueResult.get("state")); 
		}
		
	    if(!get_userkey.equals(user_key))
	    {
	    	outputs = sqlSession.selectList("userControlMapper.client_insert", input); //insert
	    }
	    
	   
	    result = Integer.parseInt(get_current_location);
	}
	
	public int getLastState() {return this.result;}
	
}
