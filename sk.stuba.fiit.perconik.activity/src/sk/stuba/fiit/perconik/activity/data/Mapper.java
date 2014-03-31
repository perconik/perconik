package sk.stuba.fiit.perconik.activity.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.PropertyNamingStrategyBase;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.guava.GuavaModule;

public final class Mapper
{
	private static final ObjectMapper instance;
	
	static
	{
		instance = new ObjectMapper();
		
		instance.registerModule(new GuavaModule());
		
		instance.setPropertyNamingStrategy(new Naming());
		instance.setVisibility(PropertyAccessor.FIELD, Visibility.NONE);
		
		instance.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
	}
	
	private Mapper()
	{
		throw new AssertionError();
	}
	
	public static final class Naming extends PropertyNamingStrategyBase
	{
		private static final long serialVersionUID = 0L;
		
		private static final LowerCaseWithUnderscoresStrategy strategy = new LowerCaseWithUnderscoresStrategy();
		
		public Naming()
		{
		}
		
		@Override
		public final String translate(String input)
		{
			if (input == null)
			{
				return null;
			}
			
			if (input.charAt(0) == '_')
			{
				input = "_" + input;
			}
			
			return strategy.translate(input.toLowerCase());
		}
	}
	
	public static final ObjectMapper getInstance()
	{
		return instance;
	}
}
