package com.hanium.myapp;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hanium.myapp.DB.UpdateDB;
import com.hanium.myapp.DB.UserDBCheck;
import com.haniumpkg.myapp.KeyboardAndMessageVO;
import com.haniumpkg.myapp.KeyboardVO;


/**
 * Handles requests for the application home page.
 */

@Controller
@SessionAttributes("next")

public class HomeController {

	@Autowired
	private SqlSession sqlSession;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	
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
				functionController.getSystemAnswerMsgAndKeyboard(currentState, parsingContent, parsingUserkey, sqlSession);
			
		//System.out.println("currentstate = " + functionController.getno());
		UpdateDB update = new UpdateDB(parsingUserkey, functionController.getno(), sqlSession);
				
		ObjectMapper mapper = new ObjectMapper();
		String parsingjson = mapper.writeValueAsString(answerKeyboardAndMessage);
		parsingjson = parsingjson.replace("message_Button", "message_button");
		
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
