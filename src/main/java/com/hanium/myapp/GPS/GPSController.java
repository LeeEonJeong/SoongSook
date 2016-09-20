package com.hanium.myapp.GPS;

import org.apache.ibatis.session.SqlSession;

import com.hanium.myapp.CurrentState_Templete;
import com.hanium.myapp.KeyboardAndMessage_Templete;
import com.haniumpkg.myapp.KeyboardAndMessageVO;
import com.haniumpkg.myapp.KeyboardVO;
import com.haniumpkg.myapp.MessageVO;
import com.haniumpkg.myapp.PhotoVO;



public class GPSController extends KeyboardAndMessage_Templete implements CurrentState_Templete{
	

	final int GPS_tableOfcontent[] = {3000, 3100, 3110, 3120, 3130, 3140, 3200, 3210};
	private String text = "ȯ���մϴ�. ����� ��ġ Ȯ���� �����Ͽ����ϴ�~\n" +
						  "1. �͹̳� ��ġ ����\n" +
		                  "2. ���� ���� ��ġ ����";
	private MyLocation mylocation; 
	private Terminal_Location terminal;
	private int no;
	
	public GPSController(int currentUserState, String userAnswerString, SqlSession sqlSession) throws Exception {
		
		
		keyboardAndMessageVO = new KeyboardAndMessageVO();
		KeyboardVO keyboardVO = null;
		MessageVO messageVO = new MessageVO(text);
		
		if(currentUserState >= 3200)
		{
			mylocation = new MyLocation();
		}
		
		else if(currentUserState >= 3100)
		{
			terminal = new Terminal_Location(currentUserState, userAnswerString);
			text = terminal.gettext();
			messageVO.setText(text);
			messageVO.setmessage_button(terminal.getMessageButton());
			messageVO.setPhoto(terminal.getphoto());
		}
		
		else
			setUpdatedUserState(GPS_tableOfcontent[0]);
					
		keyboardAndMessageVO.setMessage(messageVO);
		keyboardAndMessageVO.setKeyboard(keyboardVO);
	}

	@Override
	public void setUpdatedUserState(int no) {this.no = no;}

	@Override
	public int getUpdatedUserState() {return this.no;}
	
}