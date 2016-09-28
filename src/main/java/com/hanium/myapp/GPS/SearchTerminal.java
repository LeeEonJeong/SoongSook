package com.hanium.myapp.GPS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.XML;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.hanium.myapp.HomeController;
import com.haniumpkg.myapp.MessageButtonVO;
import com.haniumpkg.myapp.MessageVO;
import com.haniumpkg.myapp.PhotoVO;


public class SearchTerminal {
	private String text = "ÅÍ¹Ì³Î °Ë»öÀ» ¼±ÅÃÇÏ¼Ì½À´Ï´Ù. °í°´ÀÌ ¿øÇÏ´Â ÅÍ¹Ì³ÎÀ» ÀÔ·ÂÇÏ¼¼¿ä.\n";
	private ArrayList<String> Terminal_Name;
	private int no = 3110;
	private MessageVO messageVO = new MessageVO();
	private MessageButtonVO mb;
	private PhotoVO photo;

	public SearchTerminal(int currentUserState, String userAnswerString, String user_key) throws Exception
	{
		if(currentUserState == 3130)
		{
			Terminal_Name = HomeController.getUserSavingList(user_key);
			String terminal_name = Terminal_Name.get(Integer.parseInt(userAnswerString) - 1);
			
			System.out.println("terminal_name =" + terminal_name);
			mb = new MessageButtonVO("ÅÍ¹Ì³Î", "http://111.118.36.170:5001/myapp/?terminal_name=" + terminal_name);
			PhotoVO photo = new PhotoVO();
			photo.setUrl("http://cfile215.uf.daum.net/image/232F0C39525BDA410ACAE3");
			photo.setWidth(640);
			photo.setHeight(480);
			messageVO.setPhoto(photo);
			messageVO.setmessage_button(mb);
		}
		
		else if(currentUserState > no) // 3111
		{
			text = "»ç¿ëÀÚ°¡ °Ë»öÇÏ½Å ÅÍ¹Ì³ÎÀÌ ¸Â½À´Ï±î?\n";
			text += searchterminal(userAnswerString);
			HomeController.setUserSavingList(user_key, Terminal_Name);
		}
		
		else
		{
			Terminal_Name = new ArrayList<String>();
			Terminal_Name.add("terminal_search");
			HomeController.setUserSavingList(user_key, Terminal_Name);
			setNo(3111);
		}
	}
	
	public String gettext() {
		return this.text;
	}

	
	
	public String searchterminal(String userAnswerString) throws Exception
	{
		Terminal_Name = new ArrayList<String>();
		String text = "";
		
	 	StringBuilder urlBuilder = new StringBuilder("http://openapi.tago.go.kr/openapi/service/ExpBusInfoService/getExpBusTrminlList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=OeaajrjAncBXR7lHGuulY7SMYdiLLey0%2FEHM4u9Rwhzah5sjRCUygc8fngUNc%2BTBRPaKtQU%2FlnXf5HNj4rA6dg%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("999", "UTF-8")); /*ï¿½Ë»ï¿½ï¿½Ç¼ï¿½*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½È£*/
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
        
        
        
        text = text + gettext(items, userAnswerString);
        

        return text;
	}
	
	
	public String gettext(JSONArray array, String userAnswerString)
	{
		String text = "";
		int index = 1;
		Terminal_Name.clear();
		
		for(int i = 0; i < array.size(); i ++)
		{
			JSONObject item = (JSONObject) array.get(i);
			String city = (String) item.get("terminalnm");
			
			if(city.contains(userAnswerString))
			{
				Terminal_Name.add(city);
				text = text + String.valueOf(index) + ". " + city + "\n";
				index++;
			}
		}
		
	//	HomeController.setUserSavingList("test", Terminal_Name);
		return text;
	}

	
	public ArrayList<String> getTerminal_Name() {
		return Terminal_Name;
	}

	public void setTerminal_Name(ArrayList<String> terminal_Name) {
		Terminal_Name = terminal_Name;
	}
	
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
	
	public MessageButtonVO getMessageButton() {return this.mb;}
	public void setMessageButton(MessageButtonVO mb) {this.mb = mb;}
	public PhotoVO getphoto() {return this.photo;}
}
