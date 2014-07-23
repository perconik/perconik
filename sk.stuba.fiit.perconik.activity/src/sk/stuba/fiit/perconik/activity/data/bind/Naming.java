package sk.stuba.fiit.perconik.activity.data.bind;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.PropertyNamingStrategyBase;

public final class Naming extends PropertyNamingStrategyBase
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
		
		return strategy.translate(input);
	}
}
