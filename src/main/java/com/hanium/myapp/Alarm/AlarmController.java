package com.hanium.myapp.Alarm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.parser.ParseException;

import com.hanium.myapp.DB.AlarmDBCheck;

public class AlarmController {
	private SqlSession sqlSession;
	private String parsinguserkey;
	private int state;
	private String parsingcontent;
	private AlarmDBCheck alarmDBCheck;
	
	public AlarmController(SqlSession sqlSession, String parsinguserkey, int state, String parsingcontent) throws ParseException{
		this.sqlSession = sqlSession;
		this.alarmDBCheck = new AlarmDBCheck(sqlSession, parsinguserkey);	
		this.parsinguserkey = parsinguserkey;
		this.state = state;
		this.parsingcontent = parsingcontent;
	}
	
	public HashMap<String,Object> getNewStateAndMessage(){ 
		int newState = 0;
		String newText=""; //StringBuffer로 - 나중에
		HashMap<String, Object> result = new HashMap<String,Object>();
		
		switch(state){
			case 2000:{
				List<ReservationDTO> reservations = alarmDBCheck.getReservations(); //사용자가 등록한 예약 가져옴
				Iterator<ReservationDTO> iter = reservations.iterator();
				StringBuffer  reservationList = new StringBuffer();
				int i=0;
				while(iter.hasNext()){
					ReservationDTO dto = (ReservationDTO)iter.next();
					reservationList.append(i+" : "+dto.toString()+"\n");
				}
				
				if(parsingcontent.contains("1")) //알람설정
				{
					AlarmConfig init = new AlarmConfig();
					newText = init.initmessage();
					newText.concat(reservationList.toString());
					newState = init.getState();
				}else if(parsingcontent.contains("2"))//알람확인
				{
					AlarmConfirm init = new AlarmConfirm();
					newText = init.initmessage();
					state = init.getNo();
				}else if(parsingcontent.contains("3"))//알람취소
				{
					AlarmCancel init = new AlarmCancel();
					newText = init.initmessage();
					newState = init.getState();
				}
				result.put("state",newState);
				result.put("text", newText);
			}
				
		}
		return result;
	}
	
	public String getParsinguserkey() {
		return parsinguserkey;
	}
	public void setParsinguserkey(String parsinguserkey) {
		this.parsinguserkey = parsinguserkey;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
}
