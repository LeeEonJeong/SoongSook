package com.hanium.myapp.GPS;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.hanium.myapp.CurrentState_Templete;
import com.hanium.myapp.HomeController;
import com.hanium.myapp.KeyboardAndMessage_Templete;
import com.haniumpkg.myapp.KeyboardAndMessageVO;
import com.haniumpkg.myapp.KeyboardVO;
import com.haniumpkg.myapp.MessageVO;

public class GPSController extends KeyboardAndMessage_Templete {

	private String text = "환영합니다. 당신은 위치 확인을 선택하였습니다~\n" + "1. 터미널 위치 선택\n" + "2. 도착시간 / 남은시간 안내 \n\n";
	private ApplicationContext context = new FileSystemXmlApplicationContext(
			"classpath:GuideArriveLeftTime.xml");

	@Autowired
	private GuideArriveLeftTime guidetime = (GuideArriveLeftTime) context.getBean("guidetime");
	private Terminal_Location terminal;

	public GPSController(String user_key, int currentUserState, String userAnswerString, SqlSession sqlSession)
			throws Exception {

		keyboardAndMessageVO = new KeyboardAndMessageVO();
		KeyboardVO keyboardVO = null;
		text += "00. 뒤로가기";
		MessageVO messageVO = new MessageVO(text);

		if (currentUserState >= 3200) { // 도착시간 및 남은 시간 안내
			text = guidetime.gettext();
			text += guidetime.getString(user_key, sqlSession);
			messageVO.setText(text);
		}

		else if (currentUserState >= 3100) //터미널 위치 안내
		{

			if(currentUserState >= 3121) {
				terminal = new Terminal_Location(user_key, currentUserState, userAnswerString);
				text = terminal.gettext();
				text = text + "\n00. 뒤로가기";
				messageVO.setmessage_button(terminal.getMessageButton());
				messageVO.setPhoto(terminal.getphoto());

				if (terminal.getTerminal_name() != null)
					HomeController.setUserSavingList(user_key, terminal.getTerminal_name());
			}
			
			else if(currentUserState >= 3110)
			{
				SearchTerminal searchterminal = new SearchTerminal(currentUserState, userAnswerString, user_key);
				text = searchterminal.gettext();
				messageVO.setmessage_button(searchterminal.getMessageButton());
				messageVO.setPhoto(searchterminal.getphoto());
				
				if (searchterminal.getTerminal_Name() != null)
					HomeController.setUserSavingList(user_key, searchterminal.getTerminal_Name());
				
			}
			
			
			else if (currentUserState == 3100) { //한번에 선택
				AlltogetherTerminal att = new AlltogetherTerminal();
				text = att.gettext();
			}
			
			messageVO.setText(text);
		}


		keyboardAndMessageVO.setMessage(messageVO);
		keyboardAndMessageVO.setKeyboard(keyboardVO);
	}

}