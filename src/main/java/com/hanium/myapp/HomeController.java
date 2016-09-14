package com.hanium.myapp;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanium.myapp.DB.UpdateDB;
import com.hanium.myapp.DB.UserDBCheck;
import com.hanium.myapp.GPS.HomeGPS;
import com.hanium.myapp.Alarm.AlarmController;
import com.hanium.myapp.Alarm.HomeAlarm;
import com.haniumpkg.myapp.KeyboardAndMessageVO;
import com.haniumpkg.myapp.KeyboardVO;
import com.haniumpkg.myapp.MessageVO;

/**
 * Handles requests for the application home page.
 */

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private SqlSession sqlSession; 

	@RequestMapping(value = "/keyboard", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
	public @ResponseBody KeyboardVO readKeyboard() throws Exception {

		KeyboardVO keyboardVO = new KeyboardVO();
		keyboardVO.setType("buttons");
		keyboardVO.addMenu("1. 예매");
		keyboardVO.addMenu("2. 알람");
		keyboardVO.addMenu("3. 위치 안내");
		return keyboardVO;
	}

	@RequestMapping(value = "/message", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	public @ResponseBody String MessageAPI(HttpServletRequest request) throws Exception {

		RequestParsing sessionContent = new RequestParsing(request);
		JSONParser parser = new JSONParser();
		Object userObject = parser.parse(sessionContent.getrequestParsing());
		JSONObject jsonObject = (JSONObject) userObject;
		UserDBCheck userStateCheck = new UserDBCheck(request, jsonObject, logger, sqlSession); 
		String parsingContent = (String) jsonObject.get("content");
		String parsingUserkey = (String) jsonObject.get("user_key");
		
		int currentState = userStateCheck.getLastState();
		
		FunctionController functionController = new FunctionController();
		KeyboardAndMessageVO answerKeyboardAndMessage = 
				functionController.getSystemAnswerMsgAndKeyboard(currentState, parsingContent, parsingUserkey);
			
		UpdateDB update = new UpdateDB(parsingUserkey, currentState, sqlSession);
				
		ObjectMapper mapper = new ObjectMapper();
		String parsingjson = mapper.writeValueAsString(answerKeyboardAndMessage);
		
		logger.info(parsingjson);
		return parsingjson;	
	}
	
	

	@RequestMapping(value = "/friend", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> friend() {
		Map<String, Object> jsonObject = new HashMap<String, Object>();

		jsonObject.put("code", 0);
		jsonObject.put("message", "SUCCESS");
		jsonObject.put("comment", "완료");

		return jsonObject;
	}

	@RequestMapping(value = "/friend/*", method = RequestMethod.DELETE)
	public @ResponseBody Map<String, Object> delete() {
		Map<String, Object> jsonObject = new HashMap<String, Object>();
	
		jsonObject.put("code", 0);
		jsonObject.put("message", "SUCCESS");
		jsonObject.put("comment", "완료");

		return jsonObject;
	}

	@RequestMapping(value = "/chat_room/*", method = RequestMethod.DELETE)
	public @ResponseBody Map<String, Object> chat_room() {
		Map<String, Object> jsonObject = new HashMap<String, Object>();

		jsonObject.put("code", 0);
		jsonObject.put("message", "SUCCESS");
		jsonObject.put("comment", "완료");

		return jsonObject;
	}
	
}
