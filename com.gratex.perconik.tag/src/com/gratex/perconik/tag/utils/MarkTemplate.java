package com.gratex.perconik.tag.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.gratex.perconik.tag.plugin.Activator;
import com.gratex.perconik.tag.prefs.PrefKeys;

public class MarkTemplate {

	private final String mk;
	
	public MarkTemplate(){
		Date d = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'0000Z'");
		sf.setTimeZone(TimeZone.getTimeZone("UTC"));
		String unm = Activator.getDefault().getPreferenceStore().getString(PrefKeys.user);
		if (unm == null || unm.isEmpty()) unm = getDomain()+"/"+System.getProperty("user.name");		
		mk = unm+" "+sf.format(d)+" ";
	}
	
	public static String getDomain() {
		try {
			Class<?> nt = Class.forName("com.sun.security.auth.module.NTSystem");
			
			return (String) nt.getMethod("getDomain").invoke(null);
		} catch ( Throwable t ) {
			return "unknown-domain";
		}
	}
	
	public String getInfo(){ return mk; }
	
//	//      2013-07-23T14:10:17.0735270Z
//	// user 2013-08-06T13:51:04.666Z 
//	public static void main(String[] args) {
//		System.out.println(new MarkTemplate().getInfo());
//	}
}
