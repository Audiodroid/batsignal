package batsignal;

import java.util.Date;

public class BatMessage {

	private String name_;
	private Date date_;
	private String text_;
	
	public BatMessage(String name, Date date, String text) {
		name_ = name;
		date_ = date;
		text_ = text;
	}
	
	public String toString() {
        
		String str = new String();
		
		str += name_ + " said on ";
		str += date_.toString() + ": \n";
		str += "\"" + text_ + "\"\n";
		
		return str;
	}	
}
