package com.hanium.myapp.Alarm;

public class ReservationDTO {
	int no;
	String user;
	String dep_ter;
	String arr_ter;
	String dep_time; //Date타입 //에러날거같음
	String res_time; //Timestamp타입//에러날거같음
	String level;
	int adult;
	int child;
	
	public ReservationDTO(int no, String user, String dep_ter, String arr_ter,
			String dep_time, String res_time, String level, int adult, int child) {
		super();
		this.no = no;
		this.user = user;
		this.dep_ter = dep_ter;
		this.arr_ter = arr_ter;
		this.dep_time = dep_time;
		this.res_time = res_time;
		this.level = level;
		this.adult = adult;
		this.child = child;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDep_ter() {
		return dep_ter;
	}

	public void setDep_ter(String dep_ter) {
		this.dep_ter = dep_ter;
	}

	public String getArr_ter() {
		return arr_ter;
	}

	public void setArr_ter(String arr_ter) {
		this.arr_ter = arr_ter;
	}

	public String getDep_time() {
		return dep_time;
	}

	public void setDep_time(String dep_time) {
		this.dep_time = dep_time;
	}

	public String getRes_time() {
		return res_time;
	}

	public void setRes_time(String res_time) {
		this.res_time = res_time;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getAdult() {
		return adult;
	}

	public void setAdult(int adult) {
		this.adult = adult;
	}

	public int getChild() {
		return child;
	}

	public void setChild(int child) {
		this.child = child;
	}

	@Override
	public String toString() {
		return "ReservationDTO [no=" + no + ", user=" + user + ", dep_ter="
				+ dep_ter + ", arr_ter=" + arr_ter + ", dep_time=" + dep_time
				+ ", res_time=" + res_time + ", level=" + level + ", adult="
				+ adult + ", child=" + child + "]";
	}
	
	
	
}
