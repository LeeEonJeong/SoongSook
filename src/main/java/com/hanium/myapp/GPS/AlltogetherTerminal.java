package com.hanium.myapp.GPS;

public class AlltogetherTerminal {
	private String text = "터미널 위치를 선택하셨습니다. 터미널 검색으로 바로 가시겠습니까?\n" +
					      "1. 예\n" +
					      "2. 아니오(지역을 거쳐 터미널 검색)";
	private int no = 3101;
	
	public String gettext() {return this.text;}
	public int getno() {return this.no;}
}
