package com.gratex.perconik.activity.ide;

import javax.ws.rs.core.UriBuilder;

public final class UacaUriHelper
{
	private static final String application = "eclipse";

	private final static String base = "http://perconik.gratex.com/useractivity/enum";

	private final static UriBuilder codeElementTypeUri = UriBuilder.fromPath(base).path("idecodeelementevent/codeelementtype").fragment("{type}");

	private final static UriBuilder ideStateChangeTypeUri = UriBuilder.fromPath(base).path("idestatechangeevent/statetype/{idename}").fragment("{type}");

	private final static UriBuilder lookinTypeUri = UriBuilder.fromPath(base).path("idefindevent/lookintype/{idename}").fragment("{type}");

	private final static UriBuilder patternSyntaxTypeUri = UriBuilder.fromPath(base).path("idefindevent/patternsyntaxtype/{idename}").fragment("{type}");

	private final static UriBuilder rcsServerTypeUri = UriBuilder.fromPath(base).path("rcsserver/type").fragment("{type}");
	
	private UacaUriHelper()
	{
		throw new AssertionError();
	}
	
	public static final String forCodeElementType(final String type)
	{
		return codeElementTypeUri.build(type).toString();
	}

	public static final String forIdeStateChangeType(final String type)
	{
		return ideStateChangeTypeUri.build(application, type).toString();
	}

	public static final String forLookinType(final String type)
	{
		return lookinTypeUri.build(application, type).toString();
	}

	public static final String forPatternSyntaxType(final String type)
	{
		return patternSyntaxTypeUri.build(application, type).toString();
	}

	public static final String forRcsServerType(final String type)
	{
		return rcsServerTypeUri.build(type).toString();
	}
}
