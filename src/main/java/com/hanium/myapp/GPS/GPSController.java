package com.hanium.myapp.GPS;

import org.apache.ibatis.session.SqlSession;

import com.hanium.myapp.CurrentState_Templete;
import com.hanium.myapp.HomeController;
import com.hanium.myapp.KeyboardAndMessage_Templete;
import com.haniumpkg.myapp.KeyboardAndMessageVO;
import com.haniumpkg.myapp.KeyboardVO;
import com.haniumpkg.myapp.MessageVO;


public class GPSController extends KeyboardAndMessage_Templete implements CurrentState_Templete {

	final int GPS_tableOfcontent[] = { 3000, 3100, 3110, 3120, 3130, 3140, 3200, 3210 };
	private String text = "환영합니다. 당신은 위치 확인을 선택하였습니다~\n" + "1. 터미널 위치 선택\n" + "2. 도착시간 / 남은시간 안내 \n\n";
	private MyLocation mylocation;
	private Terminal_Location terminal;
	private int no;

	public GPSController(String user_key, int currentUserState, String userAnswerString, SqlSession sqlSession)
			throws Exception {

		keyboardAndMessageVO = new KeyboardAndMessageVO();
		KeyboardVO keyboardVO = null;
		text = text + "00. 뒤로가기";
		MessageVO messageVO = new MessageVO(text);

		if (currentUserState >= 3200) {
			mylocation = new MyLocation();
		}

		else if (currentUserState >= 3100) {
			terminal = new Terminal_Location(user_key, currentUserState, userAnswerString);
			text = terminal.gettext();

			text = text + "\n00. 뒤로가기";
			messageVO.setText(text);
			messageVO.setmessage_button(terminal.getMessageButton());
			messageVO.setPhoto(terminal.getphoto());

			if (terminal.getTerminal_name() != null)
				HomeController.setUserSavingList(user_key, terminal.getTerminal_name());
			// }
		}

		else
			setUpdatedUserState(GPS_tableOfcontent[0]);

		keyboardAndMessageVO.setMessage(messageVO);
		keyboardAndMessageVO.setKeyboard(keyboardVO);
	}

	@Override
	public void setUpdatedUserState(int no) {
		this.no = no;
	}

	@Override
	public int getUpdatedUserState() {
		return this.no;
	}

}