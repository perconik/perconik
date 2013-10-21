package conmark.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import conmark.Activator;

public class MarkTemplate {

	private final String mk;
	
	public MarkTemplate(){
		Date d = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'0000Z'");
		sf.setTimeZone(TimeZone.getTimeZone("UTC"));
		String unm = Activator.getDefault().getPreferenceStore().getString("conmark.prefs.user");
		if (unm == null || unm.isEmpty()) unm = new com.sun.security.auth.module.NTSystem().getDomain()+"/"+System.getProperty("user.name");		
		mk = unm+" "+sf.format(d)+" ";
	}
	
	public String getInfo(){ return mk; }
	
//	//      2013-07-23T14:10:17.0735270Z
//	// user 2013-08-06T13:51:04.666Z 
//	public static void main(String[] args) {
//		System.out.println(new MarkTemplate().getInfo());
//	}
}
