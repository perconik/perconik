package com.gratex.perconik.activity.ide;

import javax.ws.rs.core.UriBuilder;

public class EnumUriHelper {
	private final static String BASE_URI = "http://perconik.gratex.com/useractivity/enum";
	
	private final static UriBuilder rcsServerTypeUri = UriBuilder.fromPath(
			BASE_URI).path("rcsserver/type").fragment("{type}");
	
	private final static UriBuilder codeElementTypeUri = UriBuilder.fromPath(
			BASE_URI).path("idecodeelementevent/codeelementtype").fragment("{type}");
	
	private final static UriBuilder ideStateChangeUri = UriBuilder.fromPath(
			BASE_URI).path("idestatechangeevent/statetype/{idename}").fragment("{type}");
	
	private final static UriBuilder lookinTypeUri = UriBuilder.fromPath(
			BASE_URI).path("idefindevent/lookintype/{idename}").fragment("{type}");
	
	private final static UriBuilder patternSyntaxTypeUri = UriBuilder.fromPath(
			BASE_URI).path("idefindevent/patternsyntaxtype/{idename}").fragment("{type}");

	public static String getRcsServerTypeUri(String type) {
		return rcsServerTypeUri.build(type).toString();
	}
	public static String getCodeElementTypeUri(String type) {
		return codeElementTypeUri.build(type).toString();
	}
	public static String getIdeStateChangeUri(String ideName, String type) {
		return ideStateChangeUri.build(ideName, type).toString();
	}
	public static String getLookinTypeUri(String ideName, String type) {
		return lookinTypeUri.build(ideName, type).toString();
	}
	public static String getPatternSyntaxTypeUri(String ideName, String type) {
		return patternSyntaxTypeUri.build(ideName, type).toString();
	}
}
