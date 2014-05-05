package com.gratex.perconik.activity.ide;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import sk.stuba.fiit.perconik.environment.Environment;

import com.google.common.base.CharMatcher;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.gratex.perconik.activity.MilestoneResolver;
import com.gratex.perconik.activity.TimeSupplier;
import com.gratex.perconik.services.uaca.vs.IdeCheckinDto;
import com.gratex.perconik.services.uaca.vs.IdeCodeOperationDto;
import com.gratex.perconik.services.uaca.vs.IdeDocumentOperationDto;
import com.gratex.perconik.services.uaca.vs.IdeDocumentOperationTypeEnum;
import com.gratex.perconik.services.uaca.vs.IdeEventDto;
import com.gratex.perconik.services.uaca.vs.IdeProjectOperationDto;
import com.gratex.perconik.services.uaca.vs.IdeStateChangeDto;

final class Internals
{
	static final PluginConsole console = IdeActivityConsole.getInstance();
	
	static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	static final boolean debug = Environment.debug;
	
	static final int unknonwnPid = -1;
	
	static final String enumUriAppName = "eclipse";
	
	static final String optionsSequence;
	
	static final Map<String, String> options;
	
	static
	{
		optionsSequence = Strings.nullToEmpty(Environment.getVariable("UACA")).toLowerCase();
		
		Splitter entries = Splitter.on(CharMatcher.anyOf(";,")).trimResults();
		Splitter keys    = Splitter.on(CharMatcher.anyOf(":=")).trimResults().limit(2);
		
		ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
		
		for (String option: entries.split(optionsSequence))
		{
			Iterator<String> parts = keys.split(option).iterator();
			
			builder.put(parts.next(), parts.hasNext() ? parts.next() : "true");
		}
		
		options = builder.build();
	}
	
	static final DatatypeFactory datatypeFactory;
	
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
	}
	
	static final Set<Class<? extends IdeEventDto>> milestoneDataTypes;

	static final Set<IdeDocumentOperationTypeEnum> milestoneDocumentOperationTypes;

	static final MilestoneResolver<IdeEventDto> milestoneResolver;
	
	static
	{
		milestoneDataTypes = ImmutableSet.of
		(
			IdeCheckinDto.class,
			IdeCodeOperationDto.class,
			IdeProjectOperationDto.class,
			IdeStateChangeDto.class
		);
	
		milestoneDocumentOperationTypes = ImmutableSet.of
		(
			IdeDocumentOperationTypeEnum.ADD,
			IdeDocumentOperationTypeEnum.REMOVE,
			IdeDocumentOperationTypeEnum.SAVE
		);
		
		milestoneResolver = new MilestoneResolver<IdeEventDto>()
		{
			public final boolean isMilestone(IdeEventDto data)
			{
				for (Class<?> type: milestoneDataTypes)
				{
					if (type.isInstance(data))
					{
						return true;
					}
				}
				
				if (data instanceof IdeDocumentOperationDto)
				{
					IdeDocumentOperationTypeEnum type = ((IdeDocumentOperationDto) data).getOperationType();
					
					Preconditions.checkState(type != null);
					
					return milestoneDocumentOperationTypes.contains(type);
				}
				
				return false;
			}
		};
	}
	
	static final TimeSupplier timeSupplier;
	
	static
	{
		timeSupplier = new TimeSupplier()
		{
			public final XMLGregorianCalendar from(final long time, boolean utc)
			{
				GregorianCalendar calendar = utc ? new GregorianCalendar(TimeZone.getTimeZone("UTC")) : new GregorianCalendar();
				
				calendar.setTimeInMillis(time);
				
				return datatypeFactory.newXMLGregorianCalendar(calendar);
			}
		};
	}
	
	private Internals()
	{
		throw new AssertionError();
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
	
	static final String enumUriAppName(){
		return enumUriAppName;
	}
}
