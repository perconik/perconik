package com.gratex.perconik.activity;

import java.util.GregorianCalendar;
import javax.xml.datatype.XMLGregorianCalendar;
import com.google.common.base.Supplier;

public final class TimeSupplier implements Supplier<XMLGregorianCalendar>
{
	private static final TimeSupplier instance = new TimeSupplier();
	
	private TimeSupplier()
	{
	}
	
	public static final TimeSupplier getInstance()
	{
		return instance;
	}
	
	public final XMLGregorianCalendar get()
	{
		GregorianCalendar calendar = new GregorianCalendar();
		
		return Internals.datatypeFactory().newXMLGregorianCalendar(calendar);
	}
}
