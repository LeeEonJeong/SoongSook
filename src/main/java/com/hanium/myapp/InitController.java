package com.hanium.myapp;


import com.haniumpkg.myapp.KeyboardAndMessageVO;
import com.haniumpkg.myapp.KeyboardVO;
import com.haniumpkg.myapp.MessageVO;


public class InitController extends KeyboardAndMessage_Templete{

	private String text = "���������͹̳ο� ���� ���� ȯ���մϴ�^^?\n" +
			  "1. ���� ����\n" +
		      "2. �˶� ����\n" +
			  "3. ��ġ �ȳ� ����\n";
	
	public InitController()
	{
		
		keyboardAndMessageVO = new KeyboardAndMessageVO();
		
		String text = this.text;
		
	
	//	ApplicationContext context = new FileSystemXmlApplicationContext("/hanium1/src/main/resources/Next_State.xml");
		
		MessageVO messageVO = new MessageVO(text);
		KeyboardVO keyboardVO = null;
		
		keyboardAndMessageVO.setMessage(messageVO);
		keyboardAndMessageVO.setKeyboard(keyboardVO);
	}
	
	public String gettext() {return this.text;}
	
}
