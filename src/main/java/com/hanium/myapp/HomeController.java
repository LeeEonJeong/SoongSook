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

////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////// 사용자로부터 메시지를 받으면, 반드시 HomeController.class 부터 거쳐야 한다.   ////////////////////// 
/////////////////  이유는 메시지를 받을 때는 고정적으로 "/message" 이라는 url로 던져주는데,        //////////////////////
////////////////   저 url이 아니면 api에서 다시 사용자에게 response를 할 수 없다.               /////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class); // 이 변수를 통해, 로그를 볼 수 있다. 
	@Autowired
	private SqlSession sqlSession; //DB를 다룰려면 이 변수가 있어야 한다.  

	@RequestMapping(value = "/keyboard", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
	public @ResponseBody KeyboardVO readKeyboard() throws Exception {

		// logger.debug("test");

		KeyboardVO keyboardVO = new KeyboardVO();
		keyboardVO.setType("buttons");
		keyboardVO.addMenu("1. 예매");
		keyboardVO.addMenu("2. 알람");
		keyboardVO.addMenu("3. 위치 안내");
		return keyboardVO;
	}

	@RequestMapping(value = "/message", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")

	public @ResponseBody String MessageAPI(HttpServletRequest req) throws Exception {

		RequestParsing content = new RequestParsing(req);
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(content.getrequestParsing());
		JSONObject jsonObj = (JSONObject) obj;

		///////////////////////여기까지가 사용자로부터 받은 요청을 json으로 파싱 
		
		System.out.println("eonjeong : " + content); //req.getReader()를 해서 (BufferedReaader타입으로해서)한줄씩읽은것을 String으로 반환해서 toString 오버라이드 해서 가져온거
		System.out.println("---------------------");
		System.out.println(jsonObj);
		System.out.println("---------------------");
		
		KeyboardAndMessageVO keyboardAndMessageVO = new KeyboardAndMessageVO(); 
		MessageVO messageVO = null;

		UserDBCheck usercheck = new UserDBCheck(req, jsonObj, logger, sqlSession); //생성자 안에서 user_key를 이용해 현재 있는 state를 set해준다(result 멤버변수에 값 저장)

		// logger.info(usercheck.get_result());
		String parsingcontent = (String) jsonObj.get("content");
		String parsinguserkey = (String) jsonObj.get("user_key");
		
		FunctionController StringAddress = new FunctionController();
		String gettext = StringAddress.getaddress(usercheck.get_result(), parsingcontent);  //사용자에게 보낼 문구(text) 리턴/이건 FunctionController만 있으면 되는거잖아
		int getstate = StringAddress.get_currentlocation();//현재 사용자가 있는 위치state
		

		if (getstate == 0) { //처음 들어온 사람
			if (parsingcontent.equals("1"))
			{
				messageVO = new MessageVO("예매입니다~~"); //여기부분만 완성시키면 controller 클래스에 코드를  더 이상 추가시키지 않아도 됨
			}
			else if (parsingcontent.equals("2"))
			{
				HomeAlarm init = new HomeAlarm();
				gettext = init.initMessage();
				messageVO = new MessageVO(gettext);
				getstate = init.getState();
			}
				
			else if (parsingcontent.equals("3"))
			{
				HomeGPS init = new HomeGPS();
				gettext = init.initmessage();
				messageVO = new MessageVO(gettext);
				getstate = init.getno();
			}
			
			else {
				String text = "1. 예매\n" + "2. 알람\n" + "3. 위치 안내";
				messageVO = new MessageVO(text);
			}

		}
		else{ //기존에 있던 회원
			messageVO = new MessageVO(gettext);
			if(getstate/4 == 2){ //알람일경우
				AlarmController alarmController = new AlarmController(sqlSession, parsinguserkey, getstate, parsingcontent);
				HashMap<String, Object> result = alarmController.getNewStateAndMessage();
				getstate = (int)result.get("state");
				messageVO = new MessageVO((String)result.get("text"));
			}
			
		}
			
		UpdateDB update = new UpdateDB(parsinguserkey, getstate, sqlSession); //사용자에 대한 현재 위치 업데이트
		
		keyboardAndMessageVO.setMessage(messageVO);
		
		ObjectMapper mapper = new ObjectMapper();
		String parsingjson = mapper.writeValueAsString(keyboardAndMessageVO);
		
		logger.info(parsingjson); //제대로 파싱이 되었는지 로그로 확인
		return parsingjson; //keyboard나 message 리턴(사용여하에 둘다 리턴가능하게 할 수 있음)
		
		//// 마지막으로 위에서 언급한 코드 제외하면 팀원끼리 코드 짜면서, controller.class는 건들 필요가 없음 ////
		//// 우리는 FunctionController.java 만 추가만 시켜도 제대로 merge 가능함                               ////		
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
