package com.hanium.myapp.GPS;

public class MyLocation {
	private String text;
	private int no;
	
	public String initmessage()
	{
		String text = "ȯ���մϴ�. ����� ��ġ Ȯ���� �����Ͽ����ϴ�~\n" +
					  "���� ��ġ";
			
		this.text = text;
		this.no = 3200; 
				
		return this.text;
	}
	
	public int getno() { return this.no; }
}
