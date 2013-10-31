package com.gratex.perconik.activity.ide;

import static com.gratex.perconik.activity.ide.Internals.console;
import static com.gratex.perconik.activity.ide.Internals.debug;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;
import javax.xml.datatype.XMLGregorianCalendar;
import sk.stuba.fiit.perconik.environment.Environment;
import sk.stuba.fiit.perconik.utilities.MoreStrings;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.gratex.perconik.activity.MilestoneResolver;
import com.gratex.perconik.activity.TimeSupplier;
import com.gratex.perconik.services.vs.IdeEventDto;

final class Debug
{
	static final Set<String> flags;
	
	static final MilestoneResolver<IdeEventDto> milestoneResolver = new MilestoneResolver<IdeEventDto>()
	{
		public final boolean isMilestone(final IdeEventDto data)
		{
			return true;
		}
	};

	static final TimeSupplier timeSupplier = new TimeSupplier()
	{
		public final XMLGregorianCalendar from(final long time)
		{
			GregorianCalendar calendar = new GregorianCalendar();
			
			calendar.setTimeInMillis(time);
			calendar.set(Calendar.YEAR, 2000);
			
			return Internals.datatypeFactory.newXMLGregorianCalendar(calendar);
		}
	};

	static
	{
		String value = Strings.nullToEmpty(Environment.getVariable("UACA"));
		
		if (debug) console.print("UACA environment variable: %s", value.isEmpty() ? "null" : value);
		
		flags = ImmutableSet.copyOf(Iterables.transform(Splitter.on(',').split(value), MoreStrings.toLowerCaseFunction()));
		
		if (debug) console.print("UACA extracted flags: %s", flags.isEmpty() ? "none" : Joiner.on(", ").join(flags));
	}
	
	private Debug()
	{
		throw new AssertionError();
	}
}
