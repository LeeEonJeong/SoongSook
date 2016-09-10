/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////// 사용자로부터 json 형식으로 된 object에 대한 요청을 서버에서 받고 파싱하는 클래스 ///////////////////////
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
	}
	
	public String getrequestParsing()
	{
		return this.requestParsing;
	}
}
