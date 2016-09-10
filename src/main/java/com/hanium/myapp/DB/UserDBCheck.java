package com.hanium.myapp.DB;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class UserDBCheck {
	
	private int result;//현재 사용자가 있는 State 번호
	
	public UserDBCheck(HttpServletRequest req, JSONObject jsonObj, Logger logger, SqlSession sqlSession) throws ParseException
	{
		String user_key =  (String) jsonObj.get("user_key");
	    HashMap<String, String> input = new HashMap<String, String>();
		input.put("user_key", user_key);
	//	logger.info(input.get("user_key"));
		List<HashMap<String, String>> outputs = sqlSession.selectList("userControlMapper.client_check", input);
		String get_userkey = "0";
		Map<String, String> re = null;
		String get_current_location = "";
	
		if(!outputs.isEmpty())
		{
			re = outputs.get(0);
			get_userkey = re.get("user");
			get_current_location = String.valueOf(re.get("state")); 

		}
		
	    if(!get_userkey.equals(user_key)) // if there is no a client in DB,
	    {
	    	outputs = sqlSession.selectList("userControlMapper.client_insert", input); //insert
	    }
	    
	   
	    result = Integer.parseInt(get_current_location);
	}
	
	public int get_result() {return this.result;}
	
}
