package com.gratex.perconik.activity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.PropertyNamingStrategyBase;

public final class PropertyNaming extends PropertyNamingStrategyBase
{
	private static final long serialVersionUID = 0L;
	
	private static final LowerCaseWithUnderscoresStrategy strategy = new LowerCaseWithUnderscoresStrategy();
	
	public PropertyNaming()
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