package com.gratex.perconik.activity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.guava.GuavaModule;

public final class ObjectMappers
{
	private static final ObjectMapper instance;
	
	static
	{
		instance = new ObjectMapper();
		
		instance.registerModule(new GuavaModule());
		
		instance.setPropertyNamingStrategy(new PropertyNaming());
		
		instance.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
	}
	
	private ObjectMappers()
	{
		throw new AssertionError();
	}
	
	public static final ObjectMapper getDefault()
	{
		return instance;
	}
}
