package com.hanium.myapp.DB;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.json.XML;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ExtractReservationDB {

	private String text;

	public ExtractReservationDB(String user_key, SqlSession sqlSession) throws Exception {
		int i = 0;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String yyyy_mm_dd = sdf.format(date);
		// System.out.println(yyyy_mm_dd);
		HashMap<String, String> input = new HashMap<String, String>();
		input.put("user", user_key);
		List<HashMap<String, Date>> Dateoutputs = sqlSession.selectList("userControlMapper.Extract_Terminal", input);
		Map<String, Date> extractDate = null;
		Map<String, String> extractTerminal = null;

		if (Dateoutputs.isEmpty())
			this.text = "현재 고객께서는 예약하신 티켓을 가지지 않고 있습니다~\n";

		else {
			while (Dateoutputs.get(i) != null) {
				
				extractDate = Dateoutputs.get(i);
				date = extractDate.get("dep_time");
				String CompareDate = sdf.format(date);
				
				if (CompareDate.equals(yyyy_mm_dd)) {
					List<HashMap<String, String>> Terminaloutputs = sqlSession.selectList("userControlMapper.Extract_Terminal", input);
					extractTerminal = Terminaloutputs.get(i);
					ExtractLeftTime(extractTerminal.get("dep_ter"), extractTerminal.get("arr_ter"));
					break;
				}

				i++;
			}
		}

	}

	public void ExtractLeftTime(String dep_ter, String arr_ter) throws Exception {
		 StringBuilder urlBuilder = new StringBuilder
				 ("http://openapi.tago.go.kr/openapi/service/ExpBusArrInfoService/getExpBusArrPrdtInfo"); /*URL*/
		 urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=F4qo%2FN7dKHehezrw3sxLvLC5aFvf9L0DbYeDq9WTn0cQImSsFyckHpid%2BwJx1VrlT10NCUjakWo5jBRV7kURdw%3D%3D"); /*Service Key*/
	     urlBuilder.append("&" + URLEncoder.encode("depTmnCd","UTF-8") + "=" + URLEncoder.encode("010", "UTF-8")); /*출발터미널코드*/
	     urlBuilder.append("&" + URLEncoder.encode("arrTmnCd","UTF-8") + "=" + URLEncoder.encode("700", "UTF-8")); /*도착터미널코드*/
	     urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("999", "UTF-8")); /*검색건수*/
	     urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
		
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}

		
		System.out.println(sb.toString());
		
		org.json.JSONObject obj = XML.toJSONObject(sb.toString());
		String tmp = obj.toString();
		JSONParser parser = new JSONParser();

	    JSONObject total = (JSONObject) parser.parse(tmp); 
        JSONObject response = (JSONObject) total.get("response");
        JSONObject body = (JSONObject) response.get("body");
        JSONArray items = (JSONArray)((JSONObject) body.get("items")).get("item");
		
        
	
        
        this.text = extractResult(items, dep_ter, arr_ter);
        

	}
	
	
	private String extractResult(JSONArray items, String dep_ter, String arr_ter) {
		
		String text = "현재 사용자께서 예약하신 고속버스는 운행하지 않습니다.^^;\n" +
				      "처음 화면으로 돌아갑니다.";
		
		for(int i = 0; i < items.size(); i ++)
		{
			JSONObject item = (JSONObject) items.get(i);
			
			System.out.println("item = " + item.toJSONString());
			
			String deptmnnm = (String) item.get("deptmnnm");
			String arrtmnnm = (String) item.get("arrtmnnm");
			
			System.out.println("deptmnnm = " + deptmnnm);
			System.out.println("arrtmnnm = " + arrtmnnm);
			
			if(deptmnnm.equals(dep_ter) && arrtmnnm.equals(arr_ter))
			{
				text = "현재 " + dep_ter + "에서 " + arr_ter + "가고 있는 중이며 " + 
						"현재 위치는 " + item.get("curlocnm") + "이며 앞으로 " +
						item.get("rmntm") + "남았습니다.^^\n";
				
				break;
			}
		}
		
		return text;
	}

	public String gettext() {return this.text;}

}
