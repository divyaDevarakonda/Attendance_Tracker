package service;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;

public class AttendanceService {

	public String validateStudentId(Map<String, String> StudentMap,String StudentId){
		String rowNo="";
		if(StudentMap.containsKey(StudentId))
		{
			rowNo=StudentMap.get(StudentId);
		}
		return rowNo;
	}
	
	public boolean validateStudentKey(String profInputKey,String key){
		boolean valid = false;
		if(profInputKey.equals(key)){
			valid = true;
		}
		return valid;
	}
	
	public boolean getIPAddress()
	{
		try{
			String ipAddress = "10.117";
			String ipv4=InetAddress.getLocalHost().getHostAddress();
			if(ipAddress.equals(ipv4.substring(0,6)))
			return true;
			else
			return false;
		}
		catch(UnknownHostException e){
			System.out.println("Exception : "+e);
			return false;
		}
	}
	
	public long calculateTimeLapsed (String enteredTime) {
		long elapsed=0;
		try{
 	    SimpleDateFormat df = new SimpleDateFormat("HH:mm");
 	    Date date= df.parse(enteredTime);
 	    Calendar cal = Calendar.getInstance();
 	    cal.setTime(date);
 	    cal.add(Calendar.MINUTE, 4);
 	    String newTime = df.format(cal.getTime());
 	    Date date2= df.parse(newTime);
 	    elapsed =  date2.getTime()-date.getTime() ; 
		}catch(Exception e){
			System.out.println("Exception : "+e);
		}
		return elapsed;
	}
	
	public void emailService(String email){
		EmailService emailservice = new EmailService();
		emailservice.sentEmail(email, "june 28", "Thankyou for submitting attendance! Your Attendance is successfully submitted.", "Attendance Confirmation");
	}
	
}
