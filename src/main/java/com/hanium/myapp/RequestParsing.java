/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////// ����ڷκ��� json �������� �� object�� ���� ��û�� �������� �ް� �Ľ��ϴ� Ŭ���� ///////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////

package com.hanium.myapp;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public class RequestParsing {
	
	private String requestParsing; 
	
	public RequestParsing (HttpServletRequest req) throws IOException
	{

		   StringBuilder sb = new StringBuilder();
		    BufferedReader reader = req.getReader();
		    try {
		        String line;
		        while ((line = reader.readLine()) != null) {
		            sb.append(line).append('\n');
		        }
		    } finally {
		        reader.close();
		    }
		    
		    requestParsing = sb.toString();
		 //  System.out.println(requestParsing);
	}
	
	public String getrequestParsing()
	{
		return this.requestParsing;
	}
}
