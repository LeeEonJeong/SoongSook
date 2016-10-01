package com.hanium.myapp.DB;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;


import com.hanium.myapp.HomeController;

public class SelectNextState {
	private int nextState;

	public SelectNextState(int currentUserState, String userAnswerString, SqlSession sqlSession, String user_key) throws Exception
	{
		int i = 0;
		int AnswerNumber = 0;   

		
		try {
			
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
				AnswerNumber = Integer.parseInt(userAnswerString);
			
		} 
		
		catch(Exception e)
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
			
		System.out.println("SelectNextState = " + nextState);
	}
	
	public int getNextState() {return this.nextState;}
}
