package com.gratex.perconik.activity;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import sk.stuba.fiit.perconik.environment.Environment;
import sk.stuba.fiit.perconik.utilities.MoreStrings;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.base.Supplier;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.gratex.perconik.activity.plugin.Activator;
import com.gratex.perconik.services.activity.EventDto;

class Internals
{
	static final PluginConsole console = Activator.getDefault().getConsole();
	
	static final boolean debug = Environment.debug;
	
	static final Set<String> flags;
	
	static final int unknonwnPid = -1;
	
	private static final DatatypeFactory datatypeFactory;
	
	private static final Predicate<EventDto> milestoneResolver;
	
	private static final Supplier<XMLGregorianCalendar> timeSupplier;
	
	static final Predicate<EventDto> alwaysMilestoneResolver = new Predicate<EventDto>()
	{
		public final boolean apply(final EventDto input)
		{
			return true;
		}
	};

	static final Supplier<XMLGregorianCalendar> fixedYearTimeSupplier = new Supplier<XMLGregorianCalendar>()
	{
		public final XMLGregorianCalendar get()
		{
			GregorianCalendar calendar = new GregorianCalendar();
			
			calendar.set(Calendar.YEAR, 2000);
			
			return datatypeFactory().newXMLGregorianCalendar(calendar);
		}
	};

	static
	{
		try
		{
			datatypeFactory = DatatypeFactory.newInstance();
		}
		catch (DatatypeConfigurationException e)
		{
			throw Throwables.propagate(e);
		}
		
		String value = Strings.nullToEmpty(Environment.getVariable("UACA"));
		
		if (debug) console.print("UACA environment variable: %s", value.isEmpty() ? "null" : value);
		
		flags = ImmutableSet.copyOf(Iterables.transform(Splitter.on(',').split(value), MoreStrings.toLowerCaseFunction()));
		
		if (debug) console.print("UACA extracted flags: %s", flags.isEmpty() ? "none" : Joiner.on(", ").join(flags));
		
		milestoneResolver = flags.contains("always-milestone") ? alwaysMilestoneResolver : MilestoneResolver.getInstance();
		timeSupplier      = flags.contains("fixed-year") ? fixedYearTimeSupplier : TimeSupplier.getInstance();
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
	
	static final DatatypeFactory datatypeFactory()
	{
		return datatypeFactory;
	}
	
	static final Predicate<EventDto> milestoneResolver()
	{
		return milestoneResolver;
	}
	
	static final Supplier<XMLGregorianCalendar> timeSupplier()
	{
		return timeSupplier;
	}
}
