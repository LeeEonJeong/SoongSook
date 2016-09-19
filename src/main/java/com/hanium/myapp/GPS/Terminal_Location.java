package com.hanium.myapp.GPS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.StringTokenizer;



import org.json.XML;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.haniumpkg.myapp.KeyboardAndMessageVO;
import com.haniumpkg.myapp.MessageButtonVO;
import com.haniumpkg.myapp.MessageVO;
import com.haniumpkg.myapp.PhotoVO;





public class Terminal_Location extends KeyboardAndMessageVO{
	
	private String text = 
			  "터미널 위치를 선택하셨습니다~\n" +
			  "지역선택을 하세요~.\n\n" +
			  "1. 서울특별시\n" +
			  "2. 占쏙옙천 占쏙옙 占쏙옙竪�\n" + 
			  "3. 占쏙옙占쏙옙占쏙옙\n" +
			  "4. 占쏙옙占쏙옙 占쏙옙 占쏙옙청占쏙옙占쏙옙\n" +
			  "5. 占쏙옙占쏙옙 占쏙옙 占쏙옙占쏢남듸옙\n" + 
			  "6. 占쏙옙占쏙옙溝占�\n" +
			  "7. 占싸삼옙 占쏙옙 占쏙옙車껨占�\n" +
			  "8. 占쎈구 占쏙옙 占쏙옙占싹듸옙\n";
	
	private ArrayList<String> Terminal_Name;
	private MessageVO messageVO = new MessageVO();
	private MessageButtonVO mb;
	private PhotoVO photo;
	
	public Terminal_Location(int currentUserState, String userAnswerString) throws Exception, Exception
	{
		
		if(currentUserState >= 3110)
		{
			StringTokenizer st = new StringTokenizer(userAnswerString, ".");
			String parse_number = st.nextToken();
			
			//System.out.println("parse_number = " + parse_number);
			
			text = detail_terminal(parse_number);
			if(currentUserState >= 3120)
			{
			
				String terminal_name = Terminal_Name.get(Integer.parseInt(parse_number) - 1);
				
				//System.out.println("terminal_name =" + terminal_name);
				mb = new MessageButtonVO("터미널", "http://111.118.36.170:5001/myapp/?terminal_name=" + terminal_name);
				photo = new PhotoVO();
				photo.setUrl("http://cfile215.uf.daum.net/image/232F0C39525BDA410ACAE3");
				photo.setWidth(640);
				photo.setHeight(480);
				messageVO.setPhoto(photo);
				messageVO.setMessage_Button(mb);
				text = "터미널~\n";
			}
			
			else
				mb = null;
		}
		
	
		settext(text);
	}
	
	
	

	public String detail_terminal(String answer) throws IOException, Exception
	{
			Terminal_Name = new ArrayList<String>();
			int xml_start = 0;
			int xml_end = 0;
			String text = "";
			
		 	StringBuilder urlBuilder = new StringBuilder("http://openapi.tago.go.kr/openapi/service/ExpBusInfoService/getExpBusTrminlList"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=OeaajrjAncBXR7lHGuulY7SMYdiLLey0%2FEHM4u9Rwhzah5sjRCUygc8fngUNc%2BTBRPaKtQU%2FlnXf5HNj4rA6dg%3D%3D"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("999", "UTF-8")); /*占싯삼옙占실쇽옙*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*占쏙옙占쏙옙占쏙옙 占쏙옙호*/
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	 
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        
	        org.json.JSONObject obj = XML.toJSONObject(sb.toString());
	        String tmp = obj.toString();
	        JSONParser parser = new JSONParser();
	        
	        JSONObject total = (JSONObject) parser.parse(tmp); 
	        JSONObject response = (JSONObject) total.get("response");
	        JSONObject body = (JSONObject) response.get("body");
	        JSONArray items = (JSONArray)((JSONObject) body.get("items")).get("item");
	        

	        System.out.println(items.toJSONString());
	        
	        
	        switch(answer)
	        {
	        
	        case "1" : //占쏙옙占쏙옙특占쏙옙占쏙옙
	        	xml_start = 15; xml_end = 24;
	        	text = "占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싹셨쏙옙占싹댐옙.^^\n";
	        	break;
	        	
	        case "2" : //占쏙옙천 占쏙옙 占쏙옙竪�
	        	xml_start = 24; xml_end = 69;
	        	text = "占쏙옙천 占쏙옙 占쏙옙竪듸옙占� 占쏙옙占쏙옙占싹셨쏙옙占싹댐옙.^^\n";
	        	break;
	        	
	        case "3" : //占쏙옙占쏙옙占쏙옙
	        	xml_start = 69; xml_end = 83;
	        	text = "占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싹셨쏙옙占싹댐옙.^^\n";
	        	break;
	        	
	        case "4" : //占쏙옙占쏙옙 占쏙옙 占쏙옙청占쏙옙占쏙옙
	        	xml_start = 83; xml_end = 120;
	        	text  = "占쏙옙占쏙옙 占쏙옙 占쏙옙청占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싹셨쏙옙占싹댐옙.^^\n";
	        	break;
	        	
	        case "5" : //占쏙옙占쏙옙 占쏙옙 占쏙옙占쏢남듸옙
	        	xml_start = 120; xml_end = 151;
	        	text  = "占쏙옙占쏙옙 占쏙옙 占쏙옙占쏢남듸옙占쏙옙 占쏙옙占쏙옙占싹셨쏙옙占싹댐옙.^^\n";
	        	break;
	        	
	        case "6" : //占쏙옙占쏙옙溝占�
	        	xml_start = 151; xml_end = 169;
	        	text  = "占쏙옙占쏙옙溝占쏙옙占� 占쏙옙占쏙옙占싹셨쏙옙占싹댐옙.^^\n";
	        	break;
	        	
	        case "7" : //占싸삼옙 占쏙옙 占쏙옙車껨占�
	        	xml_start = 169; xml_end = 184;
	        	text  = "占싸삼옙 占쏙옙 占쏙옙車껨占쏙옙占� 占쏙옙占쏙옙占싹셨쏙옙占싹댐옙.^^\n";
	        	break;
	        	
	        case "8" : //占쎈구 占쏙옙 占쏙옙占싹듸옙
	        	xml_start = 295; xml_end = 322;
	        	text  = "占쎈구 占쏙옙 占쏙옙占싹듸옙占쏙옙 占쏙옙占쏙옙占싹셨쏙옙占싹댐옙.^^\n";
	        	break;
	        
	        }
	        
	        text = text + gettext(items, xml_start, xml_end) + "00. 占쌘로곤옙占쏙옙";
	        
	       // System.out.println(text);
	
	       // setno(3120);
	        
	        return text;
	}
	
	
	public String gettext(JSONArray array, int start, int end)
	{
		String text = "";
		int index = 1;
		
		for(int i = start; i < end; i ++)
		{
			JSONObject item = (JSONObject) array.get(i);
			String city = (String) item.get("terminalnm");
			Terminal_Name.add(city);
			text = text + String.valueOf(index) + ". " + city + "\n";
			index++;
		}
		
		return text;
	}



	public void settext(String text) { this.text = text;}
	public String gettext() {return this.text;}
	public MessageButtonVO getMessageButton() {return this.mb;}
	public void setMessageButton(MessageButtonVO mb) {this.mb = mb;}




	public PhotoVO getphoto() {return this.photo;}
	
	
}

