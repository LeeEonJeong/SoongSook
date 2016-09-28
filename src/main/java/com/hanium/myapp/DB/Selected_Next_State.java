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

import com.hanium.myapp.HomeController;

public class Selected_Next_State {
	private int nextState;
	private String user_key;
	
	
	public Selected_Next_State(int currentUserState, String userAnswerString, SqlSession sqlSession, String user_key) throws Exception
	{
		int i = 0;
		int AnswerNumber = 0;

		StringTokenizer st = new StringTokenizer(userAnswerString, ".");
		
		
		try {
			String Parse_Answer_Number = (String) (userAnswerString.contains(".") ? 
					st.nextToken() : userAnswerString);
			
			if(userAnswerString.equals("예매"))
			{
				AnswerNumber = 1;
				currentUserState = 0;
			}
			
			else if(userAnswerString.equals("알람"))
			{
				AnswerNumber = 2;
				currentUserState = 0;
			}
			
			else if(userAnswerString.equals("위치 안내"))
			{
				AnswerNumber = 3;
				currentUserState = 0;
			}
			
			
			else
				AnswerNumber = Integer.parseInt(Parse_Answer_Number);
			
		} catch(Exception e)
		
		{
			if(HomeController.getUserSavingList(user_key).isEmpty())
			{	
				nextState = currentUserState;
				return ;
			}
		}
			
		
		
//		System.out.println("AnserNumber = " + AnswerNumber);
		
			
	    HashMap<String, Integer> input = new HashMap<String, Integer>();
		input.put("parent", currentUserState);
		List<HashMap<String, Integer>> outputs = sqlSession.selectList("userControlMapper.next_state", input);
		Map<String, Integer> extractResult = null;

		if(outputs.size()!= 1)
		{
			while(outputs.get(i)!=null)
			{
				extractResult = outputs.get(i);
				int no = extractResult.get("no");
			
				if(AnswerNumber == i + 1)
				{
					nextState = no;
					break;
				}
			
				i++;
			}
		}
		
		else
		{
			extractResult = outputs.get(i);
			int no = extractResult.get("no");
			nextState = no;
		}
			
		System.out.println("Selected_Next_state = " + nextState);
	}
	
	public int getNextState() {return this.nextState;}
}
