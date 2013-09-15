package com.gratex.perconik.activity;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import sk.stuba.fiit.perconik.environment.Environment;
import com.google.common.base.Throwables;

class Internals
{
	static final int unknonwnPid = -1;
	
	static final DatatypeFactory factory;
	
	static
	{
		try
		{
			factory = DatatypeFactory.newInstance();
		}
		catch (DatatypeConfigurationException e)
		{
			throw Throwables.propagate(e);
		}
	}
	
	static final int pid()
	{
		try
		{
			return Environment.pid();
		}
		catch (RuntimeException e)
		{
			return unknonwnPid;
		}
	}
}
