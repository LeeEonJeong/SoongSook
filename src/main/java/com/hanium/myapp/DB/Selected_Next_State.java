package com.hanium.myapp.DB;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;

public class Selected_Next_State {
	private int result;
	
	public Selected_Next_State(int currentUserState, String userAnswerString, SqlSession sqlSession) throws ParseException
	{
		int i = 0;
		StringTokenizer st = new StringTokenizer(userAnswerString, ".");
		
		String Parse_Answer_Number = (String) (userAnswerString.contains(".") ? 
				st.nextToken() : userAnswerString); 
	    HashMap<String, Integer> input = new HashMap<String, Integer>();
		input.put("parent", currentUserState);
		List<HashMap<String, Integer>> outputs = sqlSession.selectList("userControlMapper.next_state", input);
		Map<String, Integer> re = null;

		
		while(outputs.get(i)!=null)
		{
			re = outputs.get(i);
			int no = re.get("no");
			
			if(Integer.parseInt(Parse_Answer_Number) == i + 1)
			{
				result = no;
				break;
			}
			
			i++;
		}
			
		System.out.println("Selected_Next_state = " + result);
	}
	
	public int getNextState() {return this.result;}
}
