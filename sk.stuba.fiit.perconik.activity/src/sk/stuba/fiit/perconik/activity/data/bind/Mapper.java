package sk.stuba.fiit.perconik.activity.data.bind;

import sk.stuba.fiit.perconik.activity.data.type.BaseModule;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.guava.GuavaModule;

public final class Mapper
{
	private static final ObjectMapper instance;
	
	static
	{
		instance = new ObjectMapper();
		
		instance.registerModule(new BaseModule());
		instance.registerModule(new GuavaModule());
		
		instance.setPropertyNamingStrategy(new Naming());
		instance.setVisibility(PropertyAccessor.FIELD, Visibility.NONE);
		
		instance.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
	}
	
	private Mapper()
	{
		throw new AssertionError();
	}
	
	public static final ObjectMapper getInstance()
	{
		return instance;
	}
}
